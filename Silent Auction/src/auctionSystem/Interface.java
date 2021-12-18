package auctionSystem;

import java.sql.Savepoint;
import java.util.*;
import People.*;

public class Interface {
	
	/*
	 * The Interface class provides a means of running the silent auction system.
	 * The class receives a SilentAuctionSystem object, then can run it.
	 * run() is the method used to run the silent auction.
	 * 
	 * this.state contains the state of the system, and the run() method uses
	 *   a switch statement to control which code runs.
	 * 
	 * The code for the bidder portal is in bidder(), login is in login(), etc
	 * */
	
	private SilentAuctionSystem sa;
	private String state;
	private Scanner scanner;
	private Bidder curBidder;
	private Seller curSeller;
	private Item curItem;

	public Interface() {
		this.sa = null;
		this.state = "welcome";
		this.scanner = new Scanner(System.in);
		this.curBidder = null;
		this.curSeller = null;
	}

	public Interface(SilentAuctionSystem sa) {
		this.sa = sa;
		this.state = "welcome";
		this.scanner = new Scanner(System.in);
		this.curBidder = null;
		this.curSeller = null;
	}

	public void run(SilentAuctionSystem sa) {
		
		/*
		 * (Overloaded method)
		 * */
		
		this.sa = sa;
		this.run();
	}

	public void run() {
		
		/*
		 * The means of running the silent auction
		 * */
		
		if (this.sa == null) {
			System.out.println("ERROR - no SilentAuctionSystem given.");
			return;
		}

		while (true) {
			
			// Check where the state is
			// (The while loop is exited if the state is "quit"
			
			switch (this.state) {
			case "welcome":
				this.welcome();
				break;
			case "login":
				this.login();
				break;
			case "create_account":
				this.create_account();
				break;
			case "bidder":
				this.bidder();
				break;
			case "seller":
				this.seller();
				break;
			case "quit":
				System.out.println("Exiting silent auction.");
				this.scanner.close();
				return;
			default:
				// Default means an error occurred, and should exit
				System.out.println("Error in state. State is: " + this.state + ". Exiting.");
				this.scanner.close();
				return;
			}
		}
	}

	private void welcome() {
		
		/*
		 * The welcome screen
		 * This is the default state when the Interface class begins running
		 * 
		 * From here, the user can create an account, login, or exit
		 * */

		System.out.println("Welcome to the silent auction!");
		System.out.println("What would you like to do?");
		System.out.println("   1. Login");
		System.out.println("   2. Create account");
		System.out.println("   3. Quit");

		String input = scanner.next();

		while (true) {
			if (input.equals("1")) {
				this.state = "login";
				break;
			} else if (input.equals("2")) {
				this.state = "create_account";
				break;
			} else if (input.equals("3")) {
				this.state = "quit";
				break;
			} else {
				System.out.println("Not a valid option, try again.");
				input = scanner.next();
			}
		}
	}

	private void login() {
		
		/*
		 * This allows a user to login to an existing bidder or seller account
		 * 
		 * this.curBidder or this.curSeller will keep track of who the user is
		 * */
		
		System.out.print("Enter Username: ");
		String username = scanner.next();
		
		System.out.print("Enter Password: ");
		String password = scanner.next();
		
		// Searches through bidders to see if the entered info is a bidder account
		ArrayList<Bidder> bidders = this.sa.getAllBidders();
		for (int i = 0; i < bidders.size(); i++) {
			Bidder tempBidder = bidders.get(i);
			if (tempBidder.getName().equals(username)) {
				if (tempBidder.validateLogin(username, password) == true) {
					this.curBidder = tempBidder;
					this.curSeller = null;
					this.state = "bidder";
					return;
				}
			}
		}
		
		// Searches through sellers to see if the entered info is a seller account
		ArrayList<Seller> sellers = this.sa.getAllSeller();
		for (int i = 0; i < sellers.size(); i++) {
			Seller tempSeller = sellers.get(i);
			if (tempSeller.getName().equals(username)) {
				if (tempSeller.validateLogin(username, password) == true) {
				this.curBidder = null;
				this.curSeller = tempSeller;
				this.state = "seller";
				return;
				}
			}
		}

		// If it reaches here, we didn't find a bidder or seller, and this.state is
		// still "login"
		System.out.println("Invalid login, please try again.");
	}

	private void create_account() {
		
		/*
		 * This method allows the user to create a bidder or seller account, then logs the user in
		 * */

		System.out.print("Are you a bidder or a seller? ");
		String input = scanner.next();
		while (true) {
			if (input.equals("bidder") || input.equals("seller")) {
				break;
			} else {
				System.out.println("Not a valid option, try again.");
				input = scanner.next();
			}
		}

		System.out.print("Enter Username: ");
		String username = scanner.next();
		System.out.print("Enter Password: ");
		String password = scanner.next();
		
		// Create the bidder or seller account
		if (input.equals("seller")) {
			this.curSeller = new Seller(username, password);
			this.sa.addSeller(this.curSeller);
		} else {
			this.curBidder = new Bidder(username, password);
			this.curBidder.setBidderMoney(500.0f); // Initialize bidders with $500
			this.sa.addBidder(this.curBidder);
		}
		
		System.out.print(input.toUpperCase() + " account succesfully created. Logging you in...\n\n");

		this.state = input; // Logs in as either bidder or seller
	}

	private void bidder() {
		
		/*
		 * This method runs all the code for the bidder
		 * The switch(userInput) contains the code for all the different actions the
		 *    bidder can take
		 * (Note that the logout option (userInput == 6) is what allows the user to
		 *    return to the welcome page)
		 * */
		
		// Print welcome
		System.out.println("Hello " + curBidder.getName() + " the bidder, what would you like to do?\n");
		System.out.println("1. View list of items available to bid on");
		System.out.println("2. Bid on an item");
		System.out.println("3. See how much money you have");
		System.out.println("4. View items you have purhcased");
		System.out.println("5. Buy a lottery ticket for $3");
		System.out.println("6. Logout");
		System.out.println("(Enter a number)");
		
		// Get user input
		int userInput = scanner.nextInt();
		// handle if the user put in the wrong input
		while (userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4 && userInput != 5 && userInput != 6) {
			System.out.println("Sorry " + curBidder.getName() + " that is not an option");
			System.out.println("These are your options:");
			System.out.println("1. View list of items available to bid on");
			System.out.println("2. Bid on an item");
			System.out.println("3. View items you have purchased");
			System.out.println("4. Logout");
			System.out.println("(Enter a number)");
		}
		
		switch(userInput) {
		case 1: // View list of items
			System.out.println("\nThese are the items available:");
			this.sa.printAllItems();
			System.out.print("\n");
			break;
		
		case 2: // Bid on item
			System.out.println("\nThese are the items available:");
			this.sa.printAllItems();
			
			System.out.println("\nYou have $" + curBidder.getMoneyString());
			
			System.out.println("\nChoose which item to bid on (type in name)");
			String userInput2 = scanner.nextLine();
			userInput2 = scanner.nextLine(); // Have twice because the 1st time gets a stray \n
			curItem = this.sa.getItem(userInput2);
			
			while(curItem == null) {
				System.out.println("I'm sorry, that's not a valid item, please try again:");
				userInput2 = scanner.nextLine();
				curItem = this.sa.getItem(userInput2);
			}
			
			curSeller = curItem.GetOwningSeller();
			
			int time = curItem.GetOwningSeller().startAuctionAI(curItem);
			
			System.out.println("Bidding on item " + curItem.getName());
			System.out.println("You have " + time + " minutes");
			
			while (curItem.checkIfDeadlineHasNotPassed()) {
				System.out.println("Place bid greater than $" + curItem.getValueString() + " (you have $" + curBidder.getMoneyString() + ")");
				curBidder.placeBidOn(curItem, scanner.nextFloat());
				
				int numBidders = sa.getAllBidders().size();
				for (Bidder randomBidder : sa.getAllBidders()) {
					if (randomBidder == curBidder)
						continue;
					
					Random rand = new Random();
					int randInt = rand.nextInt(100);
					
					if ((randInt % numBidders) < 2) {
						randomBidder.setCanPlaceBid(true);
						randomBidder.PlaceBidIfPossible(curItem);
					}
				}
			}
			
			// Print who got the item
			if (curItem.GetOwningBidder() == curBidder) {
				System.out.println("You have placed the winning bid of $" + curItem.getValueString());
				System.out.print("Auction for item " + curItem.getName() + " has finished.\n\n");
			}
			else {
				System.out.println("Another bidder has placed the winning bid.");
				System.out.println(curItem.GetOwningBidder().getName() + " has placed the winning bid of $" + curItem.getValueString());
				System.out.print("Auction for item " + curItem.getName() + " has finished.\n\n");
			}
			
			// Create Transaction class here to complete the transaction for whoever won the item
			Transaction t1 = new Transaction();
			t1.setBidder(curItem.GetOwningBidder());
			t1.setSeller(curSeller);
			t1.startTransaction();
			break;
		
		case 3: // Show money
			System.out.print("You have $" + curBidder.getMoneyString() + "\n\n");
			break;
		
		case 4: // Show acquired items
			curBidder.printAquiredItems();
			break;
			
		case 5: // Play the lottery
			Random rand = new Random();
			int drawnNumber = rand.nextInt(20) + 1;
			int winningNumber = rand.nextInt(20) + 1;
			if (curBidder.getMoney() < 3) {
				System.out.print("You don't have enough money to but a lottery ticket.\n\n");
			}
			else if (drawnNumber == winningNumber) {
				System.out.println("Your lotto number is " + drawnNumber + ". The winning number is " + winningNumber + ".");
				System.out.print("You won the lottery! You won $1000\n\n");
				curBidder.setBidderMoney(curBidder.getMoney() + 1000.0f);
			}
			else {
				System.out.println("Your lotto number is " + drawnNumber + ". The winning number is " + winningNumber + ".");
				System.out.print("You didn't win the lottery.\n\n");
				curBidder.setBidderMoney(curBidder.getMoney() - 3.0f);
			}
			break;
		
		case 6: // Logout (change this.state to "welcome")
			this.state = "welcome";
			System.out.print("\n");
			break;
		}
	}

	private void seller() {
		
		/*
		 * This method contains all the code for the seller
		 * (Note that if userInput == 5, the user will return to the welcome page)
		 * */
		
		// Print welcome
		System.out.println("Hello " + curSeller.getName() + " the seller, what would you like to do?\n"
				+ "Here are your options\n" + "1. List all your items\n" + "2. Auction an item\n"
				+ "3. Add an item\n" + "4. See how much money you have\n" + "5. Logout\n"
				+ "Remember that the console will only take in a number for this part.");
		
		// Get user input
		int userInput = scanner.nextInt();
		// handle if the user put in the wrong input
		while (userInput != 1 && userInput != 2 && userInput != 3 && userInput != 4 && userInput != 5) {
			System.out.println("Sorry " + curSeller.getName() + " that's not an option");
			System.out.println("Here are your options\n" + "1. List all your items\n" + "2. Auction an item\n"
					+ "3. Add an item\n" + "4. Logout\n"
					+ "please pick one of these options [console will only accept numbers for this]");
			userInput = scanner.nextInt();
		}
		
		
		// Display owned items
		if (userInput == 1) {
			System.out.println("Your items are ");
			curSeller.printItems();
		}
		
		// Begin an auction
		else if (userInput == 2) {
			System.out.println("Which item would you like to auction");
			System.out.println("Your items are ");
			curSeller.printItems();
			System.out.println("Remember that for this part you need to provide the name of the item ");
			while (!(curSeller.isListingValidItem())) {
				System.out.println("Sorry " + curSeller.getName() + " That's not an item you own");
				System.out.println("Which item would you like to auction");
				curSeller.printItems();
				System.out.println("Remember that for this part you need to provide the name of the item ");
			}

			System.out.println("Enter the item deadline " + "[this will be taken as minutes]");

			int ItemDeadline = scanner.nextInt();
			Date d1 = new Date();
			curSeller.getAuctionedItem().setDate(d1);
			curSeller.getAuctionedItem().setStartTime();
			curSeller.getAuctionedItem().setDeadline(ItemDeadline);
//			item1.setStartTime();
//			item1.setDeadline(ItemDeadline);

			//Bidder b1 = sa.getAllBidders().get(0);
			//Bidder b2 = sa.getAllBidders().get(1);

			while (curSeller.getAuctionedItem().checkIfDeadlineHasNotPassed()) {
				//b1.decreaseCoolDownPeriod();
				//b2.decreaseCoolDownPeriod();
				// this is where the auction will take place

				// this is where the items finds its bidders and sellers
				// example
//				Player.getAuctionedItem().setBidder(b1);
				// start silient auction session

				for (Bidder randomBidder : sa.getAllBidders()) {
					randomBidder.decreaseCoolDownPeriod();
					randomBidder.PlaceBidIfPossible(curSeller.getAuctionedItem());
				}

			}

			if (curSeller.getAuctionedItem().GetOwningBidder() == null) {
				System.out.println("Unfortunately there were no bidder" + " for the "
						+ curSeller.getAuctionedItem().getName() + " item");
				System.out.print("Auction for item " + curSeller.getAuctionedItem().getName() + " has finished.\n\n");
				curSeller.setAuctionedItem(null);
			} else {
				// making the transaction here
				Transaction silientTransaction = new Transaction();
				System.out.println("The winning bidder of the " + curSeller.getAuctionedItem().getName() + " is "
						+ curSeller.getAuctionedItem().GetOwningBidder().getName());
				System.out.println("The item was purchased for " + curSeller.getAuctionedItem().getValueString());
				// this is where we would ask for the transaction
				silientTransaction.setSeller(curSeller);
				silientTransaction.setBidder(curSeller.getAuctionedItem().GetOwningBidder());
				
				System.out.print("Auction for item " + curSeller.getAuctionedItem().getName() + " has finished.\n\n");
				
				silientTransaction.startTransaction();
				// was testing to see if the player cash was actually deducted
//				System.out.println(" his cash is now " +Player.getAuctionedItem().GetOwningBidder().getMoney());
//				System.out.println(" his cash is now " +Player.getCashRecieved());
			}
		}
		
		// Add a new item
		else if (userInput == 3) {
			Date d4 = new Date();
			Item item4 = new Item();
			System.out.print("Name of item: ");
			item4.setName(scanner.next());
			System.out.print("Value of item (minimum bid): ");
			item4.setValue(scanner.nextFloat());
			item4.setDate(d4);
			
			curSeller.addItem(item4);
			System.out.print("Item successfully added.\n\n");
		}
		
		// Show money
		else if (userInput == 4) {
			System.out.print("You have $" + curSeller.getCashRecievedString() + "\n\n");
		}
		
		// Logout (set this.state to "welcome")
		else if (userInput == 5) {
			System.out.print("Logging out...\n\n");
			this.state = "welcome";
		}
	}
}
