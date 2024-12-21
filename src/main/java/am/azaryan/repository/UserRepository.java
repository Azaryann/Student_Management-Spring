package am.azaryan.repository;

import am.azaryan.entity.Lesson;
import am.azaryan.entity.User;
import am.azaryan.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findByUserType(UserType userType);

    List<User> findAllByUserTypeAndLesson(UserType userType, Lesson lesson);

    List<User> findByLesson(Lesson lesson);
}
