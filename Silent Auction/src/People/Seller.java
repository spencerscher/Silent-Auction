package People;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import Security.PasswordUtils;
import auctionSystem.Account;
import auctionSystem.Date;
import auctionSystem.Item;


public class Seller {
	
	/*
	 * This class represents a seller.
	 * This class contains several features, including state-of-the-art security
	 *   and AI bidding.
	 * */
	
	public ArrayList<Item> items = new ArrayList<Item>();
	private String name;
	private String password;
	private float cashRecieved = 0;
	private Item AuctionedItem = new Item();
	HashMap<String, Account> sellerList = new HashMap<String, Account>();
	
	public Seller() {
		this.name = "";
		this.password = "";
		this.items = new ArrayList<Item>();
	}
	
	//creates a new seller and adds their account with user/pass to hashmap (will convert to a db entry soon).
	public Seller(String username, String password) {
		
		this.name = username;
		this.password = password;
		this.items = new ArrayList<Item>();
		
		// Security
		Account sellerAccount = new Account(username, password);
		sellerList.put(this.name, sellerAccount);
	}
	
	public float getCashRecieved()
	{
		return this.cashRecieved;
	}
	
	public String getCashRecievedString() {
		return String.format("%.2f", this.cashRecieved);
	}
	
	public void setPassword(String password) {
		this.password = password;
		
		Account sellerAccount = new Account(this.name, password);
		sellerList.put(this.name, sellerAccount);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addCashRecieved(float bidderInvestedCash)
	{
		cashRecieved += bidderInvestedCash;
	}
	
	public String getName() {
		return name;
	}
	
	public String printItemsGUI()
	{
		String retval = "";
		for (Item randomItem : items)
		{
			retval += "&nbsp;&nbsp;&nbsp;&nbsp;" + randomItem.getName() + "<br>";
		}
		return retval;
	}
	public String printItemsForSe()
	{
		String retval = "";
		for (Item randomItem : items)
		{
			retval += "&nbsp;&nbsp;&nbsp;&nbsp;" + randomItem.getName() + "<br>";
		}
		return retval;
	}
	
	public String printItemsForSellerDropDown()
	{
		String retval = "";
		for (Item randomItem : items)
		{
			retval += randomItem.getName() + "\n";
		}
		return retval;
	}
	
	public void printItems()
	{
	
		for (Item randomItem : items)
		{
			System.out.println(randomItem.getName());
		}
		System.out.print("\n");
	}
	
	public ArrayList<Item> getAllItems()
	{
		return items;
	}
	
	public void setAuctionedItem(Item randomItem)
	{
		AuctionedItem = randomItem;
	}
	
	public Item getAuctionedItem()
	{
		return AuctionedItem;
	}
	
	public boolean isListingValidItem()
	{
		
		/*
		 * Receives an item from a user and checks to see if it exists within the seller's list.
		 * */
		
		Scanner scTemp = new Scanner(System.in);
		String possiblyValidItem = scTemp.nextLine();
		
		for(Item randomItem : items)
		{
//			System.out.println(" the random item name is " + randomItem.getName() ) ;
//			System.out.print("the item passed in is named " + possiblyValidItem);
			if (randomItem.getName().contains(possiblyValidItem))
			{
				setAuctionedItem(randomItem);
				return true;
			}
		}
		return false;
		
	}
	
	public boolean isListingValidItemGUI(String possiblyValidItem)
	{
		
		/*
		 * Receives an item from a user and checks to see if it exists within the seller's list.
		 * */
		
		//Scanner scTemp = new Scanner(System.in);
		//String possiblyValidItem = scTemp.nextLine();
		for(Item randomItem : items)
		{
			System.out.println(  randomItem.getName());
			System.out.println( possiblyValidItem );

			if (possiblyValidItem.contains(randomItem.getName()))
			{
				System.out.println("This is a valid item " );
				setAuctionedItem(randomItem);
				return true;
			}
		}
		return false;
		
	}
	
	public void addItem(Item randomItem)
	{
		randomItem.setOwner(this);
		items.add(randomItem);
	}
	
	public void removeItem(Item randomItem)
	{
		items.remove(randomItem);
		if (AuctionedItem == randomItem) {
			AuctionedItem = new Item();
		}
	}
	
	public int startAuctionAI(Item item) {
		
		/*
		 * This method gets ready for an auction of AI bidders when the user is a seller.
		 * Note that this method does not actually run the auction, just gets it ready.
		 * */
		
		AuctionedItem = item;
		
		Random rand = new Random();
		//int ItemDeadline = rand.nextInt(3) + 1; // Randomly chooses deadline from 1 - 3
		int ItemDeadline = 1; // Hardcode to 1 for now for testing purposes
		
		Date d1 = new Date();
		AuctionedItem.setDate(d1);
		AuctionedItem.setStartTime();
		AuctionedItem.setDeadline(ItemDeadline);
		
		return ItemDeadline;
	}
	
	//validates user login by querying their username in a hashmap and comparing the hashed password with salt value to raw password.
	public boolean validateLogin(String name, String password) {
		boolean auth = false;
		
		if(sellerList.containsKey(name) == false) {
			System.out.println("Username not found.");
			auth = false;
		}
		
		if(sellerList.containsKey(name) == true) {
			Account a1 = sellerList.get(name);
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
