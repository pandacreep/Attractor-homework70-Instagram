package com.pandacreep.instagram.model.post;

import com.pandacreep.instagram.model.comment.Comment;
import com.pandacreep.instagram.model.comment.CommentDto;
import com.pandacreep.instagram.model.comment.CommentRepository;
import com.pandacreep.instagram.model.user.User;
import com.pandacreep.instagram.model.user.UserRepository;
import com.pandacreep.instagram.model.user.UserService;
import com.pandacreep.instagram.util.Image;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private  final CommentRepository commentRepository;
    private final UserService userService;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<PostDto> findSubscriptionsPosts(Principal principal) {
        var user = userRepository.findByEmail(principal.getName()).get();
        var subscriptions = user.getSubscriptions();
        List<Post> postFromSubscriptions = new ArrayList<>();
        for (int i = 0; i < subscriptions.size(); i++) {
            postFromSubscriptions.addAll(subscriptions.get(i).getPosts());
        }

        List<PostDto> postsDto = new ArrayList<>();
        for (int i = 0; i < postFromSubscriptions.size(); i++) {
            PostDto post = PostDto.from(postFromSubscriptions.get(i));
            List<User> likeUsers = postFromSubscriptions.get(i).getLikeUsers();
            for (int j = 0; j < likeUsers.size(); j++) {
                String likeUserEmail = likeUsers.get(j).getEmail();
                if (likeUserEmail.equalsIgnoreCase(user.getEmail())) {
                    post.setLiked(true);
                }
            }
            postsDto.add(post);
        }
        postsDto.sort(Comparator.comparing(PostDto::getDate).reversed());
        return postsDto;
    }

    public void add(MultipartFile file, String description, Principal principal) {
        User user = userService.getByEmailOrNull(principal.getName());
        if (file != null) {
            Image image = Image.getImage(file);
            String imageString = Base64.getEncoder().encodeToString(image.getImage().getData());
            Post post = new Post(UUID.randomUUID().toString(), imageString, description,
                    LocalDateTime.now(),
                    user, new ArrayList<>(), new ArrayList<>());
            postRepository.save(post);
            List<Post> userPosts = user.getPosts();
            userPosts.add(post);
            user.setPosts(userPosts);
            userRepository.save(user);
        }
    }

    public List<PostDto> findPostsByEmail(String email) {
        User user = userService.getByEmailOrNull(email);
        List<Post> posts = postRepository.findAllByUser(user);
        return posts.stream().map(PostDto::from).collect(Collectors.toList());
    }

    public void addLike(String id, Principal principal) {
        var post = postRepository.findById(id).get();
        List<User> likeUsers = post.getLikeUsers();
        var user = userRepository.findByEmail(principal.getName()).get();
        likeUsers.add(user);
        post.setLikeUsers(likeUsers);
        postRepository.save(post);
    }

    public PostDto findPostsById(String id, Principal principal) {
        var post = postRepository.findById(id).get();

        return PostDto.from(post);
    }

    public void addComment(String id, String commentText, Principal principal) {
        var post = postRepository.findById(id).get();
        var user = userRepository.findByEmail(principal.getName()).get();

        Comment comment = new Comment(UUID.randomUUID().toString(),
                commentText, LocalDateTime.now(), user, post);
        commentRepository.save(comment);

        var comments = post.getComments();
        comments.add(comment);
        post.setComments(comments);
        postRepository.save(post);
    }

    public List<CommentDto> findComments(String id) {
        var post = postRepository.findById(id).get();
        var comments = post.getComments();
        List<CommentDto> commentsDto = comments.stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
        return commentsDto;
    }
}
