package com.post.controller;

import com.post.Dto.PostDto;
import com.post.payload.PostDtoComments;
import com.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDto dto){
        postService.savePost(dto);
        return new ResponseEntity<>("PostCreate successfully", HttpStatus.CREATED);

    }
    @GetMapping("/particular/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable String postId){

        PostDto dto = postService.getPostById(postId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/{postId}/comments")
    public ResponseEntity<PostDtoComments> getPostComments(@PathVariable String postId){
        PostDtoComments postDto = postService.getPostComments(postId);
        return  new ResponseEntity<>(postDto,HttpStatus.OK);

    }
}
