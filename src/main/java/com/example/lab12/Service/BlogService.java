package com.example.lab12.Service;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.BlogRepository;
import com.example.lab12.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BlogService {


    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public List<Blog> getBlogList(Integer userId) {
        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new ApiException("User not found");
        }

        return blogRepository.findAllByUser(user);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public void addBlog(Integer userId, Blog blog) {
        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new ApiException("User not found");
        }

        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Integer blogId, Blog blog) {
        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new ApiException("User not found");
        }

        Blog existingBlog = blogRepository.findByUserIdAndId(user.getId(), blogId);

        if (existingBlog == null) {
            throw new ApiException("Blog not found");
        }

        existingBlog.setTitle(blog.getTitle());
        existingBlog.setBody(blog.getBody());
        blogRepository.save(existingBlog);
    }

    public void deleteBlog(Integer userId, Integer blogId) {
        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new ApiException("User not found");
        }

        Blog blog = blogRepository.findByUserIdAndId(user.getId(), blogId);

        if (blog == null) {
            throw new ApiException("Blog not found");
        }

        blogRepository.delete(blog);
    }

    public Blog getBlogById(Integer userId, Integer blogId) {
        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new ApiException("User not found");
        }

        Blog blog = blogRepository.findByUserIdAndId(user.getId(), blogId);

        if (blog == null) {
            throw new ApiException("Blog not found");
        }

        return blog;
    }

    public Blog getBlogByTitle(String title) {
        Blog blog = blogRepository.findBlogByTitle(title);

        if (blog == null) {
            throw new ApiException("Blog not found with the given title");
        }

        return blog;
    }

}