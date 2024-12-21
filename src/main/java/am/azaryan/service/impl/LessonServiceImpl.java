package am.azaryan.service.impl;

import am.azaryan.entity.Lesson;
import am.azaryan.repository.LessonRepository;
import am.azaryan.service.LessonService;
import am.azaryan.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final UserService userService;

    public LessonServiceImpl(LessonRepository lessonRepository, UserService userService) {
        this.lessonRepository = lessonRepository;
        this.userService = userService;
    }

    @Override
    public Lesson save(Lesson lesson){
        return lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> findById(int id) {
        return lessonRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        Optional<Lesson> byId = findById(id);
        if (byId.isPresent()) {
            Lesson lesson = byId.get();
            userService.changeUserByLesson(lesson);
        }
        lessonRepository.deleteById(id);
    }

}