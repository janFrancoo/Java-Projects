package com.janfranco.hibernatetutorial.hibernatedemo.Student;

import com.janfranco.hibernatetutorial.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        Student student = new Student("Jan Franco", "poisonweeb@gmail.com");
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
    }
}
