package auctionSystem;
import java.util.ArrayList;

import People.Bidder;
import People.Seller;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class Item {
	
	/*
	 * This class represent an item
	 * */
	
	private String name;
	private double minimumBid;
	private Date DateDeadline;
	private Seller TheOwningSeller;
	private Bidder TheOwningBidder;
	private float value;
	
	//getters and setters for item name

	public void setValue(float itemValue)
	{
		this.value = itemValue;
	}

	public float getValue()
	{
		return this.value;
	}
	public void addValue(float addedAmount)
	{
		this.value += addedAmount;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOwner(Seller randomSeller)
	{
		this.TheOwningSeller = randomSeller;
		this.TheOwningBidder = null;
	}
	public void setOwner(Bidder randomBidder)
	{
		this.TheOwningBidder = randomBidder;
		this.TheOwningSeller = null;
	}
	public String getName() {
		return name;
	}
	public Seller GetOwningSeller()
	{
		return TheOwningSeller;
	}
	public Bidder GetOwningBidder()
	{
		return TheOwningBidder;
	}
	//getters and setters for minimum bid on item
	
	public void setMinimumBid(double minBid) {
		minimumBid = minBid;
	}
	
	public double getMinimumBid() {
		return minimumBid;
	}
	public void setDeadline(int minutes) {
		DateDeadline.setDeadline(minutes);
	}
	public boolean checkIfDeadlineHasNotPassed()
	{
		return DateDeadline.checkIfDeadlineHasNotPassed();
	}
	public boolean checkIfDeadlineHasNotPassedGUI()
	{
		return DateDeadline.checkIfDeadlineHasNotPassedGUI();
	}
	public void setStartTime() {
		DateDeadline.setStartTime();
	}
	public void setDate(Date d1) {
		// TODO Auto-generated method stub
		DateDeadline = d1;
	}
	public String toString() {
		return name + " - Starting bid: $" + String.format("%.2f", value);
	}
	
	public String getValueString() {
		return String.format("%.2f", value);
	}
}
