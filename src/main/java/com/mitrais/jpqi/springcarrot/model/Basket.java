package com.mitrais.jpqi.springcarrot.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Document("baskets")
public class Basket {
//    @Autowired
//    SequenceService sequenceService;

    @Transient
    public static final String SEQUENCE_NAME = "basket_sequence";

    @Id
    private String id;
    private String name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @DBRef
    private Employee employee;
    private double carrot_amt;

/*
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Basket> event) {
        event.getSource().setId(sequenceService.generateSequence(Basket.SEQUENCE_NAME));
    }
*/


    //Constructors
    public Basket() {
    }

    public Basket(String id, String name, LocalDateTime created_at, LocalDateTime updated_at,
                  Employee employee, double carrot_amt) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.employee = employee;
        this.carrot_amt = carrot_amt;
    }

    public double getCarrot_amt() {
        return carrot_amt;
    }

    public void setCarrot_amt(double carrot_amt) {
        this.carrot_amt = carrot_amt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        return id.equals(basket.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
