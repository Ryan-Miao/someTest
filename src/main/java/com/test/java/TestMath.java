package com.test.java;

import java.util.Random;

public class TestMath {
	

	public void random(){
		System.out.println((Math.random()*1000));
		System.out.println((int)(Math.random()*100));
	}
	
	public static void main(String[] args){
		String[] b = {"公共的", "私有的", "受保护的"};
		Random rand = new Random();
		int num = rand.nextInt(3);
		System.out.println(b[num]); 
	}

}
