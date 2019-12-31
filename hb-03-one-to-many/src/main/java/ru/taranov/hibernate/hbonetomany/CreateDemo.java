package ru.taranov.hibernate.hbonetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.hbonetomany.entity.Instructor;
import ru.taranov.hibernate.hbonetomany.entity.InstructorDetail;

public class CreateDemo {

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
            // create the objects
            Instructor instructor = new Instructor("Chad", "Darby", "chad@ya.ru");
            InstructorDetail instructorDetail = new InstructorDetail("youtube.com/123","write code");

            //associate the objects
            instructor.setInstructorDetail(instructorDetail);

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
            sessionFactory.close();
        }
    }
}
