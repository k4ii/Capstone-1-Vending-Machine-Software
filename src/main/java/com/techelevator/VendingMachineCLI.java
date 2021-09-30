package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String EXIT_VENDINGMACHINE = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, EXIT_VENDINGMACHINE };
	private static final String FEED_MONEY_OPTION = "Feed Money";
	private static final String PURCHASE_OPTION = "Select Product";
	private static final String END_TRANSACTION = "Finish Transaction";
	private static final String []PURCHASE_MENU_OPTION = {FEED_MONEY_OPTION,PURCHASE_OPTION,END_TRANSACTION};

	Scanner input = new Scanner(System.in);
	VendingMachine vendingMachine = new VendingMachine();
	Map<String, Product> inventory = vendingMachine.getInventory();



	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run ()throws NumberFormatException {


		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);


			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)|| choice.equals("1")) {
				DisplayMenuItem(vendingMachine);
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				String select = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTION);
				if (select.equals(FEED_MONEY_OPTION) || select.equals("1")) {
					System.out.println("Enter amount to feed");
					double amount = input.nextDouble();
					vendingMachine.feedMoney(amount);
				} else if (select.equals(PURCHASE_OPTION) || select.equals("2")) {
					DisplayMenuItem(vendingMachine);
					System.out.println("Select a product");
					String productId = input.nextLine();
					if (inventory.containsKey(productId)){
						if(vendingMachine.getQuantity()>=1){
							if(vendingMachine.getBalance()>= inventory.get(productId).getPrice()){
								vendingMachine.purchaseValidation(productId);
								//vendingMachine.updateInventory(productId);

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


			} else if (choice.equals(EXIT_VENDINGMACHINE) || choice.equals("3")) {
				System.exit(1);
			}
		}
	}

	private void DisplayMenuItem(VendingMachine vendingMachine) {
		for (Map.Entry<String, Product> item : inventory.entrySet()) {
			System.out.println(item.getKey() + " " + item.getValue().getName() + " " + item.getValue().getPrice());
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

}



