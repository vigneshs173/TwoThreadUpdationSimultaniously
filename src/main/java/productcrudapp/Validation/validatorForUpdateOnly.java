package productcrudapp.Validation;

import org.springframework.util.StringUtils;
import productcrudapp.model.Student;

public class validatorForUpdateOnly {

    public static String validateStudentForUpdate(Student AlrdyexistingStudent, Student student) {
        StringBuilder errorMsg = new StringBuilder();


        if (AlrdyexistingStudent.getRollno() == student.getRollno()) { //alrdy vs newly
            if (StringUtils.isEmpty(student.getName())) {
                errorMsg.append("Name must not be null or empty. ");
            } else if (!student.getName().matches("^[a-zA-Z\\s]*$")) {
                errorMsg.append("Invalid input for name. ");
            }

            if (StringUtils.isEmpty(String.valueOf(student.getRollno()))) {
                errorMsg.append("Roll number must not be null or empty. ");
            } else if (!String.valueOf(student.getRollno()).matches("[0-9]+")) {
                errorMsg.append("Invalid input for roll number. ");
            }

            if (StringUtils.isEmpty(student.getGmail())) {
                errorMsg.append("Gmail must not be null or empty");
            } else if (!student.getGmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                errorMsg.append("Invalid input for gmail. ");
            }

            if (StringUtils.isEmpty(String.valueOf(student.getMark()))) {
                errorMsg.append("Mark mustn't be null ");
            } else if (!(student.getMark() != null && student.getMark() > 0 && student.getMark() <= 100)) {
                errorMsg.append("Mark must be from 0 to 100 only");
            }
        } else if (!String.valueOf(student.getMark()).matches("[0-9\\s]+")){
                errorMsg.append("Mark mustn't be null ");
            }
        else{
            errorMsg.append("Roll number cannot be changed during update");   //when OldRoll!=NewRoll
        }

        return errorMsg.toString();
    }
}
