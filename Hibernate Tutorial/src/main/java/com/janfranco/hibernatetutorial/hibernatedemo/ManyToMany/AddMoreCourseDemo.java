package com.janfranco.hibernatetutorial.hibernatedemo.ManyToMany;

import com.janfranco.hibernatetutorial.entity.Course;
import com.janfranco.hibernatetutorial.entity.Instructor;
import com.janfranco.hibernatetutorial.entity.InstructorDetail;
import com.janfranco.hibernatetutorial.entity.Review;
import com.janfranco.hibernatetutorial.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddMoreCourseDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Student student = session.get(Student.class, 2);
        System.out.println(student.getCourses());

        Course course = session.get(Course.class, 1);
        course.add(student);

        session.update(course);

        session.getTransaction().commit();

        session.clear();
        factory.close();
    }
}
