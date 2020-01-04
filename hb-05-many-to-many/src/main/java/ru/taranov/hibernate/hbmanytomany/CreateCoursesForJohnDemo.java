package ru.taranov.hibernate.hbmanytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.hbmanytomany.entity.*;

public class CreateCoursesForJohnDemo {

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

            int id = 1;

            Student student = session.get(Student.class, id);

            System.out.println("Loaded student: " + student);
            System.out.println("Course: " + student.getCourses());

            Course course1 = new Course("Rubik's cube");
            Course course2 = new Course("Atari 2600");

            course1.addStudent(student);
            course2.addStudent(student);

            session.save(course1);
            session.save(course2);

            // commit transaction
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
