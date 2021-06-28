package com.janfranco.hibernatetutorial.hibernatedemo.Review;

import com.janfranco.hibernatetutorial.entity.Course;
import com.janfranco.hibernatetutorial.entity.Instructor;
import com.janfranco.hibernatetutorial.entity.InstructorDetail;
import com.janfranco.hibernatetutorial.entity.Review;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateReviewDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class).buildSessionFactory();

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Course course = new Course("Android course");
        course.add(new Review("Great!"));
        course.add(new Review("Thx!"));
        course.add(new Review("Perfecto!"));

        session.save(course);

        session.getTransaction().commit();

        session.clear();
        factory.close();
    }
}
