package flightticket;

import java.io.Serializable;
import java.util.Arrays;

public class FlightInfo implements Serializable
{
	
	private static final long serialVersionUID = -3644249404375852101L;
	
	private String flightName;
	private String from;
	private String to;
	private int[] businessSeats;
	private int[] economySeats;
	private int businessRow;
	private int economyRow;
	private String departureDate;
	private double businessPrice;
	private double economyPrice;
	
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int[] getBusinessSeats() {
		return businessSeats;
	}
	public void setBusinessSeats(int[] businessSeats) {
		this.businessSeats = businessSeats;
	}
	public int[] getEconomySeats() {
		return economySeats;
	}
	public void setEconomySeats(int[] economySeats) {
		this.economySeats = economySeats;
	}
	public int getBusinessRow() {
		return businessRow;
	}
	public void setBusinessRow(int businessRow) {
		this.businessRow = businessRow;
	}
	public int getEconomyRow() {
		return economyRow;
	}
	public void setEconomyRow(int economyRow) {
		this.economyRow = economyRow;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public double getBusinessPrice() {
		return businessPrice;
	}
	public void setBusinessPrice(double businessPrice) {
		this.businessPrice = businessPrice;
	}
	public double getEconomyPrice() {
		return economyPrice;
	}
	public void setEconomyPrice(double economyPrice) {
		this.economyPrice = economyPrice;
	}
	
	
	@Override
	public String toString() {
		return "FlightInfo [flightName=" + flightName + ", from=" + from + ", to=" + to + ", businessSeats="
				+ Arrays.toString(businessSeats) + ", economySeats=" + Arrays.toString(economySeats) + ", businessRow="
				+ businessRow + ", economyRow=" + economyRow + ", departureDate=" + departureDate + ", businessPrice="
				+ businessPrice + ", economyPrice=" + economyPrice + "]";
	}	
}
