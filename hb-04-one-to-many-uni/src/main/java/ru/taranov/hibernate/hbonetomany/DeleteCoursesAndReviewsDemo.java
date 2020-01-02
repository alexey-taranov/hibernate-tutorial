package ru.taranov.hibernate.hbonetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.hbonetomany.entity.Course;
import ru.taranov.hibernate.hbonetomany.entity.Instructor;
import ru.taranov.hibernate.hbonetomany.entity.InstructorDetail;
import ru.taranov.hibernate.hbonetomany.entity.Review;

public class DeleteCoursesAndReviewsDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            int id = 10;
            Course course = session.get(Course.class, id);
            
            System.out.println("Delete: " + course);
            System.out.println(course.getReviews());

            session.delete(course);

            // commit transaction
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
