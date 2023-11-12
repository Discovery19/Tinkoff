package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Task6 implements StockMarket {
    private static PriorityQueue<Stock> stocks = new PriorityQueue<>(new StockPriceComparator());

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.removeIf(stk -> stk.name().equals(stock.name()));
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }

    static class StockPriceComparator implements Comparator<Stock> {
        @Override
        public int compare(Stock s1, Stock s2) {
            return (-1) * Integer.compare(s1.price(), s2.price());
        }
    }
}

