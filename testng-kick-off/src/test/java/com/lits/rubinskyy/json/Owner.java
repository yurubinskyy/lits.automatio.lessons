package com.lits.rubinskyy.json;

import java.util.List;

public class Owner {

    private String firstName;
    private int age; // 0
    // private Integer age; // null
    private boolean isMarried; // false
    private List<String> books; // collection type
    private Dog dog;
    private Object nullvalueexample;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Object getNullvalueexample() {
        return nullvalueexample;
    }

    public void setNullvalueexample(Object nullvalueexample) {
        this.nullvalueexample = nullvalueexample;
    }
}
