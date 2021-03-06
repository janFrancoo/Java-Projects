package com.janfranco.hibernatetutorial.hibernatedemo.Instructor;

import com.janfranco.hibernatetutorial.entity.Instructor;
import com.janfranco.hibernatetutorial.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        Instructor instructor = new Instructor("Dr. Jan Franco", "poisonweeb@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("youtube_link/janfrancoo");
        instructor.setInstructorDetail(instructorDetail);

        session.beginTransaction();
        session.save(instructor);
        session.getTransaction().commit();
    }
}
