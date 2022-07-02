package flightticket;

import java.util.HashMap;
import java.util.Map;

import common.CustomException;
import common.HelperUtility;
import common.InputCenter;

public class ControllerMethod {
	
	InputCenter inputCall = new InputCenter();
	
	FlightLogicLayer flightLayer = new FlightLogicLayer();
	
	
	public void bookingOptions() throws CustomException
	{
		boolean flag = false;
		
		while(!flag)
		{
			System.out.println("Press -> 1.List flight details\nPress -> 2.Search flights(location)\nPress -> 3.Find Search flights(Business Alone)\nPress -> 4.available seats for each flight\n"
					+ "Press -> 5.Booking Tickets\nPress -> 6.Business Cancel Ticket\nPress -> 7.Economy Cancel Ticket\nPress -> 8.Passenger Details\nPress -> 9.Exit");
			
			int choice = inputCall.getInt();
			
			switch(choice)
			{
				case 1:
				{
					System.out.println(flightLayer.listFlightDetails());
					break;
				}
				
				case 2:
				{
					System.out.println("Enter the to Location(Destination)");
					
					String to = inputCall.getString();
					
					System.out.println(flightLayer.searchFlights(to));
					break;
				}
				
				case 3:
				{
					System.out.println(flightLayer.searchBusinessFlights());
					break;
				}
				
				case 4:
				{
					System.out.println("Enter the Your FlightName");
					
					String flightName = inputCall.getString();
					
					int bSize = flightLayer.businessSize(flightName);
					
					int eSize = flightLayer.economySize(flightName);
					
					System.out.println(flightLayer.showBusinessSeats(bSize));
					
					System.out.println(flightLayer.showEconomySeats(eSize));
					break;
				}
				
				case 5:
				{
					booking();
					break;
				}
				
				case 6:
				{
					System.out.println("Enter Your BookingId");
					
					String bookingId = inputCall.getString();
					
					flightLayer.cancelBusinessSeats(bookingId);
					break;
				}
				
				case 7:
				{
					System.out.println("Enter Your BookingId");
					
					String bookingId = inputCall.getString();
					
					flightLayer.cancelEconomySeats(bookingId);
					break;
				}
				
				case 8:
				{
					System.out.println("Enter Your BookingId");
					
					String bookingId = inputCall.getString();
					System.out.println(flightLayer.getPassengerDetails(bookingId));
					break;
				}
				
				case 9:
				{
					flag = true;
					break;
				}
				
				default: {
					System.out.println("Enter the Valid Choice");
					break;
				}
			}
		}
	}
	
	
	
	private void setFlightValue(String flightName,String from,String to,int businessArray[],int economyArray[],int businessRow,int economyRow,
			String departureDate,double businessPrice,double economyPrice) throws CustomException
	{
		HelperUtility.checkString(flightName);
		HelperUtility.checkString(from);
		HelperUtility.checkString(to);
		FlightInfo flightInfo = new FlightInfo();
		
		flightInfo.setFlightName(flightName);
		
		flightInfo.setFrom(from);
		
		flightInfo.setTo(to);
		
		flightInfo.setBusinessSeats(businessArray);
		
		flightInfo.setEconomySeats(economyArray);
		
		flightInfo.setBusinessRow(businessRow);
		
		flightInfo.setEconomyRow(economyRow);
		
		flightInfo.setDepartureDate(departureDate);
		
		flightInfo.setBusinessPrice(businessPrice);
		
		flightInfo.setEconomyPrice(economyPrice);
		
		flightLayer.storeFlightDetails(flightInfo);
	}
	
	
	public void flightInfo() throws CustomException
	{
		
		System.out.println("Enter the Flight Name");
		
		String flightName = inputCall.getString();
		
		System.out.println("Enter the Flight From");
		
		String from = inputCall.getString();
		
		System.out.println("Enter the Flight To");
		
		String to = inputCall.getString();
		
		System.out.println("Enter the Seats Arrangements Business Length");
		
		int arrLength = inputCall.getInt();
		
		int businessArray[] = new int[arrLength];
		
		for(int iter = 0; iter < arrLength; iter++)
		{
			System.out.println("Enter the Seats Column Size");
			
			businessArray[iter] = inputCall.getInt();
		}
		
		System.out.println("Enter the Seats Arrangements Economy Length");
		
		 arrLength = inputCall.getInt();
		
		int economyArray[] = new int[arrLength];
		
		for(int iter = 0; iter < arrLength; iter++)
		{
			System.out.println("Enter the Seats Column Size");
			
			economyArray[iter] = inputCall.getInt();
		}
		
		System.out.println("Enter the Seating BusinessRow");
		
		int businessRow = inputCall.getInt();
		
		System.out.println("Enter the Seating EconomyRow");
		
		int economyRow = inputCall.getInt();
		
		System.out.println("Enter the Departure Date");
		
		String date = inputCall.getString();
		
		System.out.println("Enter the Business Ticket Price");
		
		double businessPrice = inputCall.getDouble();
		
		System.out.println("Enter the Economy Ticket Price");
		
		double economyPrice = inputCall.getDouble();
		
		flightLayer.seatingBusiness(businessArray, businessRow);
		
		flightLayer.seatingEconomy(economyArray, economyRow);
		
		setFlightValue(flightName, from, to, businessArray, economyArray, businessRow, economyRow,date,businessPrice, economyPrice);
	}
	
	private BookingType getBookingType(int number)
	{
		BookingType bookingType = null;
		
		switch(number)
		{
			case 1:
			{
				bookingType = BookingType.BUSINESS;
				break;
			}
			
			case 2:
			{
				bookingType = BookingType.ECONOMY;
				break;
			}
			
			default: {
				System.out.println("Enter the Valid Choice");
				break;
			}
		}
		return bookingType;
	}
	
	private MealStatus getMealStatus(int number)
	{
		MealStatus mealStatus = null;
		
		switch(number)
		{
			case 1:
			{
				mealStatus = MealStatus.YES;
				break;
			}
			
			case 2:
			{
				mealStatus = MealStatus.NO;
				break;
			}
			
			default: {
				System.out.println("Enter the Valid Choice");
				break;
			}
		}
		return mealStatus;
	}
	
	
	private void setPassengerValue(String flightName,int numberOfTickets,BookingType bookingType,String[] names,
			Map<String,Boolean> seats,double totalPrice,MealStatus mealStatus,String bookingId,boolean bookingStatus) throws CustomException
	{
		HelperUtility.checkString(flightName);
		HelperUtility.checkString(bookingId);
		
		PassengerInfo passengerInfo = new PassengerInfo();
		
		passengerInfo.setFlightName(flightName);
		
		passengerInfo.setNumberOfTickets(numberOfTickets);
		
		passengerInfo.setBookingType(bookingType);
		
		passengerInfo.setPassengersName(names);
		
		passengerInfo.setSeats(seats);
		
		passengerInfo.setTotalPrice(totalPrice);
		
		passengerInfo.setMealStatus(mealStatus);
		
		passengerInfo.setBookingId(bookingId);
		
		passengerInfo.setBookingStatus(bookingStatus);
		
		System.out.println("Your Booking Id " + passengerInfo.getBookingId());
		
		flightLayer.storePassengerDetails(passengerInfo);
		
		flightLayer.surgePricing(flightName);
	}
	
	private void booking() throws CustomException
	{
		System.out.println("Enter the Flight Name");
		
		String flightName = inputCall.getString();
		
		FlightInfo flightInfo = flightLayer.getFlight(flightName);
		
		System.out.println("Enter the Number Of Tickets");
		
		int numberOfTickets = inputCall.getInt();
		
		System.out.println("Enter the Your BookingType");
		
		System.out.println("Press -> 1.BUSINESS\nPress -> 2.ECONOMY");
		
		BookingType bookingType = getBookingType(inputCall.getInt());
		
		String[] names = new String[numberOfTickets];
		
		Map<String,Boolean> seats = new HashMap<>();
		
		int size = 0;
		
		boolean flag;
		
		double totalPrice = 0;
		
		for(int iter = 0; iter < numberOfTickets; iter++)
		{
			System.out.println("Enter the "+ iter +" Passenger Name");
			
			names[iter] = inputCall.getString();
			
			if(bookingType.equals(BookingType.BUSINESS))
			{
				size = flightLayer.businessSize(flightName);
				System.out.println(flightLayer.showBusinessSeats(size));
			}
			else
			{				
				size = flightLayer.economySize(flightName);
				System.out.println(flightLayer.showEconomySeats(size));
			}
			
			System.out.println("Enter the "+ iter +" Seats Number");
			
			String seatsNumber = inputCall.getString();
			
			if(bookingType.equals(BookingType.BUSINESS))
			{
				flag = flightLayer.getBusinessValue(seatsNumber);
				
				if(flag)
				{
					totalPrice += flightInfo.getBusinessPrice() + 200;
				}
				else
				{
					totalPrice += flightInfo.getBusinessPrice();
				}
				flightLayer.removeBusinessSeats(seatsNumber);
			}
			else
			{
				flag = flightLayer.getEconomyValue(seatsNumber);
				if(flag)
				{
					totalPrice += flightInfo.getEconomyPrice() + 100;
				}
				else
				{
					totalPrice += flightInfo.getEconomyPrice();
				}
				flightLayer.removeEconomySeats(seatsNumber);
			}
			seats.put(seatsNumber, flag);
			
		}
		
		System.out.println("Enter the MealStatus");
		
		System.out.println("Press -> 1.Yes\nPress -> 2.No");
		
		int number = inputCall.getInt();
		
		MealStatus mealStatus = getMealStatus(number);
		
		if(number == 1)
		{
			totalPrice += 200;
		}
		
		String bookingId = flightLayer.bookingIdGenerate(flightName);
		
		boolean bookingStatus = true;
		
		setPassengerValue(flightName, numberOfTickets, bookingType, names, seats, totalPrice, mealStatus, bookingId, bookingStatus);
		
	}
	
	
	public void flightSeatingArrangements(String flightName) throws CustomException
	{
		HelperUtility.checkString(flightName);
		
		FlightInfo flightInfo = flightLayer.getFlight(flightName);
		
		System.out.println(flightLayer.seatingBusiness(flightInfo.getBusinessSeats(), flightInfo.getBusinessRow()));
		
		System.out.println(flightLayer.seatingEconomy(flightInfo.getEconomySeats(), flightInfo.getEconomyRow()));
	}
	
}
