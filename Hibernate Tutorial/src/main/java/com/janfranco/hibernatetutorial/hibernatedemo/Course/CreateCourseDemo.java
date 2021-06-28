package com.janfranco.hibernatetutorial.hibernatedemo.Course;

import com.janfranco.hibernatetutorial.entity.Course;
import com.janfranco.hibernatetutorial.entity.Instructor;
import com.janfranco.hibernatetutorial.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        Instructor instructor = session.get(Instructor.class, 4);

        Course course = new Course("React course");
        Course course2 = new Course("Java course");

        instructor.add(course);
        instructor.add(course2);

        session.save(course);
        session.save(course2);

        session.getTransaction().commit();

        session.close();
        factory.close();
    }

}
