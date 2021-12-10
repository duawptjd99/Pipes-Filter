package utillity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student extends Information implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String studentId;
	protected String name;
	protected String department;
	protected ArrayList<String> completedCoursesList;
	int errorCode;

	public Student(String inputString) {

		StringTokenizer stringTokenizer = new StringTokenizer(inputString);
		this.studentId = stringTokenizer.nextToken();
		this.name = stringTokenizer.nextToken();
		this.name = this.name + " " + stringTokenizer.nextToken();
		this.department = stringTokenizer.nextToken();
		this.completedCoursesList = new ArrayList<String>();

		while (stringTokenizer.hasMoreTokens()) {
			this.completedCoursesList.add(stringTokenizer.nextToken());
		}

	}

	public boolean match(String studentId) {
		return this.studentId.equals(studentId);
	}

	public String getName() {
		return this.name;
	}

	
	public String toString() {
		String stringReturn = this.studentId + " " + this.name + " " + this.department;
		for (int i = 0; i < this.completedCoursesList.size(); i++) {
			stringReturn = stringReturn + " " + this.completedCoursesList.get(i).toString();
		}
		return stringReturn;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return this.studentId;
	}

	@Override
	public String getDepartment() {
		// TODO Auto-generated method stub
		return this.department;
	}

	@Override
	public ArrayList<String> getCourseList() {
		// TODO Auto-generated method stub
		return this.completedCoursesList;
	}

	@Override
	public boolean addCourseList(String course) {
		// TODO Auto-generated method stub
		return false;
	}

}
