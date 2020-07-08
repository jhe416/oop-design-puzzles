package oop.design.vending.machine;

public interface State {
	public void ready();
	public void collectCash(int cash,String productCode);
	public void pushChange(String productCode);
	public void delivery(String productCode);
	public void cancel();
}