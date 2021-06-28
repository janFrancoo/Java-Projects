package com.janfranco.hibernatetutorial.hibernatedemo.Course;

import com.janfranco.hibernatetutorial.entity.Course;
import com.janfranco.hibernatetutorial.entity.Instructor;
import com.janfranco.hibernatetutorial.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class QueryInstructorDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        /*
         * Instructor instructor = session.get(Instructor.class, 4); for (int i = 0; i <
         * instructor.getCourses().size(); i++)
         * System.out.println(instructor.getCourses().get(i).getTitle());
         */

        // Courses propery is on lazy type
        // Above, in first fetch, courses propery will not be loaded
        // When demading instructor.courses(), courses will be loaded

        // If session is closed and later courses demanded -> exception
        // To resolve this issue -> HQL ->

        Query<Instructor> query = session.createQuery(
                "select i from Instructor i join fetch i.courses where i.id=:theInstructorId", Instructor.class);
        query.setParameter("theInstructorId", 4);

        // Nothing different from Eager loading but with HQL we can specify limit or any
        // other option

        Instructor instructor = query.getSingleResult();
        System.out.println(instructor.getId() + " " + instructor.getEmail());
        System.out.println(instructor.getInstructorDetail().getYoutubeChannel());
        System.out.println(instructor.getCourses().size());

        session.getTransaction().commit();

        session.close();
        factory.close();
    }
}
