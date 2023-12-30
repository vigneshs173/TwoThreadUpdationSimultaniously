package productcrudapp.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import productcrudapp.model.PlacedStudent;
import productcrudapp.model.Student;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoClass {

    private List<Student> studentList = new ArrayList<>();

    private final SessionFactory sessionFactory;
    public DaoClass(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //create
    public void createStudentDao(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(student);
            tx.commit();
        }
    }
    //getall
    public List<Student> getAllStudentDao() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Student where isExists=1", Student.class).getResultList(); //result-->list
        }
    }

        //sortByName
    public List<Student> getAllStudentsSortedByName() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Student s WHERE s.isExists = 1 ORDER BY s.name";
            return session.createQuery(hql, Student.class).getResultList();
        }
    }

    //sortByMark
    public List<Student> getAllStudentsSortedByMark() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Student s WHERE s.isExists = 1 ORDER BY s.mark";

            return session.createQuery(hql, Student.class).getResultList();
        }
    }

    //PlacedStudents
    public List<PlacedStudent> getAllStudentsPlacedDao() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM PlacedStudent where isExists=1";
            return session.createQuery(hql, PlacedStudent.class).getResultList();
        }
    }
    //
    //GetById
    public Student getStudentByIdDao(Long id) {
        System.out.println("id inside the dao :" +id);
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        }
    }
//    public Student getStudentByIdDao(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            String hql="FROM Student WHERE id = :id AND isExists = 1"
//            Query<Student> query=session.createQuery(hql,Student.class);
//            query.setParameter("id",id);
//            return query.uniqueResult();
//        }
//    }

    //update
    public void UpdateStudentDao(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(student);
            tx.commit();
        }
    }

    //delete soft
    public Student deleteStudentByIdDao(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Student student = session.get(Student.class, id); // id provided for delete (gets)
                if (student != null) {
                    student.setIsExists(0);
                    session.update(student);
                    tx.commit();
                    return student;
                } else {
                    return null;
                }
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

//RollNo exists
public boolean isStudentRollNoExistsDao(Student student) {
    try (Session session = sessionFactory.openSession()) {
        List<Student> resultList = session.createQuery("from Student where rollno = :rollno", Student.class)
                .setParameter("rollno", student.getRollno())
                .list();
        return !resultList.isEmpty();   //true if not empty ~ vice-versa
        //(return resultList.size() > 0);
    }


}

//     id exists or not
//    public boolean isIdExistsInDbDao(Student student) {
//        try {
//            Long getId = student.getId();
//            Student student1 = sessionFactory.getCurrentSession().get(Student.class, getId);
//            return student1 != null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

//    public boolean isIdExistsOrNot(Student student)
//    {
//        Long getId=student.getId();
//        Session session=sessionFactory.openSession();
//        session.get(Student.class,getId);
//    }

public boolean isIdExistsInDbDao(Student student) {
        Long getID=student.getId();
        Session session=sessionFactory.openSession();
     Student student1=session.get(Student.class,getID);
     session.close();
     return student1!=null;

}



    //-------------------------------------------------------move-------------------------------------------//




public void saveOrUpdatePlacedStudent(PlacedStudent placedStudent) {
    try (Session session = sessionFactory.openSession()) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(placedStudent); // handling already existing  values
        tx.commit();
    }
}
public void updatePlacementStatus(Student student) {
    if (student != null) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            student.setIsExists(0);   //id is present inside the student
            session.update(student);
            tx.commit();
        }
    }
}
//---------------------------------------------------------------------------------------------------------------//
//    private Session getCurrentSession() {  //creates a new if it doesn't have
//        return sessionFactory.getCurrentSession();
//    }
//    public void saveOrUpdatePlacedStudent(PlacedStudent placedStudent) {
//       Session session=sessionFactory.openSession();
//        session.saveOrUpdate(placedStudent);               // getCurrentSession create new if not exists
//        getCurrentSession().saveOrUpdate(placedStudent);
//    }
//    public void updatePlacementStatus(Student student) {
//        if (student != null) {
//            student.setIsExists(0);
//            getCurrentSession().update(student);
//        }
//    }






    public boolean isIdPresents(Student student)
    {
        Long ID=student.getId();
        Session session=sessionFactory.openSession();
        Student student1=session.get(Student.class,ID);
        return student1!=null;
    }

    public boolean isRollNoPresent(Student student)
    {
        int Rollno=student.getRollno();
        Session session=sessionFactory.openSession();
        List<Student> studentList1=session.createQuery("FROM student where rollno=:rollno").setParameter("rollno",student.getRollno()).list();
        return !studentList1.isEmpty();
    }














}
