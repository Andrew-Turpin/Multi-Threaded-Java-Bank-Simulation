/* Name: Andrew Turpin An832547
 Course: CNT 4714 Summer 2020
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: June 14, 2020
*/

//this is our  main class where the threads are created
//we will be using the java concurrent locks util like asked in the documentation
package pack;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class bankmain implements Account {
	//create a int that holds our overall total balance that we will display with
	//each withdraw or deposit
	public int balance=0;

	
	
	//create lock
	private Lock locker=new ReentrantLock();
	//like the syncronized buffer file on webcourses, i'll implement a condition for the locks of withdrawing
	//and depositing
	
	private Condition candeposit=locker.newCondition();
	private Condition canwithdraw=locker.newCondition();

	
	//deposit connector function that utalizes our thread lock
	//pass in the given thread string and the value of deposit
	public void depositer(int dvalue, String thread) {
		//lock thread
		locker.lock();
		//add the deposit value to the balance
		balance=balance+dvalue;
		//print the console output for the thread that made the deposit and it's ammount, as well as our new balance total
		//println wouldn't work so I had to print f with a new line at end
		System.out.printf("%s deposits $%s \t\t\t\t\t\t\t\t(+)Balance is %d\n",thread,dvalue,balance);
		
		try {
			canwithdraw.signalAll();
			candeposit.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		locker.unlock();
	}
	
	
	
	
	//withdrawer connector function for our thread
	//again utalize the lock as specified by instructions and the webcourse files
	//send in the amount to withdraw, and from which thread
	public void withdrawer(int wvalue,String thread) {
		//lock thread
		locker.lock();
		
		//i'm going to use a if else, if there's enough to be withdrawn, then withdraw that and print
		
		//else there isn't enough to withdraw, print the insufficient funds message for that thread
		
		if (balance >= wvalue){
			//deduct wvalue from our total
			balance=balance-wvalue;
			//print our action
			System.out.printf("\t\t\t\t\t%s withdraws $%s\t\t\t\t(-)Balance is %d\n",thread,wvalue,balance);
			
		}
		else {
			//else print we can and it's insufficient
			System.out.printf(" \t\t\t\t\t"+ thread+" withdraws $"+ wvalue+" \t\t\tWithdrawal - Blocked - Insufficient Funds!!!\n");
		
			try {
				candeposit.signalAll();
				canwithdraw.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		locker.unlock();
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Print the text to distinguish depositer, withdrawer threads and the total balance
		
		System.out.println("Deposit threads \t\t\t  Withdrawl threads \t\t\t   Balance");
		//print text lines under each above text (as shown in example sdcreenshots
		System.out.println("................ \t\t\t .................. \t\t\t ...........");

		//create a new instance of our main bank executable
		bankmain btest=new bankmain();
		//instantiate all of our threads for both the deposit and withdrawl threads
		//also name each thread as document D1-5 and W1-9
		
		withdrawer wthread1=new withdrawer(btest);
		wthread1.setName("Thread W1");
		
		withdrawer wthread2=new withdrawer(btest);
		wthread2.setName("Thread W2");
		
		withdrawer wthread3=new withdrawer(btest);
		wthread3.setName("Thread W3");
		
		withdrawer wthread4=new withdrawer(btest);
		wthread4.setName("Thread W4");
		
		withdrawer wthread5=new withdrawer(btest);
		wthread5.setName("Thread W5");
		
		withdrawer wthread6=new withdrawer(btest);
		wthread6.setName("Thread W6");
		
		withdrawer wthread7=new withdrawer(btest);
		wthread7.setName("Thread W7");
		
		withdrawer wthread8=new withdrawer(btest);
		wthread8.setName("Thread W8");
		
		withdrawer wthread9=new withdrawer(btest);
		wthread9.setName("Thread W9");
		
		
		depositer dthread1=new depositer(btest);
		dthread1.setName("Thread D1");
		
		depositer dthread2=new depositer(btest);
		dthread2.setName("Thread D2");
		
		depositer dthread3=new depositer(btest);
		dthread3.setName("Thread D3");
		
		depositer dthread4=new depositer(btest);
		dthread4.setName("Thread D4");
		
		depositer dthread5=new depositer(btest);
		dthread5.setName("Thread D5");
		
		dthread1.start();
		dthread2.start();
		dthread3.start();
		dthread4.start();
		dthread5.start();
		
		wthread1.start();
		wthread2.start();
		wthread3.start();
		wthread4.start();
		wthread5.start();
		wthread6.start();
		wthread7.start();
		wthread8.start();
		wthread9.start();

		
		
	}
	
	
	
	
	
	

}
