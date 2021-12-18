package test;

import People.*;
import auctionSystem.Date;
import auctionSystem.Item;
import auctionSystem.SilentAuctionSystem;
import auctionSystem.Interface;

public class TestNoGui {
	
	// This first part was copied from TestAuction

	public static void main(String[] args) {
		
		SilentAuctionSystem sa1 = new SilentAuctionSystem();
		
		/*
		 * This main method initializes a silent auction system, then runs it.
		 * The silent auction is initialized by creating items, bidders, and sellers
		 *    and putting them into the system
		 * (Note that more items, bidders, and sellers can be added once the system is
		 *    running.)
		 * */
		
		// Create items
		// (An item needs a date otherwise it won't work)
		Date d1 = new Date();
		Item item1 = new Item();
		item1.setName("Yeezy 350");
		item1.setDate(d1);
		item1.setValue(160.0f);
		
		Date d2 = new Date();
		Item item2 = new Item();
		item2.setDate(d2);
		item2.setName("Air Jordan 1");
		item2.setValue(100.0f);
		
		Date d3 = new Date();
		Item item3 = new Item();
		item3.setDate(d3);
		item3.setName("Playstation 5");
		item3.setValue(250.0f);
		
		Date d4 = new Date();
		Item item4 = new Item();
		item4.setName("Xbox Series X");
		item4.setDate(d4);
		item4.setValue(180.0f);
		
		// Create bidders
		
		Bidder b1 = new Bidder();
		b1.setName("Lewis");
		b1.setPassword("password");
		b1.setBidderMoney(500.0f);
		
		Bidder b2 = new Bidder();
		b2.setName("Nasser");
		b2.setPassword("123456");
		b2.setBidderMoney(505.0f);
		
		Bidder b3 = new Bidder();
		b3.setName("Marefat");
		b3.setPassword("qwerty");
		b3.setBidderMoney(500.0f);
		
		// Create sellers
		
		Seller s1 = new Seller();
		s1.setName("Spencer");
		s1.setPassword("ece373!");
		s1.addItem(item1);
		s1.addItem(item3);
		
		Seller s2 = new Seller();
		s2.setName("Michael");
		s2.setPassword("ece373?");
		s2.addItem(item4);
		s2.addItem(item2);
		
		// Add the bidders and sellers to the silent auction
		
		sa1.addBidder(b1);
		sa1.addBidder(b2);
		sa1.addBidder(b3);
		sa1.addSeller(s1);
		sa1.addSeller(s2);
		
		// Print the initialization info
		
		System.out.print("The Silent Auction System has been initialized.\n\n");
		sa1.printAllBidder();
		sa1.printAllSeller();
		
		// Run the silent auction using the Interface class
		
		Interface inter = new Interface(sa1);
		inter.run();
		
	}

}
