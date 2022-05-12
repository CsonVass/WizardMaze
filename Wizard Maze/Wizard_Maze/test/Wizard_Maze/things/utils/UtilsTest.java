package Wizard_Maze.things.utils;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import Wizard_Maze.utils.Utils;

public class UtilsTest {

	@Test
	public void filePathsTest() {
		String[] files = Utils.filePaths("res/test/loadTest");
		String[] filesCheck = new String[2];
		filesCheck[0] = "test1.txt";
		filesCheck[1] = "test2.txt";
		
		Assert.assertArrayEquals(filesCheck, files);
	}
	
	@Test
	public void getFileNamesWithoutExtensionTest() {
		String[] fileNames = Utils.getFileNamesWithoutExtension("res/test/loadTest");
		String[] fileNamesCheck = new String[2];
		fileNamesCheck[0] = "test1";
		fileNamesCheck[1] = "test2";
		
		Assert.assertArrayEquals(fileNamesCheck, fileNames);
	}
	
	@Test
	public void loadFileAsStringTest() {
		String data = Utils.loadFileAsString("res/test/loadTest/test1.txt");
		String dataCheck = new String("test1\n\nthings in test 1\n");
		
		Assert.assertEquals(dataCheck, data);
	}
	
	@Test (expected=FileNotFoundException.class)
	public void saveFileTestInvalidName() throws Exception{
		String[] data = new String[3];
		data[0] = "0";
		data[1] = "1";
		data[2] = "two";
		
		Utils.saveFile("res/test/saveTest/t??tValid.txt", data);
	}
	
}
