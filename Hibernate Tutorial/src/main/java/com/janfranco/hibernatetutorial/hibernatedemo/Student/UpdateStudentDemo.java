package com.janfranco.hibernatetutorial.hibernatedemo.Student;

import com.janfranco.hibernatetutorial.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        session = factory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, 2);
        student.setEmail("jan@janfranco.com");
        session.getTransaction().commit();
    }
}
