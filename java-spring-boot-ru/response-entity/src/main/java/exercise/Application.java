package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index() {
        // Возвращаем весь список постов
        List<Post> allPosts = posts;
        // Добавляем заголовок X-Total-Count с общим количеством постов
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size() + 1))
                .body(allPosts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        Post foundPost = null;
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                foundPost = post;
                break;
            }
        }
        if (foundPost != null) {
            return ResponseEntity.ok(foundPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        // Проверка на наличие поста с таким же ID
        boolean exists = false;
        for (Post p : posts) {
            if (p.getId().equals(post.getId())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        Post foundPost = null;
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                foundPost = post;
                break;
            }
        }
        if (foundPost != null) {
            foundPost.setId(data.getId());
            foundPost.setTitle(data.getTitle());
            foundPost.setBody(data.getBody());
            return ResponseEntity.ok(foundPost);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
