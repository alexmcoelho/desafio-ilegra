package br.com.springbatch.manip;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ManipText {
	
	public static void writer(String path, String text) {
		BufferedWriter buffWrite;
		try {
			buffWrite = new BufferedWriter(new FileWriter(path));
			buffWrite.append(text + "\n");
			buffWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
