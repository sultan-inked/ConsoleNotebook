package tools;

public class Alert {
	public static void separator() {
		System.out.println("____________________________________");
	}
	
	public static void wrtHere() {
		System.out.print("Write here: ");
	}
	
	public static void wrtNmbr() {
		System.out.println("Just write number with your choice.");
	}
	public static void wrtNmbr(String exitOrBack) {
		System.out.println("Just write number with your choice or '" + exitOrBack + "'.");
	}
	
	public static void ftrNotYet() {
		System.out.println("This feature has not yet been developed.");
	}
	
	public String wrtNmbrScan(int numberOfChoices, String backOrExit) {
		while(true) {
			String choice = Cnsl.scanWrtHere();
			for(int i = 1; i <= numberOfChoices; i++) {
				if(choice.matches("[0-9]+") ? Integer.parseInt(choice) == i : 
						!choice.equals("") ? choice.equals(backOrExit) : false) {
					return choice;
				}
			}
			switch(backOrExit) {
			case "back":
				wrtNmbr("back");
				break;
			case "exit":
				wrtNmbr("exit");
				break;
			case "":
				wrtNmbr();
				break;
			}
		}
	}
}
