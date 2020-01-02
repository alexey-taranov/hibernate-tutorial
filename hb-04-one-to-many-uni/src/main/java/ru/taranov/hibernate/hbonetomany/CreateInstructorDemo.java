package ru.taranov.hibernate.hbonetomany;

import ru.taranov.hibernate.hbonetomany.entity.Course;
import ru.taranov.hibernate.hbonetomany.entity.Instructor;
import ru.taranov.hibernate.hbonetomany.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // create the objects
            Instructor instructor =
                    new Instructor("Susan", "Public", "susan.public@luv2code.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail(
                            "http://www.youtube.com",
                            "Video Games");

            // associate the objects
            instructor.setInstructorDetail(tempInstructorDetail);


            // start a transaction
            session.beginTransaction();

            //save the instructor
            //
            //NOTE: also will save instructorDetail
            //because of CascadeType.ALL
            //
            session.save(instructor);

            // commit transaction
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
