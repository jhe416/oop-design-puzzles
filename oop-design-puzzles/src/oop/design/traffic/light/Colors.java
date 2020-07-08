package oop.design.traffic.light;

public enum Colors {
	GREEN(60),
	YELLO(10),
	RED(60);
	
	public int value;
	
	Colors(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
