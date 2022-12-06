package com.example.demo.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class cleaningScheduele {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date Date_cleaning;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Employee employees;

    public cleaningScheduele(Date date_cleaning, Employee employees) {
        Date_cleaning = date_cleaning;
        this.employees = employees;
    }

    public cleaningScheduele() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_cleaning() {
        return Date_cleaning;
    }

    public void setDate_cleaning(Date date_cleaning) {
        Date_cleaning = date_cleaning;
    }

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }
}
