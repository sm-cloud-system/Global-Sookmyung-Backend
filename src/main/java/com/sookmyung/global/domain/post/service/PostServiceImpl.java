package com.sookmyung.global.domain.post.service;

import static com.sookmyung.global.common.code.fail.PostExceptionCode.INVALID_CREATE_POST_REQUEST;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.sookmyung.global.common.enums.*;
import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.domain.bookmark.repository.*;
import com.sookmyung.global.domain.comment.repository.*;
import com.sookmyung.global.domain.like.repository.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.member.repository.*;
import com.sookmyung.global.domain.post.dto.request.*;
import com.sookmyung.global.domain.post.dto.response.*;
import com.sookmyung.global.domain.post.entity.*;
import com.sookmyung.global.domain.post.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  private final MemberRepository memberRepository;
  private final LikeRepository likeRepository;
  private final BookmarkRepository bookmarkRepository;
  private final CommentRepository commentRepository;

  @Transactional
  @Override
  public Long createPost(final Long memberId, CreatePostRequest request) {
    final Member member = memberRepository.findByIdOrThrow(memberId);
    validatePostCreationPermission(member.getRole(), request.type());
    Post post =
        Post.builder()
            .postType(request.type())
            .title(request.title())
            .content(request.content())
            .author(member)
            .build();
    postRepository.save(post);
    return post.getId();
  }

  private void validatePostCreationPermission(Role role, PostType postType) {
    boolean isInternationalStudentPostType = PostType.INTERNATIONAL_STUDENT.equals(postType);
    boolean isNotInternationalStudentRole = !Role.INTERNATIONAL_STUDENT.equals(role);
    if (isInternationalStudentPostType && isNotInternationalStudentRole) {
      throw new PostException(INVALID_CREATE_POST_REQUEST);
    }
  }

  @Override
  public PostResponse getPost(final Long memberId, final Long postId) {
    boolean isNotServiceMember = memberId == null;
    if (isNotServiceMember) {
      return getPostForNotServiceMember(postId);
    }
    return getPostForServiceMember(memberId, postId);
  }

  private PostResponse getPostForNotServiceMember(final Long postId) {
    Post post = postRepository.findByIdOrThrow(postId);
    boolean isMyPost = false;
    boolean isLiked = false;
    int likeCount = likeRepository.countByPost(post);
    boolean isBookmarked = false;
    int bookmarkCount = bookmarkRepository.countByPost(post);

    return PostResponse.of(isMyPost, post, isLiked, likeCount, isBookmarked, bookmarkCount);
  }

  private PostResponse getPostForServiceMember(final Long memberId, final Long postId) {
    Member member = memberRepository.findByIdOrThrow(memberId);
    Post post = postRepository.findByIdOrThrow(postId);
    boolean isMyPost = memberId.equals(post.getAuthor().getId());
    boolean isLiked = likeRepository.existsLikeByMemberAndPost(member, post);
    int likeCount = likeRepository.countByPost(post);
    boolean isBookmarked = bookmarkRepository.existsLikeByMemberAndPost(member, post);
    int bookmarkCount = bookmarkRepository.countByPost(post);

    return PostResponse.of(isMyPost, post, isLiked, likeCount, isBookmarked, bookmarkCount);
  }

  @Override
  public List<PostsBySearchingResponse> getPostsBySearching(final String searchWord) {
    final List<Post> postsBySearchWord = postRepository.findAllBySearchWord(searchWord);
    return postsBySearchWord.stream()
        .map(
            post -> {
              int commentCount = commentRepository.countByPost(post);
              int likeCount = likeRepository.countByPost(post);
              return PostsBySearchingResponse.of(post, commentCount, likeCount);
            })
        .toList();
  }

  @Override
  public List<PostsResponse> getPosts(final PostType postType) {
    final List<Post> posts = postRepository.findAllByPostTypeOrderByCreatedAtDesc(postType);
    return posts.stream()
        .map(
            post -> {
              int commentCount = commentRepository.countByPost(post);
              int likeCount = likeRepository.countByPost(post);
              return PostsResponse.of(post, commentCount, likeCount);
            })
        .toList();
  }
}
