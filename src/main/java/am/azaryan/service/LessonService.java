package am.azaryan.service;

import am.azaryan.entity.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {
    Lesson save(Lesson lesson);
    List<Lesson> findAll();
    Optional<Lesson> findById(int id);
    void deleteById(int id);
}
