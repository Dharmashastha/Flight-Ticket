package flightticket;

import java.util.Map;

import common.CustomException;

public interface Connected {
	
	public void writeFlightFile(Map<String,FlightInfo> storeFlight,Map<String,Boolean> economySeatsStore,Map<String,Boolean> businessSeatsStore) throws CustomException;
	
	public Map<String,FlightInfo> getFlightData() throws CustomException;
	
	public Map<String,Boolean> getBusinessData() throws CustomException;
	
	public Map<String,Boolean> getEconomyData() throws CustomException;
	
	public long getBookingId() throws CustomException;
	
}
