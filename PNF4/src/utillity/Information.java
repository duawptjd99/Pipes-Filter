package utillity;

import java.util.ArrayList;

public abstract class Information {

	public abstract String getID();

	public abstract String getName();

	public abstract String getDepartment();

	public abstract ArrayList<String> getCourseList();
	
	public abstract boolean addCourseList(String course);
	
}
