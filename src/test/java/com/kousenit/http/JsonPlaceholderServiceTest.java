package com.kousenit.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonPlaceholderServiceTest {
    private final JsonPlaceholderService service = new JsonPlaceholderService();

    @Test
    void getPost() throws Exception {
        BlogPost post = service.getPost(1);
        System.out.println(post);
        assertEquals(1, post.id());
    }

    @Test
    void sendPost() throws Exception {
        BlogPost post = new BlogPost(999, 1, "Title", "Body");
        BlogPost submittedPost = service.submitPost(post);
        System.out.println(submittedPost);
    }

}