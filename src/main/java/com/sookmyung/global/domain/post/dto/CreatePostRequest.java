package com.sookmyung.global.domain.post.dto;

import com.sookmyung.global.common.enums.*;

public record CreatePostRequest(PostType type, String title, String content) {}