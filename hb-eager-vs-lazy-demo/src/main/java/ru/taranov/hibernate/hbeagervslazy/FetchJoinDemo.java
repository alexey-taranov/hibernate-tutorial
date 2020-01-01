package ru.taranov.hibernate.hbeagervslazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.taranov.hibernate.hbeagervslazy.entity.Instructor;
import ru.taranov.hibernate.hbeagervslazy.entity.InstructorDetail;

public class FetchJoinDemo {

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

            int theId = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                            + "join fetch i.courses "
                            + "where i.id=:theInstructorId",
                            Instructor.class);

            query.setParameter("theInstructorId", theId);

            Instructor instructor = query.getSingleResult();

            System.out.println("Instructor: " + instructor);

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
