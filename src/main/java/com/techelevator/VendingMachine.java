package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private double balance = 0;
    File log = new File("/capstone/log.txt");
    int quantity = 5;
    String path = "/C:\\Users\\Jenic\\Desktop\\Capstone\\capstone\\vendingmachine.csv";

    public double getBalance(){
        return balance;
    }
    private void setBalance(double balance) {
        this.balance = balance;
    }



    

    public void feedMoney(double amount){
        if (amount == 1.0 || amount == 2.0 || amount == 5.0 || amount == 10.0) {
            balance += amount;
            System.out.println("Current Money Provided: $" + amount);
        } else {
            System.out.println("You entered wrong amount.");
        }
    }
    public void change() {
        double change = getBalance();
        String[] coinName = {"Quarter(s)", "Dime(s)", "Nickel"};
        Double[] coinValue = {0.25, 0.10, 0.05};
        for (int i = 0; i < coinName.length ; i++) {
            int count;
            count = (int)(change/coinValue[i]);
            balance -= count * coinValue[i];
            if (count != 0){
                System.out.println(count + " "+ coinName[i]);
            }

        }
        audit("CHANGE GIVING", change);
    }

    public File getItemFile() {
        String path = "/C:\\Users\\Jenic\\Desktop\\Capstone\\capstone\\vendingmachine.csv";
        File input = new File(path);
        if (!input.exists() || !input.isFile()) {
            System.out.println("File not exist");
            System.exit(0);
        }
        return input;
    }
    public void audit (String name, double amount) {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss aa");
        String line = dtf.format(time) + " " + name + " " + amount + " " + getBalance();
        try(FileWriter auditFile = new FileWriter(log, true)) {
            auditFile.write(line);
            auditFile.write("\n");

        } catch (IOException e) {
            System.err.println("File is not writable");
        }

    }

    public void purchased(String slot) {
        Map<String, Product> inventory = getInventory();
        if (inventory.get(slot).isAvailable()){
            inventory.get(slot).purchaseItem();
            double newBalance = getBalance() - inventory.get(slot).getPrice();
            balance = newBalance;
            inventory.get(slot).displayMessage();
        } else {
            System.out.println("Sold Out");
        }
    }




 public Map<String, Product> getInventory() {

    Map<String, Product> items = new HashMap<>();
        try(Scanner reader = new Scanner(path)) {
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                String[] inputLine = line.split("\\|");

                if (inputLine[3].equals("Chip")){
                    Product chips = new Chips(inputLine[1], Double.parseDouble(inputLine[2]),quantity);

                    items.put(inputLine[0],chips);

                }
                if (inputLine[3].equals("Drink")) {
                    Product drink = new Drink(inputLine[1], Double.parseDouble(inputLine[2]),quantity);
                    items.put(inputLine[0], drink);
                }
                if (inputLine[3].equals("Candy")) {
                    Product candy = new Candy(inputLine[1], Double.parseDouble(inputLine[2]),quantity);
                    items.put(inputLine[0], candy);
                }
                if (inputLine[3].equals("Gum")) {
                    Product gum = new Candy(inputLine[1], Double.parseDouble(inputLine[2]),quantity);
                    items.put(inputLine[0], gum);
                }
            }
        }
     return items;
    }
}