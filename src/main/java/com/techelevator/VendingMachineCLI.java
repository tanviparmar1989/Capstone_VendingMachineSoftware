package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT };
	private static final String MAIN_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String MAIN_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String MAIN_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS_LEVEL2 = { MAIN_MENU_OPTION_FEED_MONEY, MAIN_MENU_OPTION_SELECT_PRODUCT, MAIN_MENU_OPTION_FINISH_TRANSACTION, "" };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			VendingMachine restockMachine = new VendingMachine();
			AuditLog.log("Created a vending machine.");
			restockMachine.restock();
			AuditLog.log("Restocked the vending machine.");
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			AuditLog.log("Displayed main menu to customer.");
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				AuditLog.log("Customer chose to display items.");
				restockMachine.displayProducts();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS_LEVEL2, restockMachine);
				AuditLog.log("Customer chose to purchase items so displayed menu 2 to customer.");
				if (choice.equals(MAIN_MENU_OPTION_FEED_MONEY)) {
					AuditLog.log("Customer chose to feed money.");
					restockMachine.acceptMoney();
					restockMachine.displayUserBalance();
					restockMachine.displayProducts();
					restockMachine.selectProduct();
					restockMachine.dispenseProducts();
					restockMachine.disperseChange();
				}else if(choice.equals(MAIN_MENU_OPTION_SELECT_PRODUCT)){
					restockMachine.displayProducts();
					restockMachine.selectProduct();
					restockMachine.acceptMoney();
					restockMachine.displayUserBalance();
					restockMachine.dispenseProducts();
					restockMachine.disperseChange();
				}else if(choice.equals(MAIN_MENU_OPTION_FINISH_TRANSACTION)){
					restockMachine.disperseChange();
				}
			}else if(choice.equals(MAIN_MENU_OPTION_EXIT)){
				// exit
				System.exit(1);
				AuditLog.log("Exiting the application.");
			}else if(choice.equals(MAIN_MENU_OPTION_SALES_REPORT)){
				//make it hidden and it generates a report
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
