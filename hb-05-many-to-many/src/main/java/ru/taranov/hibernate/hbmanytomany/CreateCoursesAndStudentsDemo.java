package ru.taranov.hibernate.hbmanytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.hbmanytomany.entity.*;

public class CreateCoursesAndStudentsDemo {

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

            Course course = new Course("how play on piano");

            Student student1 = new Student("John", "Doe", "john@ya.ru");
            Student student2 = new Student("Jim", "Eod", "jim@ya.ru");

            course.addStudent(student1);
            course.addStudent(student2);

            session.save(course);
            session.save(student1);
            session.save(student2);

            // commit transaction
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
