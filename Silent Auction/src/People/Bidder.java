package People;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import auctionSystem.Item;
import auctionSystem.Account;
import auctionSystem.Date;

import Security.*;

public class Bidder {
	
	/*
	 * This class represents a bidder.
	 * This class contains several features, including state-of-the-art security
	 *   and AI bidding.
	 * */
	
	private String name;
	private String password;
	private ArrayList<Item> AquiredItemsFromBid;
	private Item ItemBidderBiddedOn;
	private float money;
	HashMap<String, Account> bidderList = new HashMap<String, Account>();
	
	
	public Bidder() {
		this.name = "";
		this.password = "";
		this.AquiredItemsFromBid = new ArrayList<Item>();
		this.ItemBidderBiddedOn = new Item();
	}
	
	
	//creates a new bidder and adds their account with user/pass to hashmap (will convert to a db entry soon).
	public Bidder(String username, String password) {
		this.name = username; 
		this.password = password;
		this.AquiredItemsFromBid = new ArrayList<Item>();
		this.ItemBidderBiddedOn = new Item();
		
		// Security features
		Account bidderAccount = new Account(username, password);
		bidderList.put(this.name, bidderAccount);
	}
	
	
	public float getMoney()
	{
		return this.money;
	}
	
	public void deductMoney(float amountToDeduct )
	{
		this.money -= amountToDeduct;
	}
	
	public void setBidderMoney(float money)
	{
		this.money = money;
	}
	
	public void addAquiredItemsFromBid(Item randomItem )
	{
		AquiredItemsFromBid.add(randomItem);
	}
	
	public boolean hasEnoughMoneyToAfford(Item auctionenedItem)
	{
		if(this.money >= auctionenedItem.getValue())
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean hasItems() {
		return (AquiredItemsFromBid.size() > 0);
	}
	
	public boolean placeBidOn(Item i1, float bid) {
		
		/*
		 * To be used by a human bidder, not an AI bidder
		 * */
		
		if (bid > money) {
			System.out.println("Bid cannot be placed, because you don't have enough money.");
			return false;
		}
		else if (bid <= i1.getValue()) {
			System.out.println("Not a valid bid.");
			return false;
		}
		else {
			i1.setValue(bid);
			setBiddedItem(i1);
			i1.setOwner(this);
			System.out.println("Bid of $" + String.format("%.2f", bid) + " placed.");
			return true;
		}
	}
	
	public String placeBidOnGUI(Item i1, float bid) {
		
		/*
		 * To be used by a human bidder, not an AI bidder
		 * Returns a string instead of printing a string to the console
		 * */
		
		if (bid > money) {
			return "Bid cannot be placed, because you don't have enough money.";
		}
		else if (bid <= i1.getValue()) {
			return "Not a valid bid. Must be greater than $" + i1.getValueString();
		}
		else {
			i1.setValue(bid);
			setBiddedItem(i1);
			i1.setOwner(this);
			return "Bid of $" + String.format("%.2f", bid) + " placed.";
		}
	}
	
	public void printAquiredItems() {
		System.out.println("The list of items aquired by the bidder " + this.name + " is:");
		for (Item AquiredItem : AquiredItemsFromBid)
		{
			System.out.println(AquiredItem.getName());
		}
		System.out.print("\n");
	}
	
	public String printAquiredItemsGUI() {
		String retval = "The list of items aquired by the bidder " + this.name + " is:<br>";
		for (Item AquiredItem : AquiredItemsFromBid)
		{
			retval += AquiredItem.getName() + "<br>";
		}
		return retval;
	}
	
	
	/*
	private float currentCounter = 0;

	private int min = 2500000; // change these numbers to make progam number 
	private int max = 10000000; // change these numbers to make progam number 

	private boolean canPlaceBid = false;
	private float AICounter = (float ) Math.random() * (max - min + 1) + min; // give me a number between min and max range */
	
	private boolean canPlaceBid = false;
	
	public void decreaseCoolDownPeriod() {
		
		/*
		 * This method prepares an AI bidder to bid by waiting one second and
		 *    randomly determining if the bidder can bid or not.
		 * */
		
/*		currentCounter += 1;
		// making it so the AI bids at random times
//		System.out.println("Current Counter is " + currentCounter);
//		System.out.println("AI counter is  " + AICounter);
		if((int)currentCounter > (int)AICounter)
		{
			canPlaceBid = true;
			currentCounter = 0;
//			System.out.println("Current Counter is " + currentCounter);
		}
		else
		{
//			System.out.println("Waiting for bidders ");
			canPlaceBid = false;
		
//			System.out.println(name + "counter is" + AICounter);
		}*/
		
		Date date = new Date();
		date.waitSec(1);
		
		Random rand = new Random();
		int randInt = rand.nextInt(5);
		
		canPlaceBid = (randInt > 2); // True 40% of the time
	}
	
	
	public void PlaceBidIfPossible(Item i1, float increaseAmount) {
		
		/*
		 * This method should be used by AI bidders and not human bidders.
		 * */
		
//		System.out.println("Item price is now " + i1.getValue());
		if(canPlaceBid && hasEnoughMoneyToAfford(i1))
		{
			// if the current bidder already has the item he should not place a bid 
			if(i1.GetOwningBidder() != this)
			{
				// the bidder has enough money to raise 
				if(this.money >= (i1.getValue() + increaseAmount))
				{
					i1.addValue(increaseAmount);
					//System.out.println("the item value is now " + i1.getValue());
					setBiddedItem(i1);
					i1.setOwner(this);
					//System.out.println("\n");

					//System.out.println("With everything that has been going on it is easy to miss that " + name + " has in fact placed a bid");
					
					//System.out.println("\n");
					
					System.out.println(name + " has placed a bid of $" + i1.getValueString());
				}
			}
				
		}
	}
	
	public void PlaceBidIfPossible(Item i1) {
		
		/*
		 * This method should be used by AI bidders and not human bidders.
		 * */
		
//		System.out.println("Item price is now " + i1.getValue());
		if(canPlaceBid && hasEnoughMoneyToAfford(i1))
		{
			// if the current bidder already has the item he should not place a bid 
			if(i1.GetOwningBidder() != this)
			{
				// the bidder has enough money to raise 
				if(this.money >= (i1.getValue() + 5))
				{
					i1.addValue(5);
					//System.out.println("the item value is now " + i1.getValue());
					setBiddedItem(i1);
					i1.setOwner(this);
					//System.out.println("\n");

					//System.out.println("With everything that has been going on it is easy to miss that " + name + " has in fact placed a bid");
					
					//System.out.println("\n");
					
					System.out.println(name + " has placed a bid of $" + i1.getValueString());
				}
			}
				
		}
	}
	
	public int PlaceBidIfPossibleGUI(Item i1) {
		
		/*
		 * This method should be used by AI bidders and not human bidders.
		 * */
		
//		System.out.println("Item price is now " + i1.getValue());
		if(canPlaceBid && hasEnoughMoneyToAfford(i1))
		{
			// if the current bidder already has the item he should not place a bid 
			if(i1.GetOwningBidder() != this)
			{
				// the bidder has enough money to raise 
				if(this.money >= (i1.getValue() + 5))
				{
					i1.addValue(5);
					//System.out.println("the item value is now " + i1.getValue());
					setBiddedItem(i1);
					i1.setOwner(this);
					//System.out.println("\n");

					//System.out.println("With everything that has been going on it is easy to miss that " + name + " has in fact placed a bid");
					
					//System.out.println("\n");
					
					//System.out.println(name + " has placed a bid of $" + i1.getValueString());
					String message ="UPDATE: " + name + " has placed a bid of $" + i1.getValueString();
					int buttonPressed = JOptionPane.showOptionDialog(null, message, "Auction update", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, null, null);
					return buttonPressed;
				}
			}
				
		}
		return 1; // No bid placed
	}

	
	public void setCanPlaceBid(boolean value) {
		canPlaceBid = value;
	}
	
	public void setBiddedItem(Item randomItem)
	{
		ItemBidderBiddedOn = randomItem;
	}
	
	public Item getBiddedItem()
	{
		return ItemBidderBiddedOn;
	}
	
	public boolean isListingValidItem(ArrayList<Seller> allSellers ) {
		
		/*
		 * Checks if an item exists within a seller's list.
		 * */
		
		Scanner scTemp = new Scanner(System.in);
		String possiblyValidItem = scTemp.next();
		for (Seller randomSeller : allSellers)
		{
			// printing all the items owned by the sellers 
			for(Item randomItem : randomSeller.getAllItems())
			{
				if (randomItem.getName().equals(possiblyValidItem))
				{
					setBiddedItem(randomItem);
					return true;
				}
			}
			
		}
	
		return false;
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMoneyString() {
		return String.format("%.2f", this.money);
	}
	
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
		
		Account bidderAccount = new Account(this.name, password);		
		bidderList.put(this.name, bidderAccount);
	}
	
	//validates user login by querying their username in a hashmap and comparing the hashed password with salt value to raw password.
	public boolean validateLogin(String name, String password) {
		boolean auth = false;
		
		if(bidderList.containsKey(name) == false) {
			System.out.println("Username not found.");
			auth = false;
		}
		
		if(bidderList.containsKey(name) == true) {
			Account a1 = bidderList.get(name);
			boolean passwordMatch = PasswordUtils.verifyUserPassword(password, a1.getSecurePassword(), a1.getSalt());
			
			//System.out.println("Password Entered: " + password + "\nReal Password: " + realPassword);
			if(passwordMatch) {
//				System.out.println("\nProvided Password: " + password);
//				System.out.println("Hashed Password: " + a1.getSecurePassword());
//				System.out.println("Salt Value: " + a1.getSalt());
//				System.out.println("");
				//System.out.println("Password match.");
				auth = true;
			}
		}
		return auth;
	}
}
