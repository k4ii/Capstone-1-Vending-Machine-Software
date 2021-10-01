package com.techelevator;

import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    int quantity = 5;
    double balance = 0;
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    File log = new File("log.txt");

    public int getQuantity(){
        return quantity;
    }
    public void updateInventory(String slot){
        //TODO Need to fix
        //this.quantity = quantity-1;
    }


    Map<String, Product> items = new LinkedHashMap<>();
    BufferedReader br = null;

    public Map<String,Product> getInventory(){
        String path2 ="vendingmachine.csv" ;
        File input = new File(path2);

        try {
            br = new BufferedReader(new FileReader(input));
            String line = null;
            while((line=br.readLine()) != null) {
                List<String[]> item = new ArrayList<>();
                String [] fileLine= line.split("\\|");
                item.add(fileLine);
                String slot = fileLine[0].trim();
                String price = fileLine[2].trim();
                String name = fileLine[1].trim();
                String type = fileLine[3].trim();

                if(type.equals("Chip")) {
                    Product chip = new Chip(name,Double.parseDouble(price), quantity);
                    quantity = 5;
                    items.put(slot, chip);
                }
                else if(type.equals("Drink")) {
                    Product drink = new Drink (name,Double.parseDouble(price), quantity);
                    quantity = 5;
                    items.put(slot,drink);
                }
                else if(type.equals("Gum")) {
                    Product gum = new Gum (name,Double.parseDouble(price), quantity);
                    quantity = 5;
                    items.put(slot,gum);
                }

                else if(type.equals("Candy")) {
                    Product candy = new Candy (name,Double.parseDouble(price), quantity);
                    quantity = 5;
                    items.put(slot,candy);
                }
            }

        } catch (Exception e) {
            System.err.println("file not found");

        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return items;
    }


    public String []displayItem() {
        String[] itemsArray = new String[16];
        List<String> inventory = new ArrayList<>();
        File newFile = new File("vendingmachine.csv");
        try (Scanner in = new Scanner(newFile)) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                inventory.add(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 1; i < itemsArray.length; i++) {
            itemsArray[i] = inventory.get(i);
        }
        return itemsArray;
    }


    public void feedMoney(double amount){
        if( amount ==1.0 || amount==2.0 || amount==5.0 || amount==10.0){
            balance+=amount;
            System.out.println("Current money provide :"+nf.format(getBalance()));
        }
        else {
            System.out.println("you enter the wrong amount.");
        }
        audit("FEED MONEY "+nf.format(amount)+" "+nf.format(getBalance()));
    }

    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance= balance;
    }

    public void change(){
        Double change = getBalance();


        String []coinsName = new String[]{"Quarter(s)","Dime(s)","Nickel(s)"};
        Double [] coins =  new Double[]{0.25,0.10,0.05};
        for (int  i= 0; i < coinsName.length ; i++) {
            int counter;
            counter= (int)(getBalance()/coins[i]);
            balance-= counter*coins[i];
            if(counter!=0){
                System.out.println(counter + " "+ coinsName[i]);
            }

        }

        audit("CHANGE GIVEN: "+change);


    }

    public void audit(String message){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss ");
        String line = dtf.format(time)+ " "+ message;
        try(FileWriter auditFile = new FileWriter(log,true)){
            auditFile.append(line);
            auditFile.write("\n");
        }catch (IOException e){
            System.err.println("File is not writable ");
        }


    }

    public File getProductFile(){
        String path2 ="/Users/fredkonan/Desktop/capstone/vendingmachine.csv" ;
        File input = new File(path2);
        if(!input.exists()|| !input.isFile()){
            System.out.println("File does not exist or is not a file");
            System.exit(0);
        }
        return input;
    }

    public void purchaseValidation(String slot){
        updateInventory(slot);
        System.out.println(getInventory().get(slot).getName()+" "+nf.format(getInventory().get(slot).getPrice())+" quantity remind: "+getQuantity());
        audit(getInventory().get(slot).getName()+" "+nf.format(getBalance())+" "+nf.format((getBalance()-getInventory().get(slot).getPrice())));
        getInventory().get(slot).displayMessage();
        balance-=getInventory().get(slot).getPrice();


    }



}