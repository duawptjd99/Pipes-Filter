package utillity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CourseList {
	protected ArrayList<Information> vCourse;
	String errorCode;

	public CourseList(ArrayList<String> lines, int counting) throws FileNotFoundException, IOException {

		this.vCourse = new ArrayList<Information>();

		for (int i = lines.size() - counting-1; i < lines.size(); i++) {
			String CorInfo = lines.get(i);
			// System.out.print(CorInfo);
			if (!CorInfo.equals("")) {
				this.vCourse.add((Information) new Course(CorInfo));
			}
		}
	}

	public ArrayList<Information> getAllCourseRecords() {
		return this.vCourse;
	}

	public Information getList(int index) {
		return this.vCourse.get(index);
	}

	public boolean isRegisteredCourse(String sSID) {
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course objCourse = (Course) this.vCourse.get(i);
			if (objCourse.match(sSID)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkID(Course course) {
		for (int i = 0; i < this.vCourse.size(); i++) {
			if (this.vCourse.get(i).getID().equals(course.getID())) {
				return true;
			}
		}
		return false;

	}

}
