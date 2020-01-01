package ru.taranov.hibernate.hbeagervslazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.hbeagervslazy.entity.Instructor;
import ru.taranov.hibernate.hbeagervslazy.entity.InstructorDetail;

public class DeleteDemo {

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

            // get instructor by primary key
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);

            // delete the instructors
            if (instructor != null) {
                //NOTE: will also delete associate "details" object
                session.delete(instructor);
            }

            // commit transaction
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
