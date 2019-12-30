package ru.taranov.hibernate.hbonetoonebi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.hbonetoonebi.entity.Instructor;
import ru.taranov.hibernate.hbonetoonebi.entity.InstructorDetail;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create session
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            int theId = 2;

            InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

            System.out.println("the associated instructor: " + instructorDetail.getInstructor());

            // commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
