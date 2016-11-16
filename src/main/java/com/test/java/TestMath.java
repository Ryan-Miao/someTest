package com.test.java;

import org.junit.Test;

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

	@Test
	public void testCeil(){
		double a = 35;
		double b = 20;
		double c = a/b;
		System.out.println("c=="+c);
		System.out.println("c--ceil="+Math.ceil(c));
		System.out.println("c--floor="+Math.floor(c));
	}

}
