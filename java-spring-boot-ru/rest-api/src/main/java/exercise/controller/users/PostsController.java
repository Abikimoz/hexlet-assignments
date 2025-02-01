package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users") // Базовый путь для всех маршрутов
public class PostsController {

    // Получаем список постов из Data.getPosts()
    private final List<Post> posts = Data.getPosts();

    // Метод для получения всех постов конкретного пользователя
    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable int id) {
        // Фильтруем посты, привязанные к пользователю с указанным ID
        List<Post> userPosts = posts.stream()
                .filter(post -> post.getUserId() == id) // Сравниваем userId с id из маршрута
                .collect(Collectors.toList());

        if (userPosts.isEmpty()) {
            return ResponseEntity.noContent().build(); // Возвращаем 204 No Content, если посты не найдены
        }

        return ResponseEntity.ok(userPosts); // Возвращаем 200 OK с постами пользователя
    }

    // Метод для создания нового поста для пользователя
    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int id, @RequestBody Post postRequest) {
        // Создаем новый пост
        Post newPost = new Post();
        newPost.setSlug(postRequest.getSlug());
        newPost.setTitle(postRequest.getTitle());
        newPost.setBody(postRequest.getBody());
        newPost.setUserId(id); // Привязываем пост к пользователю через userId

        // Добавляем новый пост в список
        posts.add(newPost);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPost); // Возвращаем 201 Created с новым постом
    }
}
// END
