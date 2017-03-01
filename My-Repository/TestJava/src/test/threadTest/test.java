package test.threadTest;

public class test implements Runnable{

	@Override
	public void run() {
		System.out.println(1);
	}
	
	public static void main(String[] args){
		test t = new test();
		Thread t1 = new Thread(t);
		t1.start();
		Thread t2 = new Thread(t);
		t2.start();
	}
}
