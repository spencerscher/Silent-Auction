package auctionSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import People.*;

public class SilentAuctionSystem {
	
	/*
	 * This class contains all the data for a silent auction system.
	 * */
	
	private ArrayList<Bidder> BidderPeople = new ArrayList<Bidder>();
	private ArrayList<Seller> SellerPeople = new ArrayList<Seller>();
	HashMap<String, Account> accountList = new HashMap<String, Account>();
	
	public void addBidder(Bidder randomBidder)
	{
		BidderPeople.add(randomBidder);
	}
	
	public void addSeller(Seller randomSeller)
	{
		SellerPeople.add(randomSeller);
	}
	
	public void printAllSeller()
	{
		System.out.println("The sellers in the Silient Auction Systems are:");
		for (Seller randomSeller : SellerPeople)
		{
			System.out.println(randomSeller.getName());
		}
		System.out.print("\n");
	}
	
	public String printAllSellerGUI()
	{
		String retval = "The sellers in the Silent Auction Systems are:<br>";
		for (Seller randomSeller : SellerPeople)
		{
			retval += "&nbsp;&nbsp;&nbsp;&nbsp;" + randomSeller.getName() + "<br>";
		}
		return retval;
	}
	
	public String printAllSellerGUIn()
	{
		String retval = "The sellers in the Silient Auction Systems are:\n";
		for (Seller randomSeller : SellerPeople)
		{
			retval += randomSeller.getName() + "\n";
		}
		return retval;
	}
	
	public ArrayList<Seller> getAllSeller()
	{
		return SellerPeople;
	}
	
	public ArrayList<Bidder> getAllBidders()
	{
		return BidderPeople;
	}
	
	public void printAllBidder()
	{
		System.out.println("The Bidders in the Silient Auction Systems are:");
		for (Bidder randomBidder: BidderPeople)
		{
			System.out.println(randomBidder.getName());
		}
		System.out.print("\n");
	}
	
	public String printAllBidderGUI()
	{
		String retval = "The Bidders in the Silent Auction Systems are:<br>";
		for (Bidder randomBidder: BidderPeople)
		{
			retval += "&nbsp;&nbsp;&nbsp;&nbsp;" + randomBidder.getName() + "<br>";
		}
		return retval;
	}
	
	public String printAllBidderGUIn()
	{
		String retval = "The Bidders in the Silient Auction Systems are:\n";
		for (Bidder randomBidder: BidderPeople)
		{
			retval += randomBidder.getName() + "\n";
		}
		return retval;
	}
	
	private String PlayerRoll;
	public void setPlayerRoll(String StringResult)
	{
		this.PlayerRoll = StringResult;
	}
	
	public String getPlayerRoll()
	{
		return PlayerRoll;
	}
	
	public boolean isValidRoll(String playerName)
	{
		System.out.println("Hello " + playerName + " are you a bidder or a seller");
		Scanner sc2 = new Scanner(System.in);
		String PlayerRoll = sc2.nextLine();
		if(PlayerRoll.equals("bidder") || PlayerRoll.equals("seller"))
		{
			setPlayerRoll(PlayerRoll);
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public void printAllItems() {
		for (int i = 0; i < SellerPeople.size(); i++) {
			Seller tempSeller = SellerPeople.get(i);
			for (int j = 0; j < tempSeller.getAllItems().size(); j++) {
				System.out.println(tempSeller.getAllItems().get(j));
			}
		}
	}
	
	public String printAllItemsGUI() {
		String retval = "";
		for (int i = 0; i < SellerPeople.size(); i++) {
			Seller tempSeller = SellerPeople.get(i);
			for (int j = 0; j < tempSeller.getAllItems().size(); j++) {
				retval += "&nbsp;&nbsp;&nbsp;&nbsp;" + tempSeller.getAllItems().get(j) + "<br>";
			}
		}
		return retval;
	}
	public String printAllItemsForDropBox()
	{
		String retval = "";
		for (int i = 0; i < SellerPeople.size(); i++) {
			Seller tempSeller = SellerPeople.get(i);
			for (int j = 0; j < tempSeller.getAllItems().size(); j++) {
				retval +=   tempSeller.getAllItems().get(j) + "<br>";
			}
		}
		return retval;
	}
	public String printAllItemsGUIn() {
		String retval = "";
		for (int i = 0; i < SellerPeople.size(); i++) {
			Seller tempSeller = SellerPeople.get(i);
			for (int j = 0; j < tempSeller.getAllItems().size(); j++) {
				retval += tempSeller.getAllItems().get(j) + "\n";
			}
		}
		return retval;
	}
	
	public Item getItem(String itemName) {
		
		for (int i = 0; i < SellerPeople.size(); i++) {
			Seller tempSeller = SellerPeople.get(i);
			for (int j = 0; j < tempSeller.getAllItems().size(); j++) {
				if (itemName.contains(tempSeller.getAllItems().get(j).getName())) {
					return tempSeller.getAllItems().get(j);
				}
			}
		}
		
		return null;
	}
	
	public void CreateAccount(String username, String password) {
		Account account = new Account();
		
		account.setPassword(username);
		account.setPassword(password);
		
		accountList.put("login", account);
	}
	
	public String returnAllInfo() {
		String retval = "<html><font size='5';>";
		
		retval += printAllSellerGUI() + "<br>";
		retval += printAllBidderGUI() + "<br>";
		retval += "The list of items are:<br>";
		retval += printAllItemsGUI() + "<br>";
		
		retval += "List of items by seller...<br><br>";
		
		for (Seller s : SellerPeople) {
			retval += s.getName() + ":<br>";
			retval += s.printItemsGUI() + "<br>";
		}
		
		return retval + "</font></html>";
	}
}
