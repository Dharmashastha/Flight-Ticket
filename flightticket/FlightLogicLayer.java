package flightticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import common.CustomException;
import common.HelperUtility;

public class FlightLogicLayer 
{
	
	private Map<String,FlightInfo> storeFlight = new HashMap<>();
	
	private Map<String,PassengerInfo> storePassenger = new HashMap<>();
	
	private Map<String,Boolean> economySeatsStore = new TreeMap<>();
	
	private Map<String,Boolean> businessSeatsStore = new TreeMap<>();
	
	static long bookingId= 1000000;
	
	double cancellationFee;
	
	
	Connected connect;
	
	public FlightLogicLayer() 
	{
		try {
			Class<?> connectCall = Class.forName("flightticket.PersistantLayer");
			
			Object saved = connectCall.getDeclaredConstructor().newInstance();
			
			connect = (Connected)saved; 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private long autoGenerate()
	{
		return ++bookingId;
	}
	
	public String bookingIdGenerate(String flightName) throws CustomException
	{
		HelperUtility.checkString(flightName);
		return flightName + autoGenerate();
	}
	
	
	
	private void nullCheckFlight(FlightInfo flightInfo) throws CustomException
	{
		if(Objects.isNull(flightInfo))
		{
			throw new CustomException("Your Flight Name Invalid");
		}
	}
	
	private void nullCheckPassenger(PassengerInfo passengerInfo) throws CustomException
	{
		if(Objects.isNull(passengerInfo))
		{
			throw new CustomException("Your Booking ID Invalid");
		}
	}
	
	
	public String seatingEconomy(int[] economySeats,int economyRow)
	{
		StringBuilder saved = new StringBuilder();
		
		int economySize = economySeats.length;
		
		saved.append("\t\t\t ECONOMY \n");
		
		for(int i = 0; i < economyRow; i++)
		{
			for(int j = 0; j < economySize; j++)
			{
				int seats = economySeats[j];
				int count = 65;
				
				if(j == 0)
				{
					saved.append(i + " W");
					economySeatsStore.put(i+1 +" "+ (char)count++,true);
				}
				
				if(j == 0 || j == economySize - 1)
				{
					seats--;
					for(int k = 0; k < seats; k++)
					{
					    if(k > 0 && j != economySize - 1 || k > 0 && j == economySize - 1)
						{
							saved.append("M");
							economySeatsStore.put(i+1 +" "+ (char)count++,false);
						}
					    if(k == 0 && j != 0)
					    {
					        saved.append("A");
					        economySeatsStore.put(i+1 +" "+ (char)count++,true);
					    }
					    else if(k==seats - 1 && j == 0)
					    {
					       	saved.append("A "); 
					       	economySeatsStore.put(i+1 +" "+ (char)count++,true);
					    }
					    
					}
				}
				else
				{
					for(int k = 0; k < seats; k++)
					{
						if(k == 0)
						{
							saved.append("A");
							economySeatsStore.put(i+1 +" "+ (char)count++,true);
						}
						else if(k != 0 && k != seats - 1)
						{
							saved.append("M");
							economySeatsStore.put(i+1 +" "+ (char)count++,false);
						}
					
						if(k == seats - 1)
						{
							saved.append("A ");
							economySeatsStore.put(i+1 +" "+ (char)count++,true);
						}
					}
				}
				
				if(j == economySize - 1)
				{
					saved.append("W");
					economySeatsStore.put(i+1 +" "+ (char)count++,true);
				}
			}
		}
		
		saved.append("W - Window\nM - Middle\nA - Aisle\n");
		
	return saved.toString();
	}
	
	
	
	public String seatingBusiness(int[] businessSeats,int businessRow)
	{
		StringBuilder saved = new StringBuilder();
		
		saved.append("\t\t\t\t BUSINESS \n");
		
		int businessSize = businessSeats.length;
		
		
		
		for(int i = 0; i < businessRow; i++)
		{
			for(int j = 0; j < businessSize; j++)
			{
				int seats = businessSeats[j];
				int count = 65;
				
				if(j == 0)
				{
					saved.append(i +" W");
					businessSeatsStore.put(i+1 +" "+ (char)count++,true);
				}
				
				if(j == 0 || j == businessSize - 1)
				{
					seats--;
					for(int k = 0; k < seats; k++)
					{
						 if(k > 0 && j != businessSize - 1 || k > 0 && j == businessSize - 1)
						{
							saved.append("M");
							businessSeatsStore.put(i+1 +" "+ (char)count++,false);
						}
					    if(k == 0 && j != 0)
					    {
					        saved.append("A");
					        businessSeatsStore.put(i+1 +" "+ (char)count++,true);
					    }
					    else if(k==seats - 1 && j == 0)
					    {
					       	saved.append("A "); 
					       	businessSeatsStore.put(i+1 +" "+ (char)count++,true);
					    }
					}
				}
				else
				{
					for(int k = 0; k < seats; k++)
					{
						if(k == 0)
						{
							saved.append("A");
							businessSeatsStore.put(i+1 +" "+ (char)count++,true);
						}
						else if(k != 0 && k != seats - 1)
						{
							saved.append("M");
							businessSeatsStore.put(i+1 +" "+ (char)count++,false);
						}
					
						if(k == seats - 1)
						{
							saved.append("A ");
							businessSeatsStore.put(i+1 +" "+ (char)count++,true);
						}
					}
				}
				
				if(j == businessSize - 1)
				{
					saved.append("W");
					businessSeatsStore.put(i+1 +" "+ (char)count++,true);
				}
			}
			
			saved.append("\n");
		}
		
		saved.append("W - Window\nM - Middle\nA - Aisle\n");
		
		return saved.toString();	
	}
	
	public String showBusinessSeats(int size)
	{
		StringBuilder saved = new StringBuilder();
		
		int count = 0;
		
		for(String seats:businessSeatsStore.keySet())
		{
			saved.append(seats + " ");
			if(count == size)
			{
				saved.append("\n");
				count = 0;
			}
			count++;
		}
		return saved.toString();
	}
	
	public String showEconomySeats(int size)
	{
		StringBuilder saved = new StringBuilder();
		
		int count = 0;
		
		for(String seats:economySeatsStore.keySet())
		{
			saved.append(seats + " ");
			if(count == size)
			{
				saved.append("\n");
				count = 0;
			}
			count++;
		}
		return saved.toString();
	}
	
	public FlightInfo getFlight(String flightName) throws CustomException
	{
		HelperUtility.checkString(flightName);
		FlightInfo flightInfo = storeFlight.get(flightName);
		return flightInfo;
	}
	
	private PassengerInfo getPassenger(String bookingId) throws CustomException
	{
		HelperUtility.checkString(bookingId);
		PassengerInfo passengerInfo = storePassenger.get(bookingId);
		return passengerInfo;
	}
	
	
	public void storeFlightDetails(FlightInfo flightInfo) throws CustomException
	{
		nullCheckFlight(flightInfo);
		storeFlight.put(flightInfo.getFlightName(), flightInfo);
	}
	
	public void storePassengerDetails(PassengerInfo passengerInfo) throws CustomException
	{
		nullCheckPassenger(passengerInfo);
		storePassenger.put(passengerInfo.getBookingId(), passengerInfo);
	}
	
	public String listFlightDetails()
	{
		StringBuilder saved = new StringBuilder();
		
		saved.append("Number Of Flights Count : " + storeFlight.size() + "\n\n\n");
		
		int count = 1;
		
		for(String flightName:storeFlight.keySet())
		{
			saved.append(count++ + " Flight Name : " + flightName + "\n");
		}
	return saved.toString();	
	}
	
	
	public String searchFlights(String to) throws CustomException
	{
		StringBuilder saved = new StringBuilder();
		
		for(String flightName:storeFlight.keySet())
		{
			FlightInfo flightInfo = getFlight(flightName);
			
			if(flightInfo.getTo().equals(to))
			{
				saved.append(flightInfo.toString() + "\n");
			}
		}
	return saved.toString();	
	}
	
	public String searchBusinessFlights() throws CustomException
	{
		StringBuilder saved = new StringBuilder();
		
		for(String flightName:storeFlight.keySet())
		{
			FlightInfo flightInfo = getFlight(flightName);
			
			if(flightInfo.getEconomyRow() == 0)
			{
				saved.append(flightInfo.toString() + "\n");
			}
		}
	return saved.toString();	
	}
	
	public String getPassengerDetails(String bookingId) throws CustomException
	{		
		PassengerInfo passengerInfo = getPassenger(bookingId);
		nullCheckPassenger(passengerInfo);
		return passengerInfo.toString();
	}
	
	public void removeBusinessSeats(String seats) throws CustomException
	{
		HelperUtility.checkString(seats);
		businessSeatsStore.remove(seats);
	}
	
	public void removeEconomySeats(String seats) throws CustomException
	{
		HelperUtility.checkString(seats);
		economySeatsStore.remove(seats);
	}
	
	public int businessSize(String flightName) throws CustomException
	{
		HelperUtility.checkString(flightName);
		FlightInfo flightInfo = storeFlight.get(flightName);
		
		int[] seats = flightInfo.getBusinessSeats();
		int size = 0;
		
		for(int iter = 0; iter < seats.length; iter++)
		{
			size += seats[iter];
		}
	return size;	
	}
	
	public int economySize(String flightName) throws CustomException
	{
		HelperUtility.checkString(flightName);
		FlightInfo flightInfo = storeFlight.get(flightName);
		
		int[] seats = flightInfo.getEconomySeats();
		int size = 0;
		
		for(int iter = 0; iter < seats.length; iter++)
		{
			size += seats[iter];
		}
	return size;	
	}
	
	public void cancelBusinessSeats(String bookingId) throws CustomException
	{
		HelperUtility.checkString(bookingId);
		
		PassengerInfo passengerInfo = getPassenger(bookingId);
		
		Map<String, Boolean> seats = passengerInfo.getSeats();
		int passengers = passengerInfo.getNumberOfTickets();
		
		double price = passengers * 200;
		
		cancellationFee += passengerInfo.getTotalPrice() - price;
		
		businessSeatsStore.putAll(seats);
		
		storePassenger.remove(passengerInfo.getBookingId());
	}
	
	public void cancelEconomySeats(String bookingId) throws CustomException
	{
		HelperUtility.checkString(bookingId);
		
		PassengerInfo passengerInfo = getPassenger(bookingId);
		
		Map<String, Boolean> seats = passengerInfo.getSeats();
		int passengers = passengerInfo.getNumberOfTickets();
		
		double price = passengers * 200;
		
		cancellationFee += passengerInfo.getTotalPrice() - price;
		
		economySeatsStore.putAll(seats);
		
		storePassenger.remove(passengerInfo.getBookingId());
	}
	
	public Boolean getBusinessValue(String seats)
	{
		return businessSeatsStore.get(seats);
	}
	
	public Boolean getEconomyValue(String seats)
	{
		return economySeatsStore.get(seats);
	}
	
	public void surgePricing(String flightName) throws CustomException
	{
		HelperUtility.checkString(flightName);
		FlightInfo flightInfo = getFlight(flightName);
		
		flightInfo.setBusinessPrice(flightInfo.getBusinessPrice() + 200);
		
		flightInfo.setEconomyPrice(flightInfo.getEconomyPrice() + 100);
	}
	
	public void writeFileInfo() throws CustomException
	{
		connect.writeFlightFile(storeFlight,economySeatsStore,businessSeatsStore);
	}
	
	public void readFileInfo() throws CustomException
	{
		storeFlight = connect.getFlightData();
		
		businessSeatsStore = connect.getBusinessData();
		
		economySeatsStore = connect.getEconomyData();
		
		bookingId = connect.getBookingId();
	}
	
}
