package flightticket;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import common.CustomException;
import common.HelperUtility;

public class PersistantLayer implements Connected {
	
	
	public void writeFlightFile(Map<String,FlightInfo> storeFlight,Map<String,Boolean> economySeatsStore,Map<String,Boolean> businessSeatsStore) throws CustomException
	{
		try(FileOutputStream fos = new FileOutputStream("Flight.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);)
		{
			oos.writeObject(storeFlight);
			oos.writeObject(businessSeatsStore);
			oos.writeObject(economySeatsStore);
			oos.writeLong(FlightLogicLayer.bookingId);
		}
		catch(IOException e)
		{
			throw new CustomException(e);
		}
	}
	
	
	public Map<String,FlightInfo> getFlightData() throws CustomException
	{
		try(FileInputStream fis = new FileInputStream("Flight.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);)
		{
			return (Map<String, FlightInfo>) ois.readObject();
		}
		catch(IOException | ClassNotFoundException e)
		{
			throw new CustomException(e);
		}
	}
	
	public Map<String,Boolean> getBusinessData() throws CustomException
	{
		try(FileInputStream fis = new FileInputStream("Flight.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);)
		{
			ois.readObject();
			return (Map<String, Boolean>) ois.readObject();
		}
		catch(IOException | ClassNotFoundException e)
		{
			throw new CustomException(e);
		}
	}
	
	public Map<String,Boolean> getEconomyData() throws CustomException
	{
		try(FileInputStream fis = new FileInputStream("Flight.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);)
		{
			ois.readObject();
			ois.readObject();
			return (Map<String, Boolean>) ois.readObject();
		}
		catch(IOException | ClassNotFoundException e)
		{
			throw new CustomException(e);
		}
	}
	
	public long getBookingId() throws CustomException
	{
		try(FileInputStream fis = new FileInputStream("Flight.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);)
		{
			ois.readObject();
			ois.readObject();
			ois.readObject();
			return ois.readLong();
		}
		catch(IOException | ClassNotFoundException e)
		{
			throw new CustomException(e);
		}
	}
}
