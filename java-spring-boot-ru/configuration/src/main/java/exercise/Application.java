package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    // BEGIN
    @Autowired
    private UserProperties userProperties; // Инъекция свойств из конфигурации

    // BEGIN
    @GetMapping("/admins")
    public List<String> getAdmins() {
        // Получаем список email администраторов из конфигурации
        List<String> adminEmails = userProperties.getAdmins();

        // Получаем всех пользователей из Data.getUsers()
        List<User> allUsers = Data.getUsers();

        // Фильтруем пользователей, оставляя только тех, чьи email совпадают с adminEmails
        List<String> adminNames = allUsers.stream()
                .filter(user -> adminEmails.contains(user.getEmail())) // Проверяем, является ли пользователь администратором
                .map(User::getName) // Берем только имена администраторов
                .sorted((a, b) -> b.compareTo(a)) // Сортируем имена в обратном порядке
                .toList();

        return adminNames;
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
