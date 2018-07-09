package custom.ops;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {

	private String filePath = null;
	private File file;
	private final String FILENAME = "samples.dat";

	public FileOperations() {

	}

	public void writeToFile(String text, boolean appendMode) {
		createFile();
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file, appendMode);
			bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(text);
			bufferedWriter.close();
		} catch (IOException e) {
			System.out.println("Write error occurred!\n" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Writers has not closed!\n" + e.getMessage());
				e.printStackTrace();
			}

		}

	}

	public String readFromFile() {
		createFile();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		String line;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
		} catch (IOException e) {
			System.out.println("Read error occurred!\n" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Readers has not closed!\n" + e.getMessage());
				e.printStackTrace();
			}
		}
		return stringBuilder.toString();

	}

	private String getSysDir() {
		return System.getProperty("user.dir");
	}

	private void createFile() {

		if (file == null) {
			filePath = getSysDir();
			filePath += "/" + FILENAME;
			file = new File(filePath);

			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.out.println("Error occurred!\n" + e.getMessage());
					e.printStackTrace();
				}
			}
		}

	}

}
