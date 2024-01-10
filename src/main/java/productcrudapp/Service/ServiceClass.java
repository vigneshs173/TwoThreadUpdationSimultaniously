package productcrudapp.Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import productcrudapp.dao.DaoClass;
import productcrudapp.model.Student;


import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ServiceClass {
    Thread t1;
    Thread t2;
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
        return daoClass.FetchDetailsDao(id);
    }


    //private final Object lock = new Object();
//    private TransactionManager transactionManager; // assuming this is defined elsewhere
//    private DaoClass daoClass; // assuming this is defined elsewhere
//
//    public void updateService(Student student, int increment) {
//        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//
//        try {
//            synchronized (lock) {
//                Student currentStudent = daoClass.FetchDetailsDao(student.getId());
//
//                // Update roll number
//                int updateRollNo = currentStudent.getRollno() + increment;
//                currentStudent.setRollno(updateRollNo);
//                daoClass.updateDao(currentStudent);
//            }
//
//            transactionManager.commit(status);
//        } catch (Exception e) {
//            transactionManager.rollback(status);
//            throw e;
//        }
//    }
//
//    public void simulate(final Student student, final int increment) {
//        Thread t1 = new Thread(() -> {
//            try {
//                updateService(student, increment);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            try {
//                updateService(student, increment);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException ie) {
//            ie.printStackTrace();
//        }
//    }
//}
    private static final Lock lock = new ReentrantLock();
//    @Transactional
//    public void updateService(Student student) throws InterruptedException {
//        try {
//            lock.lock();
//            Student currentStudent = daoClass.FetchDetailsDao(student.getId());
//            int updateRollNo = currentStudent.getRollno() + student.getRollno();
//            currentStudent.setRollno(updateRollNo);
//            daoClass.updateDao(currentStudent);
//
//        } finally {
//            lock.unlock();
//        }
//    }
//
//}
    @Transactional
    public  void  updateService(Student student) {
        try {
            lock.lock();
            Student currentStudent = daoClass.FetchDetailsDao(student.getId());
            int updateRollNo = currentStudent.getRollno() + student.getRollno();
            currentStudent.setRollno(updateRollNo);
            System.out.println(Thread.currentThread().getName());
        }
        finally {
            lock.unlock();
        }
    }

    @Transactional
    public  void test(Student student) throws InterruptedException {
        t1 = new Thread(() -> {
            t1.currentThread().setName("one");
            updateService(student);
        });
        t2 = new Thread(() -> {
            t1.currentThread().setName("two");
            updateService(student);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
