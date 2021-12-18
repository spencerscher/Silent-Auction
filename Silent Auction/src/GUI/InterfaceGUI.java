package GUI;

import java.sql.Savepoint;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.border.MatteBorder;


import java.util.*;

import People.*;
import auctionSystem.*;
import auctionSystem.Date; // Need to explicitly import; otherwise it's ambiguous

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//test

public class InterfaceGUI extends JFrame implements ActionListener {
	
	private static final String DropDownButtonFactory = null;
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
	
	// For running the silent auction
	private SilentAuctionSystem sa;
	//private String state;
	//private Scanner scanner;
	private Bidder curBidder;
	private Seller curSeller;
	private Item curItem;
	
	// For GUI
	
	// General use
	private JLabel wide0;
	private JLabel wide10a;
	private JLabel wide10b;
	private JLabel wide20;
	private JLabel wide30;
	private JLabel wide40;
	private JLabel wide50;
	private JLabel one;
	private JLabel two;
	private JLabel three;
	private JLabel four;
	private JLabel five;
	private JLabel six;
	private JLabel seven;
	private JButton back;
	
	private JLabel titleJL;
	private String titleFormatO;
	private String titleFormatC;
	
	private int backCurrent;
	private int backNext;
	
	// Welcome screen
	private JLabel dollarImageJL;
	private JButton loginJB;
	private JButton createAccountJB;
	private JLabel securityMessage = new JLabel("All accounts are stored securely using MD-5 encryption.");
	private JButton showAllInfoJB;
	
	// Login
	private CustomTextField usernameLoginTF = new CustomTextField(20);
	private CustomTextField passwordLoginTF = new CustomTextField(20);
	private JButton loginButtonJB;
	private JLabel label = new JLabel("Username: ");
	private JLabel label2 = new JLabel("Password: ");
	
	// Create account
	private CustomTextField usernameCreateAccountTF = new CustomTextField(20);
	private CustomTextField passwordCreateAccountTF = new CustomTextField(20);
	private JButton createAccountBidderJB;
	private JButton createAccountSellerJB;
	
	// Bidder options
	private JButton viewItemsAvailableJB;
	private JButton bidJB;
	private JButton viewMoneyBidderJB;
	private JButton viewPurchasedItemsJB;
	private JButton lotteryJB;
	private JButton logoutJB;

	
	// Seller options
	private JButton viewItemsOwnedJB;
	private JButton auctionItemJB;
	private JButton addItemJB;
	private JButton viewMoneyJB;
	private JButton logoutSellerJB;
    

	public InterfaceGUI(SilentAuctionSystem sa) {
		super("Silent Auction");
		
		this.sa = sa;
		//this.state = "welcome";
		//this.scanner = new Scanner(System.in);
		this.curBidder = null;
		this.curSeller = null;
		
		// Stuff that won't change amongst the screens
		setSize(650, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// NOTE: the center-align of text should take within the JLabels, not the JFrame
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		wide0 = new JLabel();
		wide0.setPreferredSize(new Dimension(1000,0));
		wide10a = new JLabel();
		wide10a.setPreferredSize(new Dimension(1000,10));
		wide10b = new JLabel();
		wide10b.setPreferredSize(new Dimension(1000,10));
		wide20 = new JLabel();
		wide20.setPreferredSize(new Dimension(1000,20));
		wide30 = new JLabel();
		wide30.setPreferredSize(new Dimension(1000,30));
		wide40 = new JLabel();
		wide40.setPreferredSize(new Dimension(1000,40));
		one = new JLabel();
		one.setPreferredSize(new Dimension(1000,10));
		two = new JLabel();
		two.setPreferredSize(new Dimension(1000,10));
		three = new JLabel();
		three.setPreferredSize(new Dimension(1000,10));
		four = new JLabel();
		four.setPreferredSize(new Dimension(1000,10));
		five = new JLabel();
		five.setPreferredSize(new Dimension(1000,10));
		six = new JLabel();
		six.setPreferredSize(new Dimension(1000,10));
		seven = new JLabel();
		seven.setPreferredSize(new Dimension(1000,10));

		back = new JButton("Back");
		//back.setPreferredSize(new Dimension(60,30));
		back.addActionListener(this);
		//back.setFont(new Font("Times", 1, 9));
		
		// JLabel for title and formatting
		titleJL = new JLabel();
		titleJL.setPreferredSize(new Dimension(1000, 100));
		titleJL.setHorizontalAlignment(SwingConstants.CENTER);
		titleFormatO = "<html><font size='14';><strong>";
		titleFormatC = "</strong></font></html>";
		
		// Image
		// NOTE: feel free to replace this with a better picture, be sure to adjust the dimensions as necessary
		dollarImageJL = new JLabel(getScaledImage("images/dollar1.png", 250, 100));
		dollarImageJL.setPreferredSize(new Dimension(1000, 100));
		dollarImageJL.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Welcome screen buttons
		loginJB = new JButton("Login");
		loginJB.addActionListener(this);
		loginJB.setPreferredSize(new Dimension(200, 30));
		createAccountJB = new JButton("Create Account");
		createAccountJB.addActionListener(this);
		createAccountJB.setPreferredSize(new Dimension(200, 30));
		showAllInfoJB = new JButton("Show Auction Info");
		showAllInfoJB.addActionListener(this);
		showAllInfoJB.setPreferredSize(new Dimension(200, 30));
		
		// Login inputs
		usernameLoginTF.setPlaceholder("Username");
		passwordLoginTF.setPlaceholder("Password");
		loginButtonJB = new JButton("Login");
		loginButtonJB.addActionListener(this);
		
		// Create account inputs
		usernameCreateAccountTF.setPlaceholder("Username");
		passwordCreateAccountTF.setPlaceholder("Password");
		createAccountBidderJB = new JButton("Create Bidder Account");
		createAccountBidderJB.addActionListener(this);
		createAccountSellerJB = new JButton("Create Seller Account");
		createAccountSellerJB.addActionListener(this);
		
		// Create bidder options
		viewItemsAvailableJB = new JButton("View items up for auction");
		viewItemsAvailableJB.setPreferredSize(new Dimension(250, 30));
		viewItemsAvailableJB.addActionListener(this);
		bidJB = new JButton("Begin bidding");
		bidJB.setPreferredSize(new Dimension(250, 30));
		bidJB.addActionListener(this);
		viewMoneyBidderJB = new JButton("View money available");
		viewMoneyBidderJB.setPreferredSize(new Dimension(250, 30));
		viewMoneyBidderJB.addActionListener(this);
		viewPurchasedItemsJB = new JButton("View purchased items");
		viewPurchasedItemsJB.setPreferredSize(new Dimension(250, 30));
		viewPurchasedItemsJB.addActionListener(this);
		lotteryJB = new JButton("Purchase lottery ticket ($3)");
		lotteryJB.setPreferredSize(new Dimension(250, 30));
		lotteryJB.addActionListener(this);
		logoutJB = new JButton("Logout");
		logoutJB.addActionListener(this);
		
		// Create seller options
		viewItemsOwnedJB = new JButton("View your owned items");
		viewItemsOwnedJB.setPreferredSize(new Dimension(250, 30));
		viewItemsOwnedJB.addActionListener(this);
		auctionItemJB = new JButton("Auction an item");
		auctionItemJB.setPreferredSize(new Dimension(250, 30));
		auctionItemJB.addActionListener(this);
		addItemJB = new JButton("Add an item to your inventory");
		addItemJB.setPreferredSize(new Dimension(250, 30));
		addItemJB.addActionListener(this);
		viewMoneyJB = new JButton("View your money");
		viewMoneyJB.setPreferredSize(new Dimension(250, 30));
		viewMoneyJB.addActionListener(this);
		logoutSellerJB = new JButton("Logout");
		logoutSellerJB.addActionListener(this);
		
		// setVisible(true); is called within buildWelcomeScreen
		
		//run();
		buildWelcomeScreen();
	}
	
	public void actionPerformed(ActionEvent event) {
		JButton source = (JButton)event.getSource();
		
		// Welcome screen
		if(source.equals(loginJB)) {
			buildLogin();
		}
		else if (source.equals(createAccountJB)) {
			buildCreateAccount();
		}
		else if(source.equals(showAllInfoJB)) {
			buildShowAllInfo();
		}
		// Login screen
		else if(source.equals(loginButtonJB)) {
			login();
		}
		else if (source.equals(back)) {
			goToBack();
		}
		// Create account screen
		else if(source.equals(createAccountBidderJB)) {
			create_account("bidder");
		}
		else if(source.equals(createAccountSellerJB)) {
			create_account("seller");
		}
		// Bidder portal
		else if(source.equals(viewItemsAvailableJB)) {
			bidder(1);
		}
		else if(source.equals(bidJB)) {
			bidder(2);
		}
		else if(source.equals(viewMoneyBidderJB)) {
			bidder(3);
		}
		else if(source.equals(viewPurchasedItemsJB)) {
			bidder(4);
		}
		else if(source.equals(lotteryJB)) {
			bidder(5);
		}
		else if(source.equals(logoutJB)) {
			bidder(6);
		}
		// Seller portal
		else if(source.equals(viewItemsOwnedJB)) {
			seller(1);
		}
		else if(source.equals(auctionItemJB)) {
			seller(2);
		}
		else if(source.equals(addItemJB)) {
			seller(3);
		}
		else if(source.equals(viewMoneyJB)) {
			seller(4);
		}
		else if(source.equals(logoutSellerJB)) {
			seller(5);
		}
	}
	
	private ImageIcon getScaledImage(String path, int width, int height) {
		/*
			Adapted from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
				(the answer by tirz)
			Although the image maybe is a little blurry? That website has some options that might
				be better, but I'm going with this for now.
		*/
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}
	
	private void goToBack() {
		switch (backCurrent) {
		case 1:
			buildShowAllInfo();
			break;
		case 2:
			buildLogin();
			break;
		case 3:
			buildCreateAccount();
			break;
		case 4:
			buildBidderPortal();
			break;
		case 5:
			buildSellerPortal();
			break;
		default:
			buildWelcomeScreen();
		}
	}
	
	private void buildWelcomeScreen() {
		
		usernameLoginTF.setText("");
		usernameCreateAccountTF.setText("");
		passwordLoginTF.setText("");
		passwordCreateAccountTF.setText("");
		usernameCreateAccountTF.setPlaceholder("Username");
		passwordCreateAccountTF.setPlaceholder("Password");
		usernameLoginTF.setPlaceholder("Username");
		passwordLoginTF.setPlaceholder("Password");
		
		backCurrent = backNext;
		backNext = 0;
		
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().setBackground(new java.awt.Color(51, 153, 255));
		setLayout(new FlowLayout());
		
		titleJL.setText(titleFormatO + "Welcome to Silent Auction" + titleFormatC);
		add(titleJL);
		add(dollarImageJL);
		add(wide20);
		add(loginJB);
		add(wide10a);
		add(createAccountJB);
		add(wide10b);
		add(showAllInfoJB);
		
		setVisible(true);
	}
	
	private void buildShowAllInfo() {
		backCurrent = backNext;
		backNext = 1;
		
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().setBackground(new java.awt.Color(51, 153, 255));
		setLayout(new FlowLayout());
		
		titleJL.setText(titleFormatO + "Silent Auction Info" + titleFormatC);
		add(titleJL);
		JLabel allInfoJL = new JLabel(sa.returnAllInfo());
		allInfoJL.setOpaque(true);
		allInfoJL.setBackground(new Color(180,255,200));
		allInfoJL.setBorder(new MatteBorder(25, 75, 10, 10, new Color(180,255,200)));
		//JScrollPane allInfo = new JScrollPane(new JLabel(sa.returnAllInfo()));
		JScrollPane allInfo = new JScrollPane(allInfoJL);
		allInfo.setPreferredSize(new Dimension(600, 400));
		add(allInfo);
		add(wide0);
		add(back);
		
		/*JTextArea textArea = new JTextArea(univ.returnAll());
		JScrollPane scrollPane = new JScrollPane(textArea);  
		scrollPane.setPreferredSize(new Dimension(500, 500));
		JOptionPane.showMessageDialog(null, scrollPane, "All Info", JOptionPane.PLAIN_MESSAGE);*/
		
		setVisible(true);
	}
	
	private void buildLogin() {
		backCurrent = backNext;
		backNext = 2;
		
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().setBackground(new java.awt.Color(51, 153, 255));
		setLayout(new FlowLayout());
		
		titleJL.setText(titleFormatO + "Login" + titleFormatC);
		add(titleJL);
		add(dollarImageJL);
		add(wide20);
		add(label);
		add(usernameLoginTF);
		add(wide10a);
		add(label2);
		add(passwordLoginTF);
		add(wide10b);
		add(loginButtonJB);
		add(back);
		add(wide30);
		add(securityMessage);
		
		setVisible(true);
	}
	
	private void buildCreateAccount() {
		backCurrent = backNext;
		backNext = 3;
		
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().setBackground(new java.awt.Color(51, 153, 255));
		setLayout(new FlowLayout());
		
		
		titleJL.setText(titleFormatO + "Create Account" + titleFormatC);
		add(titleJL);
		add(dollarImageJL);
		add(wide20);
		add(label);
		add(usernameCreateAccountTF);
		add(wide10a);
		add(label2);
		add(passwordCreateAccountTF);
		add(wide10b);
		add(createAccountBidderJB);
		add(createAccountSellerJB);
		add(wide40);
		add(back);
		add(wide30);
		add(securityMessage);
		
		setVisible(true);
	}
	
	private void buildBidderPortal() {
		usernameLoginTF.setText("");
		usernameCreateAccountTF.setText("");
		passwordLoginTF.setText("");
		passwordCreateAccountTF.setText("");
		usernameCreateAccountTF.setPlaceholder("Username");
		passwordCreateAccountTF.setPlaceholder("Password");
		usernameLoginTF.setPlaceholder("Username");
		passwordLoginTF.setPlaceholder("Password");
		
		backCurrent = backNext;
		backNext = 4;
		
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().setBackground(new java.awt.Color(51, 153, 255));
		setLayout(new FlowLayout());
		
		titleJL.setText(titleFormatO + "Bidder Portal" + titleFormatC);
		add(titleJL);
		add(dollarImageJL);
		
		String welcomeMessage = "Welcome, " + curBidder.getName();
		JLabel welcomePanel = new JLabel(welcomeMessage);
		Font font = new Font("Verdana", Font.BOLD, 15);
		welcomePanel.setFont(font);
		add(welcomePanel);
		add(one);
		add(viewItemsAvailableJB);
		add(two);
		add(viewPurchasedItemsJB);
		add(three);
		add(viewMoneyBidderJB);
		add(four);
		add(lotteryJB);
		add(seven);
		add(bidJB);
		add(five);
		add(six);
		add(logoutJB);
		
		
		setVisible(true);
	}
	
	private void buildSellerPortal() {
		usernameLoginTF.setText("");
		usernameCreateAccountTF.setText("");
		passwordLoginTF.setText("");
		passwordCreateAccountTF.setText("");
		usernameCreateAccountTF.setPlaceholder("Username");
		passwordCreateAccountTF.setPlaceholder("Password");
		usernameLoginTF.setPlaceholder("Username");
		passwordLoginTF.setPlaceholder("Password");
		
		backCurrent = backNext;
		backNext = 5;
		
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().setBackground(new java.awt.Color(51, 153, 255));
		setLayout(new FlowLayout());
		
		titleJL.setText(titleFormatO + "Seller Portal" + titleFormatC);
		add(titleJL);
		add(dollarImageJL);
		
		String welcomeMessage = "Welcome, " + curSeller.getName();
		JLabel welcomePanel = new JLabel(welcomeMessage);
		Font font = new Font("Verdana", Font.BOLD, 15);
		welcomePanel.setFont(font);
		add(welcomePanel);
		add(one);
		add(viewItemsOwnedJB);
		add(four);
		add(viewMoneyJB);
		add(five);
		add(addItemJB);
		add(two);
		add(auctionItemJB);
		add(three);
		add(logoutSellerJB);
		
		setVisible(true);
	}

	private void login() {
		
		/*
		 * This allows a user to login to an existing bidder or seller account
		 * 
		 * this.curBidder or this.curSeller will keep track of who the user is
		 * */
		
		/*System.out.print("Enter Username: ");
		String username = scanner.next();
		
		System.out.print("Enter Password: ");
		String password = scanner.next();*/
		
		String username = usernameLoginTF.getText();
		String password = passwordLoginTF.getText();
		
		// Searches through bidders to see if the entered info is a bidder account
		ArrayList<Bidder> bidders = this.sa.getAllBidders();
		for (int i = 0; i < bidders.size(); i++) {
			Bidder tempBidder = bidders.get(i);
			if (tempBidder.getName().equals(username)) {
				if (tempBidder.validateLogin(username, password) == true) {
					this.curBidder = tempBidder;
					this.curSeller = null;
					//this.state = "bidder";
					buildBidderPortal();
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
				//this.state = "seller";
				buildSellerPortal();
				return;
				}
			}
		}

		// If it reaches here, we didn't find a bidder or seller, and this.state is
		// still "login"
		//System.out.println("Invalid login, please try again.");
		JOptionPane.showMessageDialog(null, "Invalid login, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void create_account(String input) {
		
		/*
		 * This method allows the user to create a bidder or seller account, then logs the user in
		 * */

		/*System.out.print("Are you a bidder or a seller? ");
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
		String password = scanner.next();*/
		
		String username = usernameCreateAccountTF.getText();
		String password = passwordCreateAccountTF.getText();
		
		// Create the bidder or seller account
		if(username.equals("Username") || password.equals("Password")){
				JOptionPane.showMessageDialog(null, "Please provide a valid username and password.");
		}
		else {
			if (input.equals("seller")) {
				//System.out.println("Seller: " + username + " : " + password);
				this.curSeller = new Seller(username, password);
				this.sa.addSeller(this.curSeller);
				buildSellerPortal();
			} else if (input.equals("bidder") && username != null && password != null){
				this.curBidder = new Bidder(username, password);
				this.curBidder.setBidderMoney(500.0f); // Initialize bidders with $500
				this.sa.addBidder(this.curBidder);
				buildBidderPortal();
			}
		}
		/*System.out.print(input.toUpperCase() + " account succesfully created. Logging you in...\n\n");

		this.state = input; // Logs in as either bidder or seller*/
	}

	private void bidder(int userInput) {
		
		/*
		 * This method runs all the code for the bidder
		 * The switch(userInput) contains the code for all the different actions the
		 *    bidder can take
		 * (Note that the logout option (userInput == 6) is what allows the user to
		 *    return to the welcome page)
		 * */
		
		/*// Print welcome
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
		}*/
		
		String message;
		
		switch(userInput) {
		case 1: // View list of items
			/*System.out.println("\nThese are the items available:");
			this.sa.printAllItems();
			System.out.print("\n");*/
			
			message = "<html>These are the items available:<br>";
			message += this.sa.printAllItemsGUI();
			message += "</html>";
			JOptionPane.showMessageDialog(null, message, "Available Items", JOptionPane.PLAIN_MESSAGE);
			break;
		
		case 2: // Bid on item
			/*System.out.println("\nThese are the items available:");
			this.sa.printAllItems();
			
			System.out.println("\nYou have $" + curBidder.getMoneyString());
			
			System.out.println("\nChoose which item to bid on (type in name)");
			String userInput2 = scanner.nextLine();
			userInput2 = scanner.nextLine(); // Have twice because the 1st time gets a stray \n
			curItem = this.sa.getItem(userInput2);*/
			
			message = "<html>These are the items available:<br>";
//			String dropDownBox = this.sa.printAllBidderGUI();
			String dropDownMenu = this.sa.printAllItemsForDropBox();
//			System.out.println(dropDownBox);
			String itemDropDown[]= dropDownMenu.split("<br>");
			message += this.sa.printAllItemsGUI();
			JComboBox comboBox = new JComboBox();
			for(String item1: itemDropDown)
			{
				comboBox.addItem(item1);
			}
			
//			System.out.println(message);
//			JMenuItem menuItemCreateSpringProject = new JMenuItem(message);
//			popupMenu.add(menuItemCreateSpringProject);

//			 
//			JMenuItem menuItemCreateStrutsProject = new JMenuItem("Struts Project");
//			popupMenu.add(menuItemCreateStrutsProject);
			message += "<br>You have $" + curBidder.getMoneyString();
			message += "<br>Please choose an item to bid on:";
			
			JPanel thePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			thePanel.add(new JLabel(message + "</html>"));
			
			JTextField itemName = new JTextField(10);
			
			
//			comboBox.addItem(randomMsg);
			thePanel.add(comboBox);
//			thePanel.add(itemName);  uncomment this later
			
			int buttonPressed = JOptionPane.showOptionDialog(null, thePanel, "Available Items", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			
			
//			curItem = this.sa.getItem(itemName.getText());  uncomment this later 
			
			// Redo entry if that wasn't valid
			message += "<br><br>Not a valid item, please try again:";
			thePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			thePanel.add(new JLabel(message + "</html>"));
//			thePanel.add(itemName); uncomment this later 
//			while((curItem == null) & (buttonPressed != -1)) {
//				buttonPressed = JOptionPane.showOptionDialog(null, thePanel, "Available Items", JOptionPane.DEFAULT_OPTION,
//						JOptionPane.PLAIN_MESSAGE, null, null, null);
//				//System.out.println("I'm sorry, that's not a valid item, please try again:");
//				//userInput2 = scanner.nextLine();
//				if (buttonPressed == -1) {
//					// Pressed the red X
//					break;
//				}
				curItem = this.sa.getItem((String)comboBox.getSelectedItem());
//				curItem = this.sa.getItem(itemName.getText()); 
//			}
			
			if (buttonPressed == -1) {
				// User pressed red X
				JOptionPane.showMessageDialog(null, "Operation Canceled");
			}
			else {
				curSeller = curItem.GetOwningSeller();
				
				int time = curItem.GetOwningSeller().startAuctionAI(curItem);
				
				//System.out.println("Bidding on item " + curItem.getName());
				//System.out.println("You have " + time + " minutes");
				
				message = "Bidding on item " + curItem.getName() + "<br>";
				message += "You have " + time + " minutes";
				thePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				thePanel.add(new JLabel(message + "</html>"));
				thePanel.add(itemName); 
				
				float increaseAmount = curItem.getValue() / 20.0f;
				
				while ((curItem.checkIfDeadlineHasNotPassed()) ) {
					
					message = "<html>";
					if (curItem.GetOwningBidder() != null) {
						message += "Most recent bid is $" + curItem.getValueString() + " placed by " + curItem.GetOwningBidder().getName() + "<br";
					}
					message += "Place bid greater than $" + curItem.getValueString() + " (you have $" + curBidder.getMoneyString() + ")</html>";
					
					thePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					thePanel.add(new JLabel(message + "</html>"));
					itemName.setText(""); // Clear test from the field
					thePanel.add(itemName); 
					
					buttonPressed = JOptionPane.showOptionDialog(null, thePanel, "Place a Bid", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, null, null);
					
					//System.out.println("Place bid greater than $" + curItem.getValueString() + " (you have $" + curBidder.getMoneyString() + ")");
					//curBidder.placeBidOn(curItem, scanner.nextFloat());
					
						
						try {
							message = "<html>" + curBidder.placeBidOnGUI(curItem, Float.parseFloat(itemName.getText())); 
						}
						catch (Exception e) {
							message = "<html>Must enter a number.";
						}
					
					
						JOptionPane.showMessageDialog(null, message + "</html>", "Status of Bid", JOptionPane.PLAIN_MESSAGE);
						
						int numBidders = sa.getAllBidders().size();
						for (Bidder randomBidder : sa.getAllBidders()) {
							if (randomBidder == curBidder)
								continue;
							
							Random rand = new Random();
							int randInt = rand.nextInt(100);
							
							if ((randInt % numBidders) < 2) {
								randomBidder.setCanPlaceBid(true);
								randomBidder.PlaceBidIfPossible(curItem, increaseAmount);
							}
						}
					
				}
				
//				if (buttonPressed == -1) {
//					JOptionPane.showMessageDialog(null, "Auction Canceled");
//				}
				
				
					// Print who got the item
					if (curItem.GetOwningBidder() == curBidder) {
						message = "<html>You have placed the winning bid of $" + curItem.getValueString() + "<br>";
						message += "Auction for item " + curItem.getName() + " has finished.";
						JOptionPane.showMessageDialog(null, message + "</html>", "You won the auction", JOptionPane.PLAIN_MESSAGE);
						
						try {
						System.setProperty("webdriver.chrome.driver", "images/chromedriver.exe");

						//Initiating your chromedriver
						WebDriver driver=new ChromeDriver();

						//Applied wait time
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						//maximize window
						//driver.manage().window().maximize();

						//open browser with desried URL
						driver.get("https://www.paypal.com/invoice/p/#4QMFWMCZGNKTSASY");
						}
						catch(Exception e){
							System.out.println("Chrome unavailable.");
						}
						
						//System.out.println("You have placed the winning bid of $" + curItem.getValueString());
						//System.out.print("Auction for item " + curItem.getName() + " has finished.\n\n");
					}
					else if (curItem.GetOwningBidder() == null) {
						message = "<html>No one placed any bids.<br>";
						message += "Auction for item " + curItem.getName() + " has finished.";
						JOptionPane.showMessageDialog(null, message + "</html>", "No bids", JOptionPane.PLAIN_MESSAGE);
					}
					else {
						//System.out.println("Another bidder has placed the winning bid.");
						//System.out.println(curItem.GetOwningBidder().getName() + " has placed the winning bid of $" + curItem.getValueString());
						//System.out.print("Auction for item " + curItem.getName() + " has finished.\n\n");
						message = "<html>Another bidder has placed the winning bid.<br>";
						message += curItem.GetOwningBidder().getName() + " has placed the winning bid of $" + curItem.getValueString() + "<br>";
						message += "Auction for item " + curItem.getName() + " has finished.";
						JOptionPane.showMessageDialog(null, message + "</html>", "You won the auction", JOptionPane.PLAIN_MESSAGE);
					}
					
					// Create Transaction class here to complete the transaction for whoever won the item
					Transaction t1 = new Transaction();
					t1.setBidder(curItem.GetOwningBidder());
					t1.setSeller(curSeller);
					t1.startTransaction();
				 // else from 2nd if(buttonPressed == -1)
			} // else from 1st if(buttonPressed == -1)
			break;
		
		case 3: // Show money
			//System.out.print("You have $" + curBidder.getMoneyString() + "\n\n");
			message = "<html>You have $" + curBidder.getMoneyString();
			JOptionPane.showMessageDialog(null, message + "</html>", "Your money", JOptionPane.PLAIN_MESSAGE);
			break;
		
		case 4: // Show acquired items
			//curBidder.printAquiredItems();
			if (curBidder.hasItems()) {
				message = "<html>" + curBidder.printAquiredItemsGUI();
			}
			else {
				message = "<html>You own no items.";
			}
			JOptionPane.showMessageDialog(null, message + "</html>", "Your acquired items", JOptionPane.PLAIN_MESSAGE);
			break;
			
		case 5: // Play the lottery
			Random rand = new Random();
			int drawnNumber = rand.nextInt(20) + 1;
			int winningNumber = rand.nextInt(20) + 1;
			if (curBidder.getMoney() < 3) {
				//System.out.print("You don't have enough money to but a lottery ticket.\n\n");
				message = "<html>You don't have enough money to but a lottery ticket.";
			}
			else if (drawnNumber == winningNumber) {
				//System.out.println("Your lotto number is " + drawnNumber + ". The winning number is " + winningNumber + ".");
				//System.out.print("You won the lottery! You won $1000\n\n");
				curBidder.setBidderMoney(curBidder.getMoney() + 1000.0f);
				message = "<html>Your lotto number is " + drawnNumber + ". The winning number is " + winningNumber + ".<br>";
				message += "You won the lottery! You won $1000";
			}
			else {
				//System.out.println("Your lotto number is " + drawnNumber + ". The winning number is " + winningNumber + ".");
				//System.out.print("You didn't win the lottery.\n\n");
				curBidder.setBidderMoney(curBidder.getMoney() - 3.0f);
				message = "<html>Your lotto number is " + drawnNumber + ". The winning number is " + winningNumber + ".<br>";
				message += "You didn't win the lottery.";
			}
			JOptionPane.showMessageDialog(null, message + "</html>", "Your acquired items", JOptionPane.PLAIN_MESSAGE);
			break;
		
		case 6: // Logout (change this.state to "welcome")
			curBidder = null;
			buildWelcomeScreen();
			break;
		}
	}

	private void seller(int userInput) {
		
		/*
		 * This method contains all the code for the seller
		 * (Note that if userInput == 5, the user will return to the welcome page)
		 * */
		
		/*// Print welcome
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
		}*/
		
		String message = "";
		// Display owned items
		if (userInput == 1) {
			/*System.out.println("Your items are ");
			curSeller.printItems();*/
			message = curSeller.printItemsGUI();
			if(message == "") {
				JOptionPane.showMessageDialog(null,"You do not own any items.", "Error", JOptionPane.PLAIN_MESSAGE);
			}
			else {
			message = "<html>Your items are:<br>";
			message += curSeller.printItemsGUI();
			JOptionPane.showMessageDialog(null, message + "</html>", "Owned Items", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		// Begin an auction
		else if (userInput == 2) {
			//System.out.println("Which item would you like to auction");
			//System.out.println("Your items are ");
			//curSeller.printItems();
			//System.out.println("Remember that for this part you need to provide the name of the item ");
			
			message = "<html>Which item would you like to auction?<br>";
			message += "Your items are:<br>";
			
			message += curSeller.printItemsGUI();
			message += "Remember that for this part you need to provide the name of the item.";
			String dropDownMenu = curSeller.printItemsForSellerDropDown();
//			System.out.println(dropDownBox);
			String itemDropDown[]= dropDownMenu.split("<br>");
//			message += this.sa.printAllItemsGUI();
			JComboBox comboBox = new JComboBox();
			for(String item1: itemDropDown)
			{
				comboBox.addItem(item1);
			}
			String chooseItem = new String("<html> Choose an item to auction ");
			if(dropDownMenu.length() == 0 )
			{
				chooseItem += " <br> you currently don't have any items please add an item first";
			}
			JPanel thePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			thePanel.add(new JLabel(chooseItem +  "</html>" ) );
			JTextField itemName = new JTextField(10);
//			add(two);
//			comboBox.setBorder(new Border());
			// Formatting needs to be fixed in this line this is where I add the drop down box
			thePanel.add(comboBox);
//			thePanel.add(itemName);
			
			int buttonPressed = JOptionPane.showOptionDialog(null, thePanel, "Choose item to auction", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			
			while ((!(curSeller.isListingValidItemGUI((String)comboBox.getSelectedItem()))) & (buttonPressed != -1)) {
				//System.out.println("Sorry " + curSeller.getName() + " That's not an item you own");
				//System.out.println("Which item would you like to auction");
				//curSeller.printItems();
				//System.out.println("Remember that for this part you need to provide the name of the item ");
				
				message = "<html>Sorry " + curSeller.getName() + " That's not an item you own<br>";
				message += "Your items are:<br>";
				message += curSeller.printItemsGUI();
				message += "Remember that for this part you need to provide the name of the item.";
				
				thePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				thePanel.add(new JLabel(message + "</html>"));
//				itemName = new JTextField(10);
//				thePanel.add(itemName);
				
				buttonPressed = JOptionPane.showOptionDialog(null, thePanel, "Choose item to auction", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, null, null);
			}
			
			if (buttonPressed == -1) {
				JOptionPane.showMessageDialog(null, "Operation canceled");
			}
			else {

				message = "<html>Enter the item deadline (this will be taken as minutes)";
				thePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				thePanel.add(new JLabel(message + "</html>"));
				itemName = new JTextField(10);
				thePanel.add(itemName);
				JOptionPane.showMessageDialog(null, thePanel, "Choose item to auction", JOptionPane.PLAIN_MESSAGE);
				
//				int ItemDeadline = scanner.nextInt();
				int ItemDeadline = 1;
				try {
					ItemDeadline = Integer.parseInt(itemName.getText());
				}
				catch (Exception e) {
					// Do nothing; leave itemDeadline at 1
				}
				Date d1 = new Date();
				curSeller.getAuctionedItem().setDate(d1);
				curSeller.getAuctionedItem().setStartTime();
				curSeller.getAuctionedItem().setDeadline(ItemDeadline);
	//			item1.setStartTime();
	//			item1.setDeadline(ItemDeadline);
	
				//Bidder b1 = sa.getAllBidders().get(0);
				//Bidder b2 = sa.getAllBidders().get(1);
	
				while ((curSeller.getAuctionedItem().checkIfDeadlineHasNotPassedGUI()) & (buttonPressed != -1)) {
					//b1.decreaseCoolDownPeriod();
					//b2.decreaseCoolDownPeriod();
					// this is where the auction will take place
	
					// this is where the items finds its bidders and sellers
					// example
	//				Player.getAuctionedItem().setBidder(b1);
					// start silient auction session
	
					for (Bidder randomBidder : sa.getAllBidders()) {
						randomBidder.decreaseCoolDownPeriod();
						buttonPressed = randomBidder.PlaceBidIfPossibleGUI(curSeller.getAuctionedItem());
						// there is a JOptionPane.ShowOptionDialog() within PlaceBidIfPossibleGUI()
						if (buttonPressed == -1)
							break;
					}
	
				}
				
				if (buttonPressed == -1) {
					JOptionPane.showMessageDialog(null, "Auction canceled. No transaction.");
				}
				else {
		
					if (curSeller.getAuctionedItem().GetOwningBidder() == null) {
						//System.out.println("Unfortunately there were no bidder" + " for the "
						//		+ curSeller.getAuctionedItem().getName() + " item");
						//System.out.print("Auction for item " + curSeller.getAuctionedItem().getName() + " has finished.\n\n");
						message = "<html>Unfortunately there were no bidder for the " + curSeller.getAuctionedItem().getName() + " item<br>";
						message += "Auction for item " + curSeller.getAuctionedItem().getName() + " has finished.";
						curSeller.setAuctionedItem(null);
					} else {
						// making the transaction here
						Transaction silientTransaction = new Transaction();
						//System.out.println("The winning bidder of the " + curSeller.getAuctionedItem().getName() + " is "
						//		+ curSeller.getAuctionedItem().GetOwningBidder().getName());
						//System.out.println("The item was purchased for " + curSeller.getAuctionedItem().getValueString());
						
						message = "<html>The winning bidder of the " + curSeller.getAuctionedItem().getName() + " is "+ curSeller.getAuctionedItem().GetOwningBidder().getName() + "<br>";
						message += "The item was purchased for $" + curSeller.getAuctionedItem().getValueString() + "<br>";
						
						// this is where we would ask for the transaction
						silientTransaction.setSeller(curSeller);
						silientTransaction.setBidder(curSeller.getAuctionedItem().GetOwningBidder());
						
						//System.out.print("Auction for item " + curSeller.getAuctionedItem().getName() + " has finished.\n\n");
						message += "Auction for item " + curSeller.getAuctionedItem().getName() + " has finished.";
						
						silientTransaction.startTransaction();
						// was testing to see if the player cash was actually deducted
		//				System.out.println(" his cash is now " +Player.getAuctionedItem().GetOwningBidder().getMoney());
		//				System.out.println(" his cash is now " +Player.getCashRecieved());
					}
					JOptionPane.showMessageDialog(null, message + "</html>", "Auction completed", JOptionPane.PLAIN_MESSAGE);
					
				} // else from 2nd if(buttonPressed == -1)
			} // else from 1st if(buttonPressed == -1)
		}
		
		// Add a new item
		else if (userInput == 3) {
			Date d4 = new Date();
			Item item4 = new Item();
			//System.out.print("Name of item: ");
			message = "<html>Name of item:";
			JPanel thePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			thePanel.add(new JLabel(message + "</html>"));
			JTextField itemName = new JTextField(10);
			thePanel.add(itemName);
			
			int buttonPressed = JOptionPane.showOptionDialog(null, thePanel, "Add an item", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			
			if (buttonPressed == -1) {
				JOptionPane.showMessageDialog(null, "Operation canceled.");
			}
			else {
				
				//item4.setName(scanner.next());
				item4.setName(itemName.getText());
				//System.out.print("Value of item (minimum bid): ");
				
				message = "<html>Value of item (minimum bid):";
				thePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				thePanel.add(new JLabel(message + "</html>"));
				itemName = new JTextField(10);
				thePanel.add(itemName);
				
				buttonPressed = JOptionPane.showOptionDialog(null, thePanel, "Add an item", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, null, null);
				//item4.setValue(scanner.nextFloat());
				
				float value = 0.0f;
				boolean success = true;
				try {
					value = Float.parseFloat(itemName.getText());
				}
				catch(Exception e) {
					success = false;
				}
				
				while ((!success) & (buttonPressed != -1)) {
					message = "<html>Must choose a number for the value:";
					thePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					thePanel.add(new JLabel(message + "</html>"));
					itemName = new JTextField(10);
					thePanel.add(itemName);
					
					buttonPressed = JOptionPane.showOptionDialog(null, thePanel, "Add an item", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, null, null);
				}
				
				if (buttonPressed == -1) {
					JOptionPane.showMessageDialog(null, "Operation canceled.");
				}
				else {
					item4.setValue(Math.abs(value));
					item4.setDate(d4);
					
					curSeller.addItem(item4);
					//System.out.print("Item successfully added.\n\n");
					JOptionPane.showMessageDialog(null, "Item successfully added.", "Add an item", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
		
		// Show money
		else if (userInput == 4) {
			//System.out.print("You have $" + curSeller.getCashRecievedString() + "\n\n");
			JOptionPane.showMessageDialog(null, "You have $" + curSeller.getCashRecievedString(), "Your money", JOptionPane.PLAIN_MESSAGE);
		}
		
		// Logout (set this.state to "welcome")
		else if (userInput == 5) {
			//System.out.print("Logging out...\n\n");
			//this.state = "welcome";
			buildWelcomeScreen();
		}
	}
}
