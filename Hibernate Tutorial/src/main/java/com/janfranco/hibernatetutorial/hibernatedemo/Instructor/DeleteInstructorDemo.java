package com.janfranco.hibernatetutorial.hibernatedemo.Instructor;

import com.janfranco.hibernatetutorial.entity.Instructor;
import com.janfranco.hibernatetutorial.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();
        Instructor instructor = session.get(Instructor.class, 1);
        if (instructor != null) {
            session.delete(instructor);
        }
        session.getTransaction().commit();

        session.close();
    }
}
