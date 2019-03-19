package com.mitrais.jpqi.springcarrot.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Document("transactions")
public class Transaction {

    public enum Type{
        BAZAAR, REWARD, SHARED
    }

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String from;
    private String to;
    private Basket detail_from;
    private Basket detail_to;
    private Freezer freezer_from;
    private Freezer freezer_to;
    private String description;
    private int carrot_amt;
    private LocalDateTime transaction_date;
    private boolean status;

    public Transaction(){}


    public Transaction(String id, Type type, String from, String to, Basket detail_from, Basket detail_to,
                       Freezer freezer_from, Freezer freezer_to, String description,
                       int carrot_amt, LocalDateTime transaction_date) {
        this.id = id;
        this.type = type;
        this.from = from;
        this.to = to;
        this.detail_from = detail_from;
        this.detail_to = detail_to;
        this.freezer_from = freezer_from;
        this.freezer_to = freezer_to;
        this.description = description;
        this.carrot_amt = carrot_amt;
        this.transaction_date = transaction_date;
    }

    //Getter & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFrom() { return from; }

    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }

    public void setTo(String to) { this.to = to; }

    public Basket getDetail_from() { return detail_from; }

    public void setDetail_from(Basket detail_from) { this.detail_from = detail_from; }

    public Basket getDetail_to() { return detail_to; }

    public void setDetail_to(Basket detail_to) { this.detail_to = detail_to; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCarrot_amt() {
        return carrot_amt;
    }

    public void setCarrot_amt(int carrot_amt) {
        this.carrot_amt = carrot_amt;
    }

    public LocalDateTime getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(LocalDateTime transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Freezer getFreezer_from() { return freezer_from; }

    public void setFreezer_from(Freezer freezer_from) { this.freezer_from = freezer_from; }

    public Freezer getFreezer_to() { return freezer_to; }

    public void setFreezer_to(Freezer freezer_to) { this.freezer_to = freezer_to; }
}
