package am.azaryan.controller;

import am.azaryan.entity.Lesson;
import am.azaryan.entity.User;
import am.azaryan.entity.UserType;
import am.azaryan.service.LessonService;
import am.azaryan.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class LessonController {

    private final UserService userService;
    private final LessonService lessonService;

    public LessonController(UserService userService, LessonService lessonService) {
        this.userService = userService;
        this.lessonService = lessonService;
    }

    @GetMapping("/lesson")
    public String lessonPage(ModelMap modelMap) {
        List<Lesson> lessons = lessonService.findAll();
        modelMap.put("lessons", lessons);
        return "lesson";
    }

    @GetMapping("/lesson/add")
    public String addLessonPage(ModelMap modelMap) {
        List<User> teachers = userService.findByUserType(UserType.TEACHER);
        if (teachers != null && !teachers.isEmpty()) {
            modelMap.addAttribute("teachers", teachers);
            return "addLesson";
        }
        return "addLesson";
    }

    @PostMapping("/lesson/add")
    public String addLesson(@ModelAttribute Lesson lesson, @RequestParam("teacherId") int teacherId){
        Optional<User> byId = userService.findById(teacherId);
        byId.ifPresent(lesson::setUser);
        lessonService.save(lesson);
        return "redirect:/lesson";
    }

    @GetMapping("/lesson/delete/{id}")
    public String deleteLesson(@PathVariable("id") int id) {

        lessonService.deleteById(id);
        return "redirect:/lesson";
    }

    @GetMapping("/lesson/update/{id}")
    public String updateLessonPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Lesson> byId = lessonService.findById(id);
        List<User> teachers = userService.findByUserType(UserType.TEACHER);
        if (byId.isPresent()) {
            Lesson lesson = byId.get();
            modelMap.addAttribute("lesson", lesson);
            modelMap.addAttribute("teachers", teachers);
            return "updateLesson";
        }
        return "lesson";
    }

    @PostMapping("/lesson/update")
    public String updateUser(@ModelAttribute Lesson lesson,@RequestParam("teacher.id") int id){
        Optional<User> byId = userService.findById(id);
        byId.ifPresent(lesson::setUser);
        lessonService.save(lesson);
        return "redirect:/lesson";
    }
}