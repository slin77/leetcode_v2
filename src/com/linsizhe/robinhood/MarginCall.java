package com.linsizhe.robinhood;

import org.w3c.dom.html.HTMLObjectElement;

import java.util.*;

public class MarginCall {

    class Holding {
        String symbol;
        int price;
        int amount;
        Holding cola;

        public Holding(String symbol, int price, int amount) {
            this.price = price;
            this.symbol = symbol;
            this.amount = amount;
        }
    }

    HashMap<String, Holding> symb2Holding;
    int cashAmount;
    PriorityQueue<Holding> priceQueue;

    public MarginCall() {
        cashAmount = 1000;
        symb2Holding = new HashMap<>();
        priceQueue = new PriorityQueue<>(Comparator.<Holding>comparingInt(x -> x.price).reversed().thenComparing(x -> x.symbol));
    }

    void execute(String[] trade) {
         String symbol = trade[1];
         int quantity = Integer.valueOf(trade[3]);
         int price = Integer.valueOf(trade[4]);
         execute(symbol, quantity, price, trade[2]);
    }

    void execute(String symbol, int quantity, int price, String type) {
        Holding holding;
        if (!symb2Holding.containsKey(symbol)) {
            holding = new Holding(symbol, price, 0);
            symb2Holding.put(symbol, holding);
            priceQueue.add(holding);
        } else {
            holding = symb2Holding.get(symbol);
        }
        if (type.equals("B")) {
            holding.amount += quantity;
            cashAmount -= quantity * price;
        } else {
            holding.amount -= quantity;
            cashAmount += quantity * price;
        }
        holding.price = price;
    }

    void makeUp() {
        while (cashAmount < 0 && !priceQueue.isEmpty()) {
            Holding h = priceQueue.peek();
            int amount = h.amount;
            int totalValue = amount * h.price;
            int k = Math.min(totalValue, -cashAmount);
            int delta = (int) Math.ceil((double) k / h.price);
            if (k == totalValue) {
                priceQueue.poll();
            }
            h.amount -= delta;
            cashAmount += delta * h.price;
        }
    }

    void makeUpWithCol() {

        List<Holding> list = new ArrayList<>(symb2Holding.values());
        Collections.sort(list, Comparator.<Holding>comparingInt(x -> x.price).reversed().thenComparing(x -> x.symbol));

        for (Holding h : list) {
            int amount = h.amount;
            String cola = h.symbol + "O";
            if (symb2Holding.containsKey(cola)) {
                amount = h.amount - symb2Holding.get(cola).amount;
            }
            if (amount <= 0) continue;
            int totalVal = amount * h.price;
            int k = Math.min(totalVal, -cashAmount);
            int delta = (int) Math.ceil((double) k / h.price);
            h.amount -= delta;
            cashAmount += delta * h.price;
            if (cashAmount >= 0) return;
        }
    }

    public void getUserProf(String[][] trades) {
        for (String[] trade : trades) {
            execute(trade);
        }
    }

    public void getUserProfWithMC(String[][] trades) {
        for (String[] trade : trades) {
            execute(trade);
            if (trade[2].equals("B") && cashAmount < 0) {
                makeUp();
            }
        }
    }

    public void getUserProfWithMCCol(String[][] trades) {
        for (String[] trade : trades) {
            execute(trade);
            if (trade[2].equals("B") && cashAmount < 0) {
                makeUpWithCol();
            }
        }
    }

    public void print() {
        for (String str : symb2Holding.keySet()) {
            System.out.println(str + ": " + symb2Holding.get(str).amount);
        }
        System.out.println("Cash: " + cashAmount);
    }

    public static void main(String[] args) {
        MarginCall mc1 = new MarginCall();
        String[][] orders1 = new String[][] {
                {"1", "AAPL", "B", "10", "10"},
                {"3", "GOOG", "B", "20", "5"},
                {"10", "AAPL", "S", "5", "15"}
        };
        mc1.getUserProf(orders1);
        mc1.print();

        System.out.println("case 2:");
        MarginCall mc2 = new MarginCall();
        String[][] orders2 = {
                {"1", "AAPL", "B", "10", "10"},
                {"3", "GOOG", "B", "20", "5"},
                {"4", "  FB", "B", "5", "12"},
                {"3", "GOOG", "S", "3", "8"},
                {"3", "GOOG", "B", "5", "10"},
                {"10", "AAPL", "S", "5", "15"}
        };
        mc2.getUserProf(orders2);
        mc2.print();


        System.out.println("case 3:");
        MarginCall mc3 = new MarginCall();
        String[][] orders3 = {
                {"1", "AAPL", "B", "5", "100"},
                {"2", "ABPL", "B", "5", "100"},
                {"3", "AAPL", "S", "2", "80"},
                {"4", "ABPL", "S", "2", "80"},
                // has tie on price, take alpha first
                {"5", "GOOG", "B", "15", "30"}
        };
        mc3.getUserProfWithMC(orders3);
        mc3.print();

        System.out.println("case Q3 0:");
        MarginCall mc4 = new MarginCall();
        String[][] orders4 = {
                {"1", "AAPL", "B", "5", "100"},
                {"2", "GOOG", "B", "5", "75"},
                {"3", "AAPLO", "B", "5", "50"}
        };
        mc4.getUserProfWithMCCol(orders4);
        mc4.print();

    }



}
