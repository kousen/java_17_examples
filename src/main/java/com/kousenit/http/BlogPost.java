package com.kousenit.http;

public record BlogPost(
        int id,
        int userId,
        String title,
        String body
) {
}
