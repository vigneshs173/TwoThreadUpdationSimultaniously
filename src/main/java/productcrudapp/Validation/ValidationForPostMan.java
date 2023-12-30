package productcrudapp.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import productcrudapp.Service.ServiceClass;
import productcrudapp.dao.DaoClass;
import productcrudapp.model.Student;
import java.util.ArrayList;
import java.util.List;

public class ValidationForPostMan {
    @Autowired
    ServiceClass serviceClass;
    public static void validateStudent(Student student, DaoClass daoClass,boolean includeExistingCheck) {
        List<String> errorMessages = new ArrayList<>();
        if (StringUtils.isEmpty(student.getName())) {
            errorMessages.add("Name must not be null or empty.");
        }
        if (!student.getName().matches("^[a-zA-Z\\s]*$")) {
            errorMessages.add("Invalid input for name.");
        }
        if (StringUtils.isEmpty(student.getRollno())) {
            errorMessages.add("Roll number must not be null or empty.");
        } else if (!String.valueOf(student.getRollno()).matches("[0-9]+")) {
            errorMessages.add("Invalid input for roll number.");
        }
        if (StringUtils.isEmpty(student.getGmail())) {
            errorMessages.add("Gmail must not be null or empty.");
        } else if (!student.getGmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            errorMessages.add("Invalid input for gmail.");
        }
        if (StringUtils.isEmpty(student.getMark())) {
            errorMessages.add("Mark must be initialized.");
        }
        if (includeExistingCheck &&daoClass.isStudentRollNoExistsDao(student)) {
            errorMessages.add("Student already exists.");
        }
        if (!errorMessages.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errorMessages));
            // extends runtime Exception
            // if error throws Exception ----> str are join (append in another code)
        }


    }
}
