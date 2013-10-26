/**
 * Implements the file reading and writing
 * for WaiterHelper
 */
package com.raven.waiterhelper.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
		deletePrevFiles();

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
			PrintWriter pw = new PrintWriter(new FileWriter(fis.getFD()));
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
				pw.append('\n');
				pw.println();
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.toString(sb);

	}

	public String toString(StringBuilder sb) {
		String formattedString = "";

		for (int i = 0; i < sb.length(); i++) {
			if ((sb.charAt(i) == 93) || (sb.charAt(i) == 91)) {
				formattedString = formattedString + '\n';
			} else
				formattedString = formattedString + sb.charAt(i);
		}

		return formattedString;
	}

	public void deletePrevFiles() {
		String[] fileList = context.fileList();
		for (int i = 0; i < fileList.length; i++) {
			context.deleteFile(fileList[i]);
		}
	}
}
