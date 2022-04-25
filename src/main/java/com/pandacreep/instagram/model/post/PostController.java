package com.pandacreep.instagram.model.post;

import com.pandacreep.instagram.model.comment.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/post/{id}")
    public String commentPost(@PathVariable String id,
                           Model model,
                           Principal principal) {
        PostDto post = postService.findPostsById(id, principal);
        model.addAttribute("post", post);
        List<CommentDto> comments = postService.findComments(id);
        model.addAttribute("comments", comments);
        return "post";
    }

    @PostMapping("/comments/add")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addComment(@RequestParam String postId,
                             @RequestParam String comment,
                             Principal principal) {
        postService.addComment(postId, comment, principal);
        return "redirect:/post/" + postId;
    }

    @GetMapping("/like/{id}")
    public String likePost(@PathVariable String id,
                           Principal principal) {
        postService.addLike(id, principal);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showIndex(Model model, Principal principal) {
        model.addAttribute("posts", postService.findSubscriptionsPosts(principal));
        return "index";
    }

    @GetMapping("/posts/add")
    public String showAddPostPage() {
        return "post-add";
    }

    @PostMapping("/posts/add")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String add(@RequestParam MultipartFile file,
                      @RequestParam String description,
                      Principal principal) {
        postService.add(file, description, principal);
        return "redirect:/";
    }
}
