package ru.taranov.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.taranov.hibernate.demo.entity.Student;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> students = session.createQuery("from Student").getResultList();

            // display the students
            DisplayTheStudents(students);

            // query students: lastName = Doe
            students = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();

            // display the students
            System.out.println("\n Students whose have last name Doe");
            DisplayTheStudents(students);

            //query students: lastName="Doe" OR firstName="Daffy"
            students = session.createQuery("from Student s where" +
                    " s.lastName = 'Doe' OR s.firstName = 'Daffy'").getResultList();

            // display the students
            System.out.println("\n Students whose have last name Doe or first name Daffy");
            DisplayTheStudents(students);

            //query students where email like %ya.ru"
            students = session.createQuery("from Student s where email like '%ya.ru'").getResultList();

            // display the students
            System.out.println("\n Students whose have email like %ya.ru");
            DisplayTheStudents(students);

            // commit transaction
            session.getTransaction().commit();


        } finally {
            sessionFactory.close();
        }
    }

    private static void DisplayTheStudents(List<Student> students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
