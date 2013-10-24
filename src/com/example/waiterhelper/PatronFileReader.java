/**
 * Implements the file reading and writing
 * for WaiterHelper
 */
package com.example.waiterhelper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

/**
 * @author Tendai T.T. Mudyiwa
 * @version October 20 2013
 */
public class PatronFileReader {
	private FileOutputStream fos;
	private String fileName;
	private String file;
	private Context context;
	private FileInputStream fis;

	public PatronFileReader(String fileName, Context context, String file) {
		this.fos = null;
		this.fileName = fileName;
		this.file = file;
		this.context = context;
	}

	/**
	 * @param fileList 
	 * 
	 */
	public PatronFileReader(String fileList, Context context) {
		this.fileName = fileList;
		this.context = context;
	}

	public void storeFile() {
		try {
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fos.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String retrieveFile() {
		 try {
			fis = context.openFileInput(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		 InputStreamReader isr = new InputStreamReader(fis);
		   BufferedReader bufferedReader = new BufferedReader(isr);
		   StringBuilder sb = new StringBuilder();
		   String line;
		   try {
			while ((line = bufferedReader.readLine()) != null) {
			       sb.append(line);
			   }
		} catch (IOException e) {
			e.printStackTrace();
		}
		   return this.toString(sb);
		   
	}
	
	public String toString(StringBuilder sb){
		String formattedString="";
		
		for(int i =0; i<sb.capacity();i++){
			if((sb.charAt(i)==']')||(sb.charAt(i)=='[')){
				formattedString = formattedString+ '\n';
			}
			formattedString = formattedString+ sb.charAt(i);
		}
		
		return formattedString;
	}

}
