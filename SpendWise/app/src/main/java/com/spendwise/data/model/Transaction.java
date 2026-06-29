package com.spendwise.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public double amount;
    public String category;   // Food, Transport, Bills, Shopping, Health, Other
    public String type;       // "expense" or "income"
    public long date;         // timestamp millis
    public String note;

    public Transaction(String title, double amount, String category, String type, long date, String note) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
        this.note = note;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getType() { return type; }
    public long getDate() { return date; }
    public String getNote() { return note; }
}
