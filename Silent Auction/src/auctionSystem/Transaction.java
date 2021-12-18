package auctionSystem;

import People.*;

public class Transaction {
	
	/*
	 * This class represents the transaction of an item from a seller to a bidder.
	 * */
	
	private Seller SellerOfItem;
	private Bidder BidderOfItem;
	
	public void setSeller(Seller randomSeller)
	{
		 SellerOfItem = randomSeller;
	}
	
	public void setBidder(Bidder randomBidder)
	{
		BidderOfItem = randomBidder;
	}
	
	public void startTransaction()
	{
		
		/*
		 * This method actually completes the transaction, provided all the fields have been properly set.
		 * This method works for AI as well as human bidders and sellers.
		 * */
		
		if (BidderOfItem != null) {
			SellerOfItem.addCashRecieved(SellerOfItem.getAuctionedItem().getValue());
			BidderOfItem.deductMoney(SellerOfItem.getAuctionedItem().getValue());
			
			SellerOfItem.getAuctionedItem().setOwner(BidderOfItem);
			
			BidderOfItem.addAquiredItemsFromBid(SellerOfItem.getAuctionedItem());
			SellerOfItem.removeItem(SellerOfItem.getAuctionedItem());
		}
		else { // BidderOfItem is null, meaning there was no bidder
			System.out.println("Unfortunately there were no bidder" + " for the "
					+ SellerOfItem.getAuctionedItem().getName() + " item");
		}
	}	
}
