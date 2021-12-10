package utillity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentList {
	protected ArrayList<Information> vStudent;
	String errorCode;

	public StudentList(ArrayList<String> lines,int counting) throws FileNotFoundException, IOException {
		//BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
		this.vStudent = new ArrayList<Information>();
	
		for(int i = 0;i<counting+1;i++) 
		{
			String stuInfo = lines.get(i);
			//System.out.print(stuInfo);
			if (!stuInfo.equals("")) {
				this.vStudent.add((Information) new Student(stuInfo));
			}
			
		}
		
	}
	public ArrayList<Information> getAllCourseRecords() {
		return this.vStudent;
	}
	public Information getList(int index) {
		return this.vStudent.get(index);
	}

	public boolean isRegisteredStudent(String sSID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student objStudent = (Student) this.vStudent.get(i);
			if (objStudent.match(sSID)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkID(Student student) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			if (this.vStudent.get(i).getID().equals(student.getID())) {
			
				return true;
			}
		}
		return false;

	}

}
