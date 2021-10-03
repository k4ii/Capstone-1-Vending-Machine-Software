package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {

    private int quantity = 5;
    private double balance = 0;
    NumberFormat nf = NumberFormat.getCurrencyInstance();


    Map<String, Product> items = new LinkedHashMap<>();
    BufferedReader br = null;

    public Map<String, Product> inventory() {
        String path2 = "vendingmachine.csv";
        File input = new File(path2);

        try {

            br = new BufferedReader(new FileReader(input));
            String line = null;
            while ((line = br.readLine()) != null) {
                List<String[]> item = new ArrayList<>();
                String[] fileLine = line.split("\\|");
                item.add(fileLine);
                String slot = fileLine[0];
                String price = fileLine[2];
                String name = fileLine[1];
                String type = fileLine[3];

                if (type.equals("Chip")) {
                    Product chip = new Chip(name, Double.parseDouble(price), 5);
                    items.put(slot, chip);

                } else if (type.equals("Drink")) {
                    Product drink = new Drink(name, Double.parseDouble(price), 5);
                    items.put(slot, drink);
                } else if (type.equals("Gum")) {
                    Product gum = new Gum(name, Double.parseDouble(price), 5);
                    items.put(slot, gum);
                } else if (type.equals("Candy")) {
                    Product candy = new Candy(name, Double.parseDouble(price), 5);
                    items.put(slot, candy);
                }
            }

        } catch (Exception e) {
            System.err.println("Invalid file.");
            System.out.println("==============================");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return items;
    }


    public void feedMoney(double amount) {
        if (amount == 1.0 || amount == 2.0 || amount == 5.0 || amount == 10.0) {
            balance += amount;
            System.out.println("AMOUNT PROVIDED: " + nf.format(getBalance()));
        } else {
            System.out.println("Invalid amount provided.");
            System.out.println("==============================");
        }
        logger("MONEY FED " + nf.format(amount) + " " + nf.format(getBalance()));
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getQuantity() {
        return quantity;
    }

    public Map<String, Product> getItems() {
        return items;
    }


    public void returnChange() {
        String[] coinsName = new String[]{"Quarter(s)", "Dime(s)", "Nickel(s)"};
        Double[] coins = new Double[]{0.25, 0.10, 0.05};
        Integer[] coinTotals = new Integer[]{0, 0, 0};
        BigDecimal preciseChange = BigDecimal.valueOf(getBalance()).setScale(2, RoundingMode.HALF_UP);


        for (int i = 0; i < coins.length; i++) {
            while (preciseChange.compareTo(BigDecimal.valueOf(coins[i])) >= 0) {
                coinTotals[i]++;
                preciseChange = preciseChange.subtract(BigDecimal.valueOf(coins[i]));
            }
        }

        System.out.println(coinsName[0] + ": " + coinTotals[0]);
        System.out.println(coinsName[1] + ": " + coinTotals[1]);
        System.out.println(coinsName[2] + ": " + coinTotals[2]);

        logger("CHANGE GIVEN: " + nf.format(getBalance()));
        this.balance = 0;
    }

        public void logger(String message) {
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");
            String line = dtf.format(time) + " " + message;
            try (FileWriter auditFile = new FileWriter("log.txt", true)) {
                auditFile.append(line);
                auditFile.write("\n");
            } catch (IOException e) {
                System.err.println("File cannot be appended.");
            }
        }

        public int updateQuantity (String slot) {
            int currentQuantity = items.get(slot).getItems_quantity();
            int newQuantity = currentQuantity - 1;
            items.get(slot).items_quantity = newQuantity;
            return newQuantity;
        }

        public void purchaseVerification(String slot) {
            updateQuantity(slot);
            System.out.println(items.get(slot).getName() + " " + nf.format(items.get(slot).getPrice()) + " REMAINING QUANTITY: "
                    + items.get(slot).items_quantity);
            logger(items.get(slot).getName() + " " + nf.format(getBalance()) + " " + nf.format((getBalance() - items.get(slot).getPrice())));
            items.get(slot).displayMessage();
            balance -= items.get(slot).getPrice();
            System.out.println("REMAINING BALANCE: " + nf.format(getBalance()));
        }


    }


