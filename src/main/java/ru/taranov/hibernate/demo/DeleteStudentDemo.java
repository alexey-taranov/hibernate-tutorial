package ru.taranov.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();
            int studentId = 1;

            Student myStudent = session.get(Student.class, studentId);

            session.delete(myStudent);

            // delete student with id=2
            session.createQuery("delete from Student where id = 2").executeUpdate();


            // commit transaction
            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
