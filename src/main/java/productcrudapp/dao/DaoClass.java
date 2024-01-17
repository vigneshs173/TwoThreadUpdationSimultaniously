package productcrudapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import productcrudapp.model.Student;

import java.util.List;

@Repository
public class DaoClass {
    @Autowired
    protected SessionFactory sessionFactory;

    public void saveDetailsDao(Student student)
    {
        Session session=sessionFactory.getCurrentSession();
        session.save(student);

    }
    public List<Student> GetDetailsDao()
    {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("From Student", Student.class).getResultList();
    }

    public Student fetchDetailsDao(Long id)
    {
        Session session= sessionFactory.getCurrentSession();
        String hql = "FROM Student Where id=:id";
        Student st = (Student) session.createQuery(hql,Student.class).
                setParameter("id",id).
                getSingleResult();
        return st;
    }

    public void updateDao(Student student)
    {
        Session session= sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(student);
        tx.commit();
        session.close();
    }

}
