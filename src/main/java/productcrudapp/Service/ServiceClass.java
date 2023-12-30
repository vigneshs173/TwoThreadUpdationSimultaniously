package productcrudapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import productcrudapp.Validation.ValidationForPostMan;
import productcrudapp.dao.DaoClass;
import productcrudapp.model.PlacedStudent;
import productcrudapp.model.Student;
import javax.validation.Valid;
import java.util.List;

@Service
public class ServiceClass {

	private final DaoClass daoClass;

	@Autowired
	public ServiceClass(DaoClass daoClass) {
		this.daoClass = daoClass;
	}
	// create
	@Transactional
	public void createStudentService(Student student) {
		daoClass.createStudentDao(student);
	}

	//get all
	@Transactional
	public List<Student> getAllStudentService() {
		return daoClass.getAllStudentDao();
	}

	//get all sorted by name
	public List<Student> getAllStudentsSortedByName() {
		return daoClass.getAllStudentsSortedByName();
	}

	//get all sorted by mark
	public List<Student> getAllStudentsSortedByMark() {
		return daoClass.getAllStudentsSortedByMark();
	}

	//get all Placed students
	public List<PlacedStudent> getAllPlacedStudents() {
		return daoClass.getAllStudentsPlacedDao();
	}


	//get by id
	@Transactional
	public Student getStudentByIdService(Long id) {
		return daoClass.getStudentByIdDao(id);
	}

	//delete
	@Transactional
	public void deleteStudentByIdService(Long id) {
		daoClass.deleteStudentByIdDao(id);
	}


	//update
	@Transactional
	public void updateStudentService(Student student, Model model) {
		daoClass.UpdateStudentDao(student);
	}

	//roll no exists or not
	public boolean isStudentRollNoExistsService(@Valid Student student) {
		return daoClass.isStudentRollNoExistsDao(student);
	}

	//id exists or not
	@Transactional
	public boolean isIdExistsInDbService(Student student) {
		return daoClass.isIdExistsInDbDao(student);
	}




	//---------------------------------------------------moved----------------------------//
//@Transactional
//public void placeStudentService(Long id) {
//	Student student = daoClass.getStudentByIdDao(id);
//	if (student != null && student.getIsExists() == 1) {
//		PlacedStudent placedStudent = new PlacedStudent();
//		placedStudent.setId(student.getId());
//		placedStudent.setName(student.getName());
//		placedStudent.setRollno(student.getRollno());
//		placedStudent.setGmail(student.getGmail());
//		placedStudent.setMark(student.getMark());
//		//placedStudent.setIsExists(student.getIsExists());
//		daoClass.saveOrUpdatePlacedStudent(placedStudent);
//		daoClass.updatePlacementStatus(id);
//	}
//}
//	@Transactional
//	public void placeStudentService(Long id) {
//		try {
//			Student student = daoClass.getStudentByIdDao(id);
//			if (student != null && student.getIsExists() == 1) {
//				PlacedStudent placedStudent = new PlacedStudent();
//				placedStudent.setId(student.getId());
//				placedStudent.setName(student.getName());
//				placedStudent.setRollno(student.getRollno());
//				placedStudent.setGmail(student.getGmail());
//				placedStudent.setMark(student.getMark());
//				daoClass.saveOrUpdatePlacedStudent(placedStudent);
//				daoClass.updatePlacementStatus(student);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}












@Transactional
	public void placeStudentService(Long id) {
		try {
			Student student = daoClass.getStudentByIdDao(id);  //submit(id)-->service-->here  // get details of id
			System.out.println("id after coming to service :"+id);
			if (student != null && student.getIsExists() == 1) {   // setting student details to placed student
				PlacedStudent placedStudent = new PlacedStudent();
				placedStudent.setId(student.getId());
				System.out.println("id for setting :" +id);
				placedStudent.setName(student.getName());
				placedStudent.setRollno(student.getRollno());
				placedStudent.setGmail(student.getGmail());
				placedStudent.setMark(student.getMark());
				daoClass.saveOrUpdatePlacedStudent(placedStudent);  // save in another table

				daoClass.updatePlacementStatus(student);			// soft delete
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}









































	//-------------------------------------------------------------postman----------------------------------------------

	//create in postman
	@Transactional
	public Student createStudentServicePostman(Student student, Long id) {
		ValidationForPostMan.validateStudent(student, daoClass, true);
		daoClass.createStudentDao(student);
		return student;
	}


	//update in postman
	@Transactional
	public Student updateStudentPostmanService(Student student) {
		ValidationForPostMan.validateStudent(student, daoClass, false);
		daoClass.UpdateStudentDao(student);
		return student;
	}

}

