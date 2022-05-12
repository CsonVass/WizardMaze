package Wizard_Maze.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
	
	////Returns the names of files in a directory
	public static String[] filePaths(String dir_path){
		File dir = new File(dir_path);
		String files [] = dir.list();
		return files;
	}
	
	//Returns the names of files in a directory without their extendsions
	public static String[] getFileNamesWithoutExtension(String dir_path) {
		String[] fileNames = filePaths(dir_path);
		String[] fileNamesWithoutExtension = new String[fileNames.length];
		for(int i = 0; i < fileNames.length; i++) {
			int extPos = fileNames[i].lastIndexOf(".");
			if(extPos == -1)
				fileNamesWithoutExtension[i] = fileNames[i];
			else
				fileNamesWithoutExtension[i] = fileNames[i].substring(0, extPos);
		}
		return fileNamesWithoutExtension;
	}
	
	//Loading in file and putting the content into the returned string
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	//saves files, in case of error, returns false, if successful, true
	public static void saveFile(String path, String[] contentInStringArray) throws IOException {
			BufferedWriter br = new BufferedWriter(new FileWriter(path));
			for(int i = 0; i < contentInStringArray.length; i++) {
				br.write(contentInStringArray[i] + "\n");
			}
			br.close();
	}


}
