package com.janfranco.hibernatetutorial.hibernatedemo.ManyToMany;

import com.janfranco.hibernatetutorial.entity.Course;
import com.janfranco.hibernatetutorial.entity.Instructor;
import com.janfranco.hibernatetutorial.entity.InstructorDetail;
import com.janfranco.hibernatetutorial.entity.Review;
import com.janfranco.hibernatetutorial.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Course course = new Course("Swift course");
        session.save(course);

        Student student = new Student("Jane Franco", "selen.aksoy@hotmail.com");
        Student student2 = new Student("Jane Francooooo", "maniac.aksoy@hotmail.com");

        course.add(student);
        course.add(student2);

        session.save(student);
        session.save(student2);

        session.getTransaction().commit();

        session.clear();
        factory.close();
    }
}
