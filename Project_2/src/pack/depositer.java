/* Name: Andrew Turpin An832547
 Course: CNT 4714 Summer 2020
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: June 14, 2020
*/

package pack;

//import random util to deposit random values into account
import java.util.Random;


//start of deposit class
public class depositer extends Thread {
	//make a int variable for the deposit int valeus
	private int dvalue_num;
	
	
	//make the random variable that selects a vlue between 1-250 whole dollars only
	//had to make this variable static for some reason to run properly
	private static Random ran=new Random();
	
//create a new instance of our account
	private Account account;
	
	
	public depositer(Account acc) {
		account=acc;
	}
	
	//run method for depositer, as stated in assignment documentation
	//run method should be an infinite loop
	public void run() {
		//infinite loop so while true
		while(true) {
			//make the ammount to be desposited never be 0, so at the start of the loop set it to 1
			dvalue_num=1;
			//now add some random number to it
			dvalue_num+=ran.nextInt(249);
			
			//now deposit this value into the account using our deposit call from the Account file
			account.depositer(dvalue_num, Thread.currentThread().getName());
			
			//now make this deposit thread go to sleep for some random time
			// as per instructions in documentation
			
				try {
					Thread.sleep(ran.nextInt(425));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
		}
		
		
		
		
	}
		//end of run bracket
	
	
	
}
//end of deposit class