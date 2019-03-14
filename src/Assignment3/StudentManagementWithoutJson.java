package Assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class StudentManagementWithoutJson {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to student manager Lite");
		System.out.println("List all students");
		System.out.println("Find a student");
		System.out.println("Add a student");
		System.out.println("Edit a student");
		System.out.println("Delete a student");
		System.out.println("Quit");

		System.out.println("Enter your choice");

		int choice = scan.nextInt();

		switch (choice) {

		case 1:
			listAllStudents();
			break;

		case 2:
			findAStudent();
			break;

		case 3:
	/*		addAStudent();
			break;

		case 4:
			editAStudent();
			break;

		case 5:
			deleteAStudent();
			break;

		case 6:
			quit();
			break;
*/
		default:

		}

	}

	private static List<Student> populate() {

		String date1 = "1990-10-21";
		String date2 = "1987-09-23";
		String date3 = "1990-10-21";
		String date4 = "1990-10-21";
		String date5 = "1990-10-21";
		String date6 = "1990-10-21";
		String date7 = "1990-10-21";
		String date8 = "1990-10-21";
		List<Student> students = new ArrayList<>();
		try {
			// SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			// f.setLenient(false);

			students.add(new Student(10, "Manjinder Singh Bharaj", date1, "manjinderbharaj50@gmail.com",
					"102,Sai charan Residency", "IT"));
			students.add(new Student(2, "ABC", date2, "abc@gmail.com", "702,High Rise", "Mechanical"));
			students.add(new Student(6, "Rohit Jugraj", date3, "rohitjugraj67@gmail.com", "103,Singh Residency",
					"Computers"));
			students.add(new Student(5, "Diljit Dosanjh", date4, "diljitdosanjhawala@gmail.com", "Plot No.01,Mohali",
					"Electrical"));
			students.add(new Student(8, "Santosh Sivan", date5, "santoshsivan888@gmail.com", "301,Mugalivakkam Chennai",
					"Graphics"));
			students.add(new Student(12, "Vicky Kaushal", date6, "vickyK@gmail.com", "901,Pali Hill", "Automation"));
			students.add(new Student(4, "Ajay Singh Rathore", date7, "acprathore@gmail.com",
					"100,Rajasthan Emporium Taj ke peeche Colaba", "IPS"));
			students.add(
					new Student(3, "Arjun Roshan", date8, "arjunbroker@gmail.com", "303,London High Rise ", "MBA"));
		} catch (Exception e) {

			e.printStackTrace();
		}

		return students;
	}

	private static void listAllStudents() {
		List<Student> students = populate();

		writeToFile(students);
	}

	private static void findAStudent() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Keyword");
		String enteredKeyword = scan.nextLine();
		try {
			List<String> students = readFile();
			List<Student> objectList = new ArrayList<Student>();

			System.out.println(students);
			List<String> studentsWithEnteredValue = new ArrayList<>();
			System.out.println("List of students "+students);
			for (String student : students) {

				if (student.contains(enteredKeyword) && enteredKeyword.length() > 1) {

					studentsWithEnteredValue.add(student);

				} 
			}
			System.out.println("Found " + studentsWithEnteredValue.size() + " students ");
			if (studentsWithEnteredValue.isEmpty()) {
				System.out.println("No Student Found.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

/*	private static void addAStudent() {

		List<Student> studentList = null;
		Student newStudent = null;
		String validStudentId = null;
		String validDate = null;
		String validName = null;
		String validEmail = null;
		String validAddress = null;
		String validFaculty = null;
		Scanner scan = new Scanner(System.in);

		try {
			List<String> students = readFile();
			System.out.println(students);
			System.out.println("Enter the new Student");
			System.out.println("Enter student id");
			// saving all the student ids in a list
		//	List<Integer> ids = students.stream().map(s -> s.getStudentId()).collect(Collectors.toList());
			// System.out.println(ids);

			while (true) {
				String enteredStudentId = scan.nextLine();
				if (!validateUserId(enteredStudentId)) {
					System.out.println("Student id should always be a number");
					System.out.println("Enter valid student Id");
				} else if (ids.contains(Integer.parseInt(enteredStudentId))) {
					System.out.println("Student id should always be unique");
					System.out.println("Enter unique student Id");
				} else {
					validStudentId = enteredStudentId;
					break;
				}
			}
			while (true) {
				System.out.println("Enter Birthdate for the student");
				String enteredDate = scan.nextLine();
				if (!isValidDate(enteredDate)) {
					System.out.println("Enter date in only yyyy/MM/dd format");
				} else {
					validDate = enteredDate;
					break;
				}
			}
			System.out.println("Enter FullName of new student");
			validName = scan.nextLine();

			System.out.println("Enter Email of new student");
			validEmail = scan.nextLine();

			System.out.println("Enter Address of new student");
			validAddress = scan.nextLine();

			System.out.println("Enter Faculty of new student");
			validFaculty = scan.nextLine();

			newStudent = new Student(Integer.parseInt(validStudentId), validName, validDate, validEmail, validAddress,
					validFaculty);

			studentList = new ArrayList<>();
			studentList.add(newStudent);
			writeToFile(studentList);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private static void editAStudent() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter student id to edit");
		String enteredStudentId = scan.nextLine();
		String validDate = null;
		while (true) {
				if (!validateUserId(enteredStudentId)) {
					System.out.println("Student id should always be a number");
					System.out.println("Enter valid student Id");
				}
					else {
						break;
					}
	}
		List<Student> students = readFile();
		if(!students.contains(Integer.parseInt(enteredStudentId))) {
			System.out.println("student Id does not exist");
		}
		else {
			for(Student student : students) {
				if(enteredStudentId.equals(String.valueOf(student.getStudentId()))){
					System.out.println("Full details of the student who will be edited is ");
					System.out.println("Full name "+student.getFullName());
					System.out.println("Email "+student.getEmail());
					System.out.println("Birthday "+student.getBirthDate());
					System.out.println("Address "+student.getAddress());
					System.out.println("Faculty "+student.getFaculty());
					
					System.out.println("Edit the selected student with id "+student.getStudentId());
					System.out.println("Enter Full Name");
					String fullName = scan.nextLine();
					System.out.println("Enter Email");
					String emailId = scan.nextLine();

					while (true) {
				System.out.println("Enter Birthdate for the student");
				String enteredDate = scan.nextLine();
				if (!isValidDate(enteredDate)) {
					System.out.println("Enter date in only yyyy/MM/dd format");
				} else {
					validDate = enteredDate;
					break;
				}
			}
					System.out.println("Edit the Address");
					String address = scan.nextLine();
					System.out.println("Edit the faculty ");
					String faculty = scan.nextLine();
					
					if(StringUtils.isNotBlank(fullName)) {
						student.setFullName(fullName);
					}
					else if(StringUtils.isNotBlank(validDate)) {
						student.setBirthDate(validDate);
					}
					else if(StringUtils.isNotBlank(emailId)) {
						student.setEmail(emailId);
					}
					else if(StringUtils.isNotBlank(address)) {
						student.setAddress(address);
					}
					else if(StringUtils.isNotBlank(faculty)) {
						student.setFaculty(faculty);
					}
					
					
					
				}
			}
		}
		
}
		private static void deleteAStudent() {

	}

	private static void quit() {

	}

*/	private static List<String> readFile() {

		String line = null;
        List<String> lines = new ArrayList<>();
	try {
		FileReader reader = new FileReader("C:\\Users\\Manjinder\\Studentsdb.dat");
		BufferedReader bufferedReader = new BufferedReader(reader);
      
       while((line = bufferedReader.readLine()) != null) {
            lines.addAll(Arrays.asList(line.split("\\r?\\n")));
       }   
       
 //      System.out.println(lines);
       bufferedReader.close();
       
       	}catch (Exception e) {
			e.printStackTrace();
		}
	return lines; 
	}

	private static boolean validateUserId(String userEnteredValue) {

		if (NumberUtils.isNumber(userEnteredValue)) {
			return true;
		}
		return false;

	}

	private static boolean isValidDate(String d) {
		// String regex = "^(1[0-2]|0[1-9])/(3[01]"
		// + "|[12][0-9]|0[1-9])/[0-9]{4}$";
		String regex = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) d);
		return matcher.matches();
	}

	private static void writeToFile(List<Student> students) {

		/*
		 * File file = new File("C:\\Users\\Manjinder\\Studentsdb.dat"); JsonEncoding
		 * enc = null; // FileWriter fStream = null; 1
		 * 
		 * try { if (!file.exists()) { file.createNewFile(); } // fStream = new
		 * FileWriter(file); if (students.size() == 0) {
		 * System.out.println("No Student is present"); } // JSONObject jsonObject = new
		 * JSONObject(); ObjectMapper mapper = new ObjectMapper(); SimpleDateFormat sdf
		 * = new SimpleDateFormat("yyyy-MM-dd");
		 * mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		 * 
		 * mapper.setDateFormat(sdf); Collections.sort(students, new
		 * StudentIdComparator()); enc = JsonEncoding.UTF8; JsonGenerator jg =
		 * mapper.getFactory().createGenerator(file, enc); mapper.writeValue(jg,
		 * students); jg.close(); } catch (IOException e) { e.printStackTrace(); }
		 */

		File file = new File("C:\\Users\\Manjinder\\Studentsdb.dat");
		int serialNo = 0;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			if (students.size() == 0) {
				System.out.println("No Student is present");
			}

			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true))); // append mode file

			Collections.sort(students, new StudentIdComparator());

			out.write(String.format("%10s %10s %30s %20s %25s %25s %20s\n", "No.", "ID", "Full Name","BirthDate","Email","Address","Faculty"));
			
			for(Student student:students) {
				serialNo++;
				out.write(String.format("%10d %10d %30s %20s %25s %25s %20s\n", serialNo,student.getStudentId(), student.getFullName(), student.getBirthDate(),student.getEmail(),student.getAddress(),student.getFaculty()));
			}
			
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
