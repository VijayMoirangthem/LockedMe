package Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Application {

	static Scanner sc = new Scanner(System.in);

	public static boolean welcome() {

		System.out.println("WELCOME TO LOCKEDME.COM\n");
		System.out.println("Developed by:");
		System.out.println("Vijay Moirangthem");
		System.out.println("LockedMe Private Limited\n");

		System.out.println(
				"Details: \nContact:+91-9403569414\nMail ID: vijaymoirangthem9@gmail.com\n");
		System.out.println(
				("This Application is useful in:\n*Sorting files inside directory and its subdirectory \n*Adding file to a directory\n*Deleteing a file in a directory\n*Searching a file in a directory\n"));
		System.out.println("Please press Y to proceed..");
		String str = sc.next();
		while (true) {
			if (str.equalsIgnoreCase("y")) {
				return true;
			} else {
				System.out.println("Press Y to continue");
				str = sc.next();
			}
		}
	}

	public static void choices() {
		while (true) {
			System.out.println("\nChoose any one: \n");
			System.out.println("1: List files in a directory");
			System.out.println("2: File manipulation");
			System.out.println("3: Exit\n");
			int n = sc.nextInt();
			switch (n) {
			case 1:
				List<String> list = new ArrayList<>();
				list = listFiles();

				System.out.println("\nTotal number of items present in the directory: \n");
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
				break;
			case 2:
				userInterfaces();
				break;

			case 3:
				System.out.println("Thank you for choosing LockedMe :)");
				System.exit(0);
				break;

			default:
				System.out.println("Incorrect. Please select from the listed choices");
			}
		}
	}

	public static ArrayList<String> listFiles() {

		System.out.println("Enter Root Directory path");
		String location = sc.next();
		File file = null;
		try {
			file = new File(location);
		} catch (NullPointerException e) {
			System.out.println("Please enter correct Root directory");
			e.printStackTrace();}
			catch (java.util.InputMismatchException i) {
			System.out.println("Input not Matching");
            i.printStackTrace();    
		}
		File[] fs = file.listFiles();
		ArrayList<String> list = new ArrayList<>();
		list = read(fs, list);
		Collections.sort(list);
		return list;

	}

	public static ArrayList<String> read(File file[], ArrayList<String> list) {
		for (File eachfile : file) {
			list.add(eachfile.getName());
			if (eachfile.isDirectory()) {
				File fs[] = eachfile.listFiles();
				read(fs, list);
			}
		}
		return list;
	}

	public static void userInterfaces() {
		System.out.println("\n\nChoose any one: \n");
		System.out.println("1: Add file");
		System.out.println("2: Delete file");
		System.out.println("3: Search file");
		System.out.println("4: Exit\n");
		int n = sc.nextInt();
		switch (n) {
		case 1:
			addFile();
			break;
		case 2:
			deleteFile();
			break;
		case 3:
			searchFile();
			break;
		case 4:
			choices();
			break;
		default:
			System.out.println("Invalid option. Please try again.");
		}
	}

	public static void addFile() {
		System.out.println("Enter Existing Directory path with new file name\n ");
		Path path = Paths.get(sc.next());
		List<String> list = new ArrayList<>();
		try {
			Files.write(path, list, StandardOpenOption.CREATE_NEW);
		} catch (IOException e) {
			System.out.println("\nFile Exists!");
			e.printStackTrace();
		}
		System.out.println("\nFile Created!");
	}

	public static void deleteFile() {
		System.out.println("Enter the file to be deleted with absolute path\n");
		Path path = Paths.get(sc.next());
		try {
			Files.deleteIfExists(path);
			System.out.println("File deleted successfully!");
		} catch (DirectoryNotEmptyException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void searchFile() {
		System.out.println("\nEnter file name to be searched with extension");
		{
			String string = sc.next();
			ArrayList<String> list = new ArrayList<>();
			list = listFiles();
			if (list.contains(string)) {
				System.out.println("\nFile found!");
			} else
				System.out.println("\nFile doesn't exist!");
		}
	}

	public static void main(String[] args) {
		boolean check = welcome();
		if (check) {
			choices();
		}
	}

}