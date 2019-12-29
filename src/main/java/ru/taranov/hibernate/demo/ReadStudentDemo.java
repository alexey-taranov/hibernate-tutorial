package ru.taranov.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.demo.entity.Student;

public class ReadStudentDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // create a student object
            Student student = new Student("Daffy", "Duck", "duck@ya.ru");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println(student);
            session.save(student);

            //find out student id: primary key
            System.out.println("student. Generated id: " + student.getId());

            Student myStudent = session.get(Student.class, student.getId());
            System.out.println(myStudent);

            // commit transaction
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
