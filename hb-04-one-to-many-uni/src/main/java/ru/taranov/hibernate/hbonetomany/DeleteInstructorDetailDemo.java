package ru.taranov.hibernate.hbonetomany;

import ru.taranov.hibernate.hbonetomany.entity.Instructor;
import ru.taranov.hibernate.hbonetomany.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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

            // now let's delete the instructor detail
            instructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(instructorDetail);

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
