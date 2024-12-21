package am.azaryan.service;

import am.azaryan.entity.Lesson;
import am.azaryan.entity.User;
import am.azaryan.entity.UserType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user, MultipartFile multipartFile) throws IOException;

    List<User> findByUserType(UserType userType);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findById(int id);

    void deleteById(int id);

    List<User> findAllByUserTypeAndLesson(UserType userType,Lesson lesson);

    User update(User user,MultipartFile multipartFile) throws IOException;

    void changeUserByLesson(Lesson lesson);
}