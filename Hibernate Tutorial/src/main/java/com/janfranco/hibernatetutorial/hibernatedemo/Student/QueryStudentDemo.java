package com.janfranco.hibernatetutorial.hibernatedemo.Student;

import java.util.List;

import com.janfranco.hibernatetutorial.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        session = factory.getCurrentSession();
        session.beginTransaction();
        List<?> students = session.createQuery("from Student").getResultList();
        session.getTransaction().commit();
        for (Object student : students) {
            System.out.println((Student) student);
        }
    }
}
