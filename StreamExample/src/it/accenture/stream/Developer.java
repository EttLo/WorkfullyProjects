package it.accenture.stream;

import java.time.LocalDate;
import java.time.Period;

public class Developer {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String favouriteLanguage;
    private int yearsOfExperience;
    private Sex sex;
    private double salary;

    public Developer(long id, String firstName, String lastName, LocalDate birthDate, String favouriteLanguage,
            int yearsOfExperience, Sex sex, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.favouriteLanguage = favouriteLanguage;
        this.yearsOfExperience = yearsOfExperience;
        this.sex = sex;
        this.salary = salary;
    }

    public boolean isOlderThan(int age) {
        return this.getAge() > age;
    }

    public int getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFavouriteLanguage() {
        return favouriteLanguage;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public Sex getSex() {
        return sex;
    }

    public double getSalary() {
        return salary;
    }
}
