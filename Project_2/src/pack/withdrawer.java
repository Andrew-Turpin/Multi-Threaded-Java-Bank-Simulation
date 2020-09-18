/* Name: Andrew Turpin An832547
 Course: CNT 4714 Summer 2020
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: June 14, 2020
*/

package pack;

//import random util to deposit random values into account
//just like the depositer class
	import java.util.Random;


//start of withdrawer class
public class withdrawer extends Thread {

	//we are essentially doign the sme thing from the deposit class with our variables
	
	//make a int variable for the withdraw int valeus
	private int wvalue_num;
	
	
	//make the random variable that selects a vlue between 1-50 whole dollars only
	//had to make this variable static for some reason to run properly
	private static Random ran=new Random();
	
//create a new instance of our account
	private Account account;
	
	public withdrawer(Account acc) {
		account=acc;
	}
	
	//run method for withdrawer, as stated in assignment documentation
		//run method should be an infinite loop
		public void run() {
			//infinite loop so while true
			while(true) {
				//make the ammount to be withdrawn never be 0, so at the start of the loop set it to 1
				wvalue_num=1;
				//now add some random number to it
				wvalue_num+=ran.nextInt(49);
				
				//now withdraw this value into the account using our withdraw call from the Account file
				account.withdrawer(wvalue_num, Thread.currentThread().getName());
				
				
				//looked up the yiled call for a thread to essentially not try and use the same thread
				//so instead of sleeping this thread for a time we just yield it so it doesn't try and get used while already in use
				Thread.yield();
				
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
