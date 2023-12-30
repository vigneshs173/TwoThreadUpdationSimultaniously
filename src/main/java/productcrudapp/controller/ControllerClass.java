package productcrudapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import productcrudapp.Service.ServiceClass;
import productcrudapp.dao.DaoClass;
import productcrudapp.model.PlacedStudent;
import productcrudapp.model.Student;
import productcrudapp.Validation.validatorForUpdateOnly;

import javax.validation.Valid;
import java.util.List;
@Controller
public class ControllerClass {

    private final ServiceClass serviceClass;

    @Autowired
    public ControllerClass(ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Autowired
    DaoClass daoClass;

    @GetMapping("/insert")  //insert
    public String showInsertForm(Model model) {
        model.addAttribute("student", new Student());
        return "insert";
    }

    @PostMapping("/insert") //insert
    public String insertProductController(@ModelAttribute("student") @Valid Student student, Errors errors, Model model) {
        if (errors.hasErrors()) {    // if error ---> goes to below
            return "insert";
        }
        if (serviceClass.isStudentRollNoExistsService(student)) {  //rollNo==rollNo----> error
            model.addAttribute("isStudentExistsWhileInserting", "Student values already exist in the database");
            return "insert";
        } else {
            serviceClass.createStudentService(student);
            return "redirect:/list";
        }
    }

    // list all
    @GetMapping("/list")
    public String getAllStudents(Model model) {
        List<Student> students = serviceClass.getAllStudentService();
        model.addAttribute("students", students);
        return "list";
    }


    // sort by name
    @GetMapping("list/name")
    public String getSortedByName(Model model) {
        List<Student> sortedStudents = serviceClass.getAllStudentsSortedByName();
        model.addAttribute("sortedStudents", sortedStudents);
        return "singleUser";
    }

    //sort by mark
    @GetMapping("list/mark")
    public String getSortedByMark(Model model) {
        List<Student> sortedByMarkStudents = serviceClass.getAllStudentsSortedByMark();
        model.addAttribute("sortedByMarkStudents", sortedByMarkStudents);
        return "mark";
    }

    //  Placed Students
    @GetMapping("/placedStudents")
    public String getPlacedStudents(Model model) {
        List<PlacedStudent> PlacedStudents = serviceClass.getAllPlacedStudents();
        model.addAttribute("PlacedStudents", PlacedStudents);
        return "placedStudent";
    }

    //update
    @GetMapping("/update")
    public String showEditPage(Model model) {
        model.addAttribute("student", new Student());         // empty or default obj----> form fields x entity fields(pre-fill existing data )
        return "update";


    }

    //fetching(update)
    @PostMapping("/fetchStudent")
    public String fetchStudent(@RequestParam Long id, Model model) {
        Student studentToCheck = new Student();
        studentToCheck.setId(id);
        if (serviceClass.isIdExistsInDbService(studentToCheck)) {  //id check
            Student fetchedStudent = serviceClass.getStudentByIdService(id); //getting id + its details to fetch
            model.addAttribute("student", fetchedStudent);  //"student"  contains the details
            return "update";
        } else {
            model.addAttribute("errorMessage", "Student with ID: " + id + " not found");
            return "update";
        }
    }

    //update
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student, Model model) {
        System.out.println("i am checking the std instance  :"+student);
        System.out.println(student.toString());
        if (serviceClass.isIdExistsInDbService(student)) { // id exists or not
            Student existingStudent = serviceClass.getStudentByIdService(student.getId()); // getting that id

            String errorMessages = validatorForUpdateOnly.validateStudentForUpdate(existingStudent, student);            // automatically validate for existing student

            if (errorMessages.length() > 0) {   //validation msg presents
                model.addAttribute("isStudentExistsWhileUpdating", errorMessages);
                return "update";
            } else {
                serviceClass.updateStudentService(student, model);
                return "redirect:/list";
            }
        } else {
            model.addAttribute("isStudentExistsWhileUpdating", "Student with the provided ID does not exist in the database");
            return "update";
        }
    }


    // delete
    @GetMapping("/delete")
    public String deleteIt() {
        return "delete";
    }


//    @PostMapping("/delete")
//    public String deleteItt(@RequestParam Long id, Model model) {
//        if (id != null) {
//            Student studentToDelete = new Student();
//            studentToDelete.setId(id);
//                if (serviceClass.isIdExistsInDbService(studentToDelete)) {
//                    serviceClass.deleteStudentByIdService(id);
//                    return "redirect:/list";
//                } else {
//                    model.addAttribute("deleteForm", "id is not present");
//                    return "delete";
//                }
//        } else {
//            model.addAttribute("deleteForm", "Id cannot be null");
//            return "delete";
//        }
//}

    // delete
    @PostMapping("/delete")
    public String deleteIt(@RequestParam Long id, Model model) {
        if (id == null) {
            model.addAttribute("deleteForm", "id " + id + "is cannot be null");
        }
        Student studToDelete = new Student();
        studToDelete.setId(id);
        if (serviceClass.isIdExistsInDbService(studToDelete))  // id check
        {
            serviceClass.deleteStudentByIdService(id);   //delete
            return "redirect:/list";
        } else {
            model.addAttribute("deleteForm", "id " + id + "is not present");
            return "delete";
        }
    }

    //-------------------------------moving--------------------------------------//

    @GetMapping("/moving")
    public String MoveStudent() {
        return "move";
    }
//
//    @PostMapping("/move")
//    public String placeStudentController(@RequestParam("id") Long id) {
//        serviceClass.placeStudentService(id);
//        return "redirect:/list";
//    }



















    @PostMapping("/move")
    public String placeStudentController(@RequestParam("id") Long id,Model model) {
        System.out.println("id before creating new student obj :"+id);
        Student studentIdCheck = new Student();
        studentIdCheck.setId(id);
        System.out.println("id after creating new student obj :"+id);
        if (serviceClass.isIdExistsInDbService(studentIdCheck)) {  //id check
            System.out.println("checking the id if exists  :"+id);
            serviceClass.placeStudentService(id);  //id is passed & calling service
            System.out.println("id after passing to service  :"+id);
            return "redirect:/list";
        } else {
            model.addAttribute("obj", "id" + id + "is not present");
            return "move";
        }
    }



















    //--------------------------------------------postman----------------------------------------------------//
    // get id postman
    @GetMapping("/lists/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {  // message+str so--->? used
        try {
            Student student = serviceClass.getStudentByIdService(id);

            if (student != null && student.getIsExists() == 0) {
                throw new RuntimeException("Do not try to get this again");
            }
            return ResponseEntity.ok(student);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }


    // insert in postman
    @PostMapping("/postForPostman")
    public ResponseEntity<Object> createStudentPostmanController(@RequestBody Student student, Long id) {
        try {
            Student result = serviceClass.createStudentServicePostman(student, id);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Data already exists");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    //update in postman
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateStudentPostmanController(@PathVariable Long id,@RequestBody Student student) {
        try {
            student.setId(id);
            Student result = serviceClass.updateStudentPostmanService(student);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Catch any unexpected exception and return a generic error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    //delete with id postman
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteStudentByIdPostmanController(@PathVariable Long id)
    {
        serviceClass.deleteStudentByIdService(id);
        return "deleted";
    }
}