/* Name: Andrew Turpin An832547
 Course: CNT 4714 Summer 2020
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: June 14, 2020
*/


package pack;
//account interface file based on buffer.java webcourses file
public interface Account {
//Interface that specifies method called by 'Withdrawer'
	public void withdrawer(int wvalue, String thread);
	
//Interface that specifies method called by 'depositer'
	public void depositer(int dvalue, String thread);
}
