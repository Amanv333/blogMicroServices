package com.comment.comment.service;

import com.comment.comment.config.RestTemplateConfig;
import com.comment.comment.payload.Post;
import com.comment.comment.repository.CommentRepository;
import com.comment.comment.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RestTemplateConfig restTemplate;
//http://localhost:8081/api/mpost/particular/{postId}
    public Comment saveComment(Comment comment){
        Post post = restTemplate.getTemplate().getForObject(
                "http://POST-SERVICE/api/post/particular/" + comment.getPostId(), Post.class);
        if(post!=null){
            String commentId = UUID.randomUUID().toString();
            comment.setId(commentId);
            return commentRepository.save(comment);
        }

        return null;
    }

    public List<Comment> getAllComment(String postId) {
       List<Comment> comments = commentRepository.findByPostId(postId);
       return comments;
    }
}
