package com.example.restapp;

public class Product {	
	int Id;
	int ProductCategoryID;
	String No;
	String Name;
	Double Price;
	
    @Override
    public String toString() {
        return this.Name;
    }	
}
