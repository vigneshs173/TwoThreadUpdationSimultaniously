package productcrudapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    public Student FetchDetailsDao(Long id)
    {
        Session session= sessionFactory.getCurrentSession();
        return session.get(Student.class,id);
    }

    public Student updateDao(Student student)
    {
        Session session= sessionFactory.getCurrentSession();
        session.update(student);
        return student;
    }

}
