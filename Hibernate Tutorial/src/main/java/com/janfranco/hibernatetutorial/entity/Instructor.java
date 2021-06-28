package com.janfranco.hibernatetutorial.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INSTRUCTOR_DETAIL_ID")
    private InstructorDetail instructorDetail;

    // If fetch type is lazy, when retrieving instructors, courses will load only on
    // demand
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor", cascade = { CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH })
    private List<Course> courses;

    public Instructor() {

    }

    public Instructor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void add(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }

        course.setInstructor(this);
        courses.add(course);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Instructor [Id=" + Id + ", courses=" + courses + ", email=" + email + ", instructorDetail="
                + instructorDetail + ", name=" + name + "]";
    }
}
