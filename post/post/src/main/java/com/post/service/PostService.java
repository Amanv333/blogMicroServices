package com.post.service;

import com.post.Dto.PostDto;
import com.post.Entity.Post;
import com.post.config.RestTemplateConfig;

import com.post.payload.PostDtoComments;
import com.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private RestTemplateConfig restTemplateConfig;

    public void savePost(PostDto dto) {
        Post post = new Post();
        String postId = UUID.randomUUID().toString();
        post.setId(postId);
        post.setContent(dto.getContent());
        post.setDescription(dto.getDescription());
        post.setTitle(dto.getTitle());

        repository.save(post);
    }

    public PostDto getPostById(String id) {
        Post byId = repository.findById(id).orElseThrow(
                ()->new RuntimeException("Post not available"));
        PostDto dto = new PostDto();
        dto.setContent(byId.getContent());
        dto.setDescription(byId.getDescription());
        dto.setTitle(byId.getTitle());
        return dto;
    }

    public PostDtoComments getPostComments(String postId) {
        Post post = repository.findById(postId).get();
        ArrayList comments = restTemplateConfig.getTemplate().getForObject(
                "http://COMMENT-SERVICE/api/comment/" + postId, ArrayList.class
        );
        PostDtoComments dto =new PostDtoComments();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        dto.setComments(comments);

        return dto;

    }
}
