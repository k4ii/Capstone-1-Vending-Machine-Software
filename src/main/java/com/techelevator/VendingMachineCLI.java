package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};





    Scanner scanner = new Scanner(System.in);
	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
/*
	public void getQuantity(int quantity){
		if(quantity > 0){
			//Subtract inventory purchased from quantity
			//
		}
		else{
			System.out.println("SOLD OUT");
		}

	}*/

	public void run() {
		VendingMachine vendingMachine = new VendingMachine();
		File file = vendingMachine.getItemFile();
		int quantity = 5;

		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);


			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)|| choice.equals("1")) {
				for(Map.Entry<String, Product> item: vendingMachine.getInventory().entrySet()){
					System.out.println(item.getKey()+" "+item.getValue().getName()+" "+item.getValue().getPrice());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				String select = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if (select.equals(PURCHASE_MENU_OPTION_FEED_MONEY) || select.equals("1")) {
					System.out.println("Enter amount to feed");
					double amount = scanner.nextDouble();
					vendingMachine.feedMoney(amount);
				} else if (select.equals(MAIN_MENU_OPTION_PURCHASE) || select.equals("2")) {
					for(Map.Entry<String,Product> item: vendingMachine.getInventory().entrySet()){
						System.out.println(item.getKey()+" "+item.getValue().getName()+" "+item.getValue().getPrice() + item.getValue().getQuantity());
					}
					System.out.println("Select a product");
					String productId = scanner.nextLine();
					if (vendingMachine.getInventory().containsKey(productId)){
						if(quantity >= 1){
							if(vendingMachine.getBalance()>= vendingMachine.getInventory().get(productId).getPrice()){
								vendingMachine.purchased(productId);
								quantity--;
							}else{
								System.out.println("not enough money");
							}
						}else {
							System.out.println(vendingMachine.getInventory().get(productId).getName()+" is sold out, I'm Sorry" );
						}


					} else {
						System.out.println("This product does not exist");

					}


				} else if (select.equals("Finish Transaction") || select.equals("3")) {
					vendingMachine.change();
				}


			} else if (choice.equals(MAIN_MENU_OPTION_EXIT) || choice.equals("3")) {
				System.exit(1);
			}
		}

	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
