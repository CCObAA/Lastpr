package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 4,max = 30,message = "От 4 до 30 символов")
    private String  namepost;
    @Min(value = 0, message = "Значение не должно быть меньше 0")
    @Max(value = 300000, message = "Значение не должно быть больше 300")
    private float salary;
    private double maxsalary;
    private boolean paid;
    @NotNull
    private int countemploey;
    @NotNull
    private int views;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    private Collection<Employee> postonemany;

    public Post(double maxsalary, float salary, boolean paid, String namepost, int countemploey, Collection<Employee> postonemany) {
        this.maxsalary = maxsalary;
        this.paid = paid;
        this.namepost = namepost;
        this.salary = salary;
        this.countemploey = countemploey;
        this.postonemany = postonemany;
    }

    public Post() {
    }

    public Collection<Employee> getPostonemany() {
        return postonemany;
    }

    public void setPostonemany(Collection<Employee> postonemany) {
        this.postonemany = postonemany;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMaxsalary() {
        return maxsalary;
    }

    public void setMaxsalary(double maxsalary) {
        this.maxsalary = maxsalary;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getNamepost() {return namepost;}

    public void setNamepost(String namepost) {this.namepost = namepost;}

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getCountemploey() {
        return countemploey;
    }

    public void setCountemploey(int countemploey) {
        this.countemploey = countemploey;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }


}
