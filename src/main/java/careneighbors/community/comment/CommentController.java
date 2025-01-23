package careneighbors.community.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponse createComment(@RequestBody CommentRequest rq) {
        return commentService.create(rq);
    }

    @GetMapping("/{commentId}")
    public CommentResponse readComment(@PathVariable Long commentId) {
        return commentService.read(commentId);
    }

    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody CommentRequest rq) {
        commentService.update(commentId, rq);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }
}