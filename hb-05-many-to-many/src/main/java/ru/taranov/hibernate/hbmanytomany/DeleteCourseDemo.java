package ru.taranov.hibernate.hbmanytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.hbmanytomany.entity.*;

public class DeleteCourseDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            int id = 13;

            Course course = session.get(Course.class, id);

            System.out.println("Deleting course: " + course);

            session.delete(course);

            // commit transaction
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
