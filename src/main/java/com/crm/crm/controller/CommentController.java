package com.crm.crm.controller;

import com.crm.crm.entity.Comments;
import com.crm.crm.entity.Post;
import com.crm.crm.repository.CommentsRepository;
import com.crm.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private PostRepository postRepository;
    private CommentsRepository commentRepository;


    public CommentController(PostRepository postRepository, CommentsRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
        @PostMapping
        public String createComment(
               @RequestBody Comments comment,
              @RequestParam long postId){
            Post post = postRepository.findById(postId).get();
            comment.setPost(post);
            System.out.println("Comment11");
            commentRepository.save(comment);
            return "Comment created successfully!";
        }
}

