package io.github.rawadhossain.GikiZero.controller;

import io.github.rawadhossain.GikiZero.model.Post;
import io.github.rawadhossain.GikiZero.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    // Get all posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get post by ID
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postRepository.findById(id).orElse(null);
    }

    // Create new post
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    // Update post
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            return postRepository.save(post);
        }
        return null;
    }

    // Delete post
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "Post deleted with id " + id;
    }
}
