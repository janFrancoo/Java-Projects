package com.janfranco.hibernatetutorial.hibernatedemo.Student;

import com.janfranco.hibernatetutorial.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        Student student = new Student("Jane Franco", "poisonweeeb@gmail.com");
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();

        session = factory.getCurrentSession();
        session.beginTransaction();
        Student fetchStudent = session.get(Student.class, student.getId());
        session.getTransaction().commit();
        System.out.println(fetchStudent);
    }
}
