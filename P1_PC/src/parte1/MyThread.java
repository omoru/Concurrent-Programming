package parte1;

public class MyThread extends Thread{
	
	private int time_to_sleep;
	private int id;
	public MyThread(int id,int time_to_sleep) {
		this.time_to_sleep = time_to_sleep;
		this.id = id;
		
	}
	
	public void run() {
		System.out.println("Starting, my id is "+ id);
		System.out.println("Sleeping for "+ time_to_sleep +", my id is "+ id);
		try {
			sleep(time_to_sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ending, my id is " +id);
		
	}
}
