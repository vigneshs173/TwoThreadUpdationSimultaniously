package productcrudapp.Service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import productcrudapp.dao.DaoClass;
import productcrudapp.model.Student;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class ServiceClass {
    private static final Lock lock = new ReentrantLock();
    private final PlatformTransactionManager transactionManager;
    private final DaoClass daoClass;

    public ServiceClass(PlatformTransactionManager transactionManager, DaoClass daoClass) {
        this.transactionManager = transactionManager;
        this.daoClass = daoClass;
    }

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public String saveDetailsService(Student student) {
        daoClass.saveDetailsDao(student);
        return null;
    }

    @Transactional
    public List<Student> GetDetailsService() {
        return daoClass.GetDetailsDao();
    }

    @Transactional
    public Student FetchDetailsService(Long id) {
        return daoClass.fetchDetailsDao(id);
    }

    @Transactional
    public void updateService(Student student) {
        try {
            lock.lock();
            Student currentStudent = daoClass.fetchDetailsDao(student.getId());
            int updateRollNo = currentStudent.getRollno() + student.getRollno();
            currentStudent.setRollno(updateRollNo);
            daoClass.updateDao(currentStudent);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void test(Student student) {
        Thread t1 = new Thread(() -> updateService(student));

        System.out.println("after t1 method " + student);
        Thread t2 = new Thread(() -> updateService(student));

        System.out.println("after t2 method " + student);

        t1.start();
        t2.start();

    }
}
