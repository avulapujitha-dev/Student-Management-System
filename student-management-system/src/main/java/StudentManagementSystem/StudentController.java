package StudentManagementSystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    private final List<String> students = new ArrayList<>();

    // View Students
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", students);
        return "index";
    }

    // Add Student
    @PostMapping("/add")
    public String addStudent(@RequestParam String name) {
        if (!name.trim().isEmpty()) {
            students.add(name.trim());
        }
        return "redirect:/";
    }

    // Delete Student
    @PostMapping("/delete")
    public String deleteStudent(@RequestParam int index) {
        if (index >= 0 && index < students.size()) {
            students.remove(index);
        }
        return "redirect:/";
    }

    // Search Student
    @PostMapping("/search")
    public String searchStudent(@RequestParam String name, Model model) {

        model.addAttribute("students", students);

        if (students.contains(name.trim())) {
            model.addAttribute("message", "Student Found!");
        } else {
            model.addAttribute("message", "Student Not Found!");
        }

        return "index";
    }

    // Update Student
    @PostMapping("/update")
    public String updateStudent(
            @RequestParam int index,
            @RequestParam String newName) {

        if (index >= 0 && index < students.size()
                && !newName.trim().isEmpty()) {

            students.set(index, newName.trim());
        }

        return "redirect:/";
    }
}