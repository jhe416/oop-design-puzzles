package oop.design.vending.machine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
	 Map<String,Integer> productPrice = new HashMap<>();
	 Map<String,Product> productCodeMap = new HashMap<>();
	 State currentState = new Ready(this);
	 int cash = 0;
	
	public void collectCash(int cash, String productCode) {
		currentState.collectCash(cash,productCode);
	}

	public void cancel() {
		currentState.cancel();
	}
}

interface Product{
	public String getCode();
	public String getName();
	public String getCatagory();
	public int getPrice();
}
class Ready implements State{
	private VendingMachine vendingMachine;
	public Ready(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
		this.vendingMachine.cash = 0;
	}
	
	public void collectCash(int cash, String productCode) {
		this.vendingMachine.cash+=cash;
		this.vendingMachine.currentState = new PushChange(vendingMachine);
		this.vendingMachine.currentState.pushChange(productCode);
	}
	
	public void pushChange(String productCode) {
		throw new RuntimeException("Error");
	}
	public void delivery(String productCode) {
		throw new RuntimeException("Error");
	}
	public void cancel() {
		throw new RuntimeException("Error");
	}
	public void ready() {}
}

class PushChange implements State{
	
	private VendingMachine vendingMachine;
	public PushChange(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}
	
	public void ready() {
		//error
	}
	public void collectCash(int cash, String productCode) {
		//error
	}
	public void pushChange(String productCode) {
		int productPrice = this.vendingMachine.productPrice.get(productCode);
		if(this.vendingMachine.cash<productPrice) {
			this.vendingMachine.currentState = new Ready(vendingMachine);
			System.out.println(String.format("not enough cash, returning full amount %d", this.vendingMachine.cash));
		}else {
			this.vendingMachine.currentState = new Delivery(vendingMachine);
			System.out.println(String.format("returning change: %d", this.vendingMachine.cash - productPrice));
			this.vendingMachine.currentState.delivery(productCode);
		}
	}
	public void delivery(String productCode) {
		//error
	}
	public void cancel() {
		//error
	}
}
class Delivery implements State{
	
	VendingMachine vendingMaching;
	
	public Delivery(VendingMachine vendingMachine) {
		this.vendingMaching = vendingMachine;
	}
	public void ready() {
		//error
	}
	public void pushChange(String productCode) {
		//error
	}
	public void delivery(String productCode) {
		System.out.println(String.format("deliver product : %s", this.vendingMaching.productCodeMap.get(productCode).getName()));
		this.vendingMaching.currentState = new Ready(vendingMaching);
	}
	public void cancel() {
		//error
	}
	public void collectCash(int cash, String productCode) {
		//error
	}
}
