package ru.taranov.hibernate.hbmanytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.hbmanytomany.entity.Instructor;
import ru.taranov.hibernate.hbmanytomany.entity.InstructorDetail;
import ru.taranov.hibernate.hbmanytomany.entity.Review;
import ru.taranov.hibernate.hbmanytomany.entity.Course;

public class CreateCoursesAndReviewsDemo {

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

            Course course = new Course("how play on piano");

            course.addReview(new Review("great course"));
            course.addReview(new Review("super course"));
            course.addReview(new Review("wonderful course"));

            System.out.println("KURS");
            System.out.println(course.getReviews());
            session.save(course);

            // commit transaction
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
