package com.test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class TestRead {
	
	public static void readTxt(String filePath){
		String encoding = "utf-8";
		File file = new File(filePath);
		if(file.isFile() && file.exists()){
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file),encoding);
				BufferedReader bfr = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bfr.readLine())!=null){
					System.out.println(lineTxt);
				}
				bfr.close();
				read.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		readTxt("F:\\provinces.txt");
	}
	
	
}
