package com.janfranco.hibernatetutorial.hibernatedemo.Instructor;

import com.janfranco.hibernatetutorial.entity.Instructor;
import com.janfranco.hibernatetutorial.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryInstructorDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();
        InstructorDetail instructorDetail = session.get(InstructorDetail.class, 2);
        System.out.println(instructorDetail.getInstructor());
        session.getTransaction().commit();
    }
}
