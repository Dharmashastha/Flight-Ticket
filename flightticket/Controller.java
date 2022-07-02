package flightticket;

import common.CustomException;

public class Controller {

	public static void main(String[] args) {
		
		ControllerMethod controllerMethod = new ControllerMethod();
		
		boolean flag = false;

		int choice = 0;
		
		try {
			controllerMethod.flightLayer.readFileInfo();
		} catch (CustomException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		while (!flag) {
						
			System.out.println(
					"Press -> 1.Add New FlightInfo\nPress -> 2.Booking Options\nPress -> 3.Check Seating Arrangements\nPress -> 4.Exit");

			System.out.println("Enter Your Choice");

			try {
				choice = controllerMethod.inputCall.getInt();
			} catch (CustomException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}

			switch (choice) {
			case 1: {
				try {
					controllerMethod.flightInfo();
				} catch (CustomException e) {
					// e.printStackTrace();
					System.out.println(e.getMessage());
				}
				break;
			}

			case 2: {
				try {
					controllerMethod.bookingOptions();
				} catch (CustomException e) {
					// e.printStackTrace();
					System.out.println(e.getMessage());
				}
				break;
			}

			case 3: {
				try {
					System.out.println("Enter the FlightName");
					
					String flightName = controllerMethod.inputCall.getString();
					
					controllerMethod.flightSeatingArrangements(flightName);
					
				} catch (CustomException e) {
					// e.printStackTrace();
					System.out.println(e.getMessage());
				}
				break;
			}

			case 4: {
				flag = true;
				try {
					controllerMethod.flightLayer.writeFileInfo();
				} catch (CustomException e) {
					//e.printStackTrace();
					System.out.println(e.getMessage());
				}
				break;
			}

			default: {
				System.out.println("Enter the Valid Choice");
				break;
			}
			}
		}
	}

}
