package com.janfranco.mvctutorial.model;

import java.util.LinkedHashMap;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.janfranco.mvctutorial.util.validation.StudentNo;

public class Student {

    @NotNull(message = "is required")
    @Size(min = 3, message = "is required")
    private String firstName;

    private String lastName;

    @NotNull(message = "is required")
    @Min(value = 18, message = "Min 18 required")
    private Integer age;

    @StudentNo(value = "1", message = "Student no must starts with 1")
    private String studentNo;

    private String country;
    private String favoriteLanguage;
    private String[] operatingSystems;

    private final LinkedHashMap<String, String> countryOptions;

    public Student() {
        countryOptions = new LinkedHashMap<>();
        countryOptions.put("BR", "Brazil");
        countryOptions.put("EN", "England");
        countryOptions.put("TR", "Turkey");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public String[] getOperatingSystems() {
        return operatingSystems;
    }

    public void setOperatingSystems(String[] operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public LinkedHashMap<String, String> getCountryOptions() {
        return countryOptions;
    }
}
