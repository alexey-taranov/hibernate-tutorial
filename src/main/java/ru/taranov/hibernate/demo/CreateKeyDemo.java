package ru.taranov.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.demo.entity.Student;

public class CreateKeyDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // create 3 student object
            Student student1 = new Student("John", "Doe", "john@ya.ru");
            Student student2 = new Student("Ron", "Hill", "ron@ya.ru");
            Student student3 = new Student("Dan", "Jackson", "dan@ya.ru");

            // start a transaction
            session.beginTransaction();

            // save the student object
            session.save(student1);
            session.save(student2);
            session.save(student3);

            // commit transaction
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
