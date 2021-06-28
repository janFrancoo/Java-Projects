package com.janfranco.hibernatetutorial.hibernatedemo.Review;

import com.janfranco.hibernatetutorial.entity.Course;
import com.janfranco.hibernatetutorial.entity.Instructor;
import com.janfranco.hibernatetutorial.entity.InstructorDetail;
import com.janfranco.hibernatetutorial.entity.Review;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryReviewDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class).buildSessionFactory();

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Course course = session.get(Course.class, 5);
        System.out.println(course);
        System.out.println(course.getReviews());

        session.getTransaction().commit();

        session.clear();
    }
}
