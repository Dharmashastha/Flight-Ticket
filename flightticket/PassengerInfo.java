package flightticket;

import java.util.Arrays;
import java.util.Map;

public class PassengerInfo {
	
	private String flightName;
	private int numberOfTickets;
	private BookingType bookingType;
	private String[] passengersName;
	private Map<String,Boolean> seats;
	private MealStatus mealStatus;
	private double totalPrice;
	private String bookingId;
	private boolean bookingStatus;
	
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public int getNumberOfTickets() {
		return numberOfTickets;
	}
	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}
	public BookingType getBookingType() {
		return bookingType;
	}
	public void setBookingType(BookingType bookingType) {
		this.bookingType = bookingType;
	}
	public String[] getPassengersName() {
		return passengersName;
	}
	public void setPassengersName(String[] passengersName) {
		this.passengersName = passengersName;
	}
	public Map<String,Boolean> getSeats() {
		return seats;
	}
	public void setSeats(Map<String,Boolean> seats) {
		this.seats = seats;
	}
	public MealStatus isMealStatus() {
		return mealStatus;
	}
	public void setMealStatus(MealStatus mealStatus) {
		this.mealStatus = mealStatus;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public boolean isBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	
	@Override
	public String toString() {
		return "PassengerInfo [flightName=" + flightName + ", numberOfTickets=" + numberOfTickets + ", bookingType="
				+ bookingType + ", passengersName=" + Arrays.toString(passengersName) + ", seats=" + seats
				+ ", mealStatus=" + mealStatus + ", totalPrice=" + totalPrice + ", bookingId=" + bookingId
				+ ", bookingStatus=" + bookingStatus + "]";
	}
	
	
}
