package com.tdil.simon.test;

public class StartDelegate {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Delegate d1 = new Delegate();
		d1.setUsername(args[0]);
		d1.start();
		

	}

}
