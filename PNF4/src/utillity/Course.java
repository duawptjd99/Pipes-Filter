package utillity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course extends Information implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String courseId;
	protected String name;
	protected String department;
	protected ArrayList<String> CoursesList;


	public Course(String inputString) {

		StringTokenizer stringTokenizer = new StringTokenizer(inputString);
		this.courseId = stringTokenizer.nextToken();
		this.name = stringTokenizer.nextToken();
		this.department = stringTokenizer.nextToken();
		this.CoursesList = new ArrayList<String>();
		while (stringTokenizer.hasMoreTokens()) {
			this.CoursesList.add(stringTokenizer.nextToken());
		}


	}

	public boolean match(String studentId) {
		return this.courseId.equals(studentId);
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		String stringReturn = this.courseId + " " + this.name+ " " +this.department;
		for (int i = 0; i < this.CoursesList.size(); i++) {
			stringReturn = stringReturn + " " + this.CoursesList.get(i).toString();
		}

		return stringReturn;
	}
	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return this.courseId;
	}

	@Override
	public String getDepartment() {
		// TODO Auto-generated method stub
		return this.department;
	}

	@Override
	public ArrayList<String> getCourseList() {
		// TODO Auto-generated method stub
		return this.CoursesList;
	}

	@Override
	public boolean addCourseList(String course) {
		// TODO Auto-generated method stub
		return false;
	}

}
