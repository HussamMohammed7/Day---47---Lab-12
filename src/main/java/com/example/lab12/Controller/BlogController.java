package com.example.lab12.Controller;

import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get/user")
    public ResponseEntity getBlogsByUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogList(user.getId()));
    }

    @GetMapping("/get/all")
    public ResponseEntity getAllBlogs() {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAllBlogs());
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @Valid @RequestBody Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(HttpStatus.OK).body("Blog added successfully");
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @PathVariable Integer blogId, @Valid @RequestBody Blog blog) {
        blogService.updateBlog(user.getId(), blogId, blog);
        return ResponseEntity.status(HttpStatus.OK).body("Blog updated successfully");
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blogId) {
        blogService.deleteBlog(user.getId(), blogId);
        return ResponseEntity.status(HttpStatus.OK).body("Blog deleted successfully");
    }

    @GetMapping("/get/{blogId}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable Integer blogId) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogById(user.getId(), blogId));
    }

    @GetMapping("/get/title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogByTitle(title));
    }
}
