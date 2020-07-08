package oop.design.traffic.light;
public class TrafficController {
	private Colors[] signals = {Colors.GREEN,Colors.YELLO,Colors.RED,Colors.YELLO};
	private int ns_pos = 0;
	private int we_pos = 2;
	
	public void stateChange () throws Exception{
		Colors signal = signals[ns_pos];
		Thread.sleep(signal.getValue()*1000);
		ns_pos = (ns_pos + 1) % 4;
		we_pos = (we_pos + 1) % 4;
		display();
	}
	
	public void display() throws Exception {
		boolean isRedYellowNS = signals[ns_pos] == Colors.YELLO && signals[ns_pos-1] == Colors.RED;
		System.out.println(String.format("north south road signal: %s", isRedYellowNS? signals[ns_pos-1].name() : signals[ns_pos].name()));
		
		
		boolean isRedYellowWE = signals[we_pos] == Colors.YELLO && signals[we_pos-1] == Colors.RED;
		System.out.println(String.format("east west road signal: %s", isRedYellowWE? signals[we_pos-1] : signals[we_pos].name()));
		
		stateChange();
	}
	public void run() throws Exception {
		while(true) {
			display();
		}
	}
	
	public static void main(String[] args) throws Exception {
		TrafficController controller = new TrafficController();
		controller.run();
	}
}
