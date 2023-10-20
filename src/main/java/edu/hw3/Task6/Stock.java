package edu.hw3.Task6;

public class Stock {
    private String name;
    private int price;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " " + price;
    }
}
