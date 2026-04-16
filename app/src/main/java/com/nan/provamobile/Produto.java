package com.nan.provamobile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Produto {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    //String porque aceita numeros e letras
    private String code;

    private double price;

    private int amount;

    //construtor
    public Produto(String name, String code, double price, int amount){
        this.name = name;
        this.code = code;
        this.price = price;
        this.amount = amount;
    }
    //Metodos Getters e Setters
    //ID
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    //nome
    public String getName()
    {
        return name;
    }
    //código
    public String getCode(){return code;}
    //preço
    public double getPrice(){return price;}
    //quantidade
    public int getAmount(){return amount;} }
