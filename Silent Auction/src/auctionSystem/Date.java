package auctionSystem;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime; 

public class Date {
	
	/*
	 * This class provides a way of keeping track of time.
	 * */
	
	private long startTime = System.currentTimeMillis();
	private long estimatedTime;
	private long deadline;
	
	public void setStartTime() {
		this.startTime = startTime;
		//System.out.println(startTime);
	}
	
	public void setDeadline(int minutes) {
		deadline = minutes * 60000;
	}
	
	public boolean checkIfDeadlineHasNotPassed()
	{
		
		estimatedTime = System.currentTimeMillis() - startTime;
		
		if(estimatedTime > deadline) {
			System.out.println("\nThe deadline has passed.");
			return false;
		}
		else {
//			System.out.println("The bid has been placed.");
			return true;
		}
	}
	
	public boolean checkIfDeadlineHasNotPassedGUI()
	{
		
		estimatedTime = System.currentTimeMillis() - startTime;
		
		if(estimatedTime > deadline) {
			//System.out.println("\nThe deadline has passed.");
			return false;
		}
		else {
//			System.out.println("The bid has been placed.");
			return true;
		}
	}
	
	public void waitSec(float numSeconds) {
		
		/*
		 * Waits the number of seconds provided - a way of delaying code execution
		 * */
		
		long willEndHere = (long) (System.currentTimeMillis() + (numSeconds * 1000.0));
		long currentTime = System.currentTimeMillis();
		while (currentTime < willEndHere) {
			currentTime = System.currentTimeMillis();
		}
	}
	
}
