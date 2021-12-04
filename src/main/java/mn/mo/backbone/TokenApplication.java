package mn.mo.backbone;

import mn.mo.backbone.domain.Role;
import mn.mo.backbone.domain.User;
import mn.mo.backbone.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class TokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(new User(null,"manager", "manager","pass", new ArrayList<>()));
            userService.saveUser(new User(null,"admin", "admin","pass", new ArrayList<>()));
            userService.saveUser(new User(null,"user", "user","pass", new ArrayList<>()));

            userService.addRoleToUser("manager", "ROLE_MANAGER");
            userService.addRoleToUser("admin", "ROLE_ADMIN");
            userService.addRoleToUser("user", "ROLE_USER");
        };
    }

}
