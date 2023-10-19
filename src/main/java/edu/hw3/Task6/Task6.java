package edu.hw3.Task6;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Task6 implements StockMarket{
    private Task6(){
    }
    private static PriorityQueue<Stock> stocks = new PriorityQueue<>();
    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }

    public static void main(String[] args) {
        stocks.add(new Stock("A", 10));
        stocks.add(new Stock("B", 110));
        stocks.add(new Stock("12A", 1));
        stocks.add(new Stock("13A", 1000));

    }
}

