/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Framework.CommonFilterImpl;
import utillity.CourseList;
import utillity.Information;
import utillity.StudentList;
import utillity.ToChar;

public class MiddleFilter extends CommonFilterImpl {

	private int totalOut;
	private int totalIn;
	public StudentList studentList;
	public CourseList courseList;
	ArrayList<Information> success = new ArrayList<Information>();
	ArrayList<Information> fail = new ArrayList<Information>();
	ArrayList<Information> Courses = new ArrayList<Information>();

	public MiddleFilter(int totalOut, int totalIn) {
		super(totalOut, totalIn);

		this.totalIn = totalIn;
		this.totalOut = totalOut;
	}

	@Override
	public boolean specificComputationForFilter() throws IOException {
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		int byte_read = 0;
		ArrayList<String> lines = new ArrayList<String>();
		int counting = 0;

		for (int i = 0; i < totalIn; i++) {
			boolean count = true;
			while (count) {
				String line = "";
				while (byte_read != '\n' && byte_read != -1) {
					byte_read = in.get(i).read();
					if (byte_read != -1) {
						buffer[idx++] = (byte) byte_read;
						line += Character.toString((char) byte_read);
					}
				}
				lines.add(line);
				line = "";
				if (byte_read == -1) {
					setList(lines, counting, i);
					count = false;
				}
				idx = 0;
				byte_read = '\0';
				counting++;
			}
			counting = 0;
		}
		FindCourse();
		FindFail2();
		FindSuccess();
		write_byte();
		return true;
	}

	// utility char 변환후 output
	public void write_byte() throws IOException {
		ToChar SuccessToChar = new ToChar(success);
		ToChar FailToChar = new ToChar(fail);

		for (int i = 0; i < SuccessToChar.getbuffer().size(); i++) {
			out.get(0).write(SuccessToChar.getbuffer().get(i));
		}
		for (int i = 0; i < FailToChar.getbuffer().size(); i++) {
			out.get(1).write(FailToChar.getbuffer().get(i));
		}

	}

	// 성공한 학생 분류
	public void FindSuccess() {
		success = this.studentList.getAllCourseRecords();
		for (int i = 0; i < this.success.size(); i++) {
			for (int j = 0; j < fail.size(); j++) {
				if (success.get(i).getName().equals(fail.get(j).getName())) {
					success.remove(i);
				}
			}
		}
	}

	// utility착용
	public void setList(ArrayList<String> lines, int counting, int i) throws FileNotFoundException, IOException {

		if (i == 0) {
			this.studentList = new StudentList(lines, counting);
		} else if (i == 1) {
			this.courseList = new CourseList(lines, counting);
		}

	}

	// 선이수과목 존재강좌 찾기
	public void FindCourse() {

		for (int j = 0; j < this.courseList.getAllCourseRecords().size(); j++) {
			if (!this.courseList.getList(j).getCourseList().isEmpty()) {
				Courses.add(this.courseList.getList(j));

			}

		}

	}

	// 실패한 학생 & 중복 골라내기
	public void FindFail2() {

		for (int i = 0; i < this.studentList.getAllCourseRecords().size(); i++) {
			int count = 0;
			for (int j = 0; j < this.Courses.size(); j++) {
				if (this.studentList.getList(i).getCourseList().contains(this.Courses.get(j).getID())) {
					count++;
				}

			}
			for (int j = 0; j < this.Courses.size(); j++) {
				if (this.Courses.get(j).getCourseList().size() == 1) {
					if (this.studentList.getList(i).getCourseList().contains(this.Courses.get(j).getID())) {
						if (this.studentList.getList(i).getCourseList()
								.contains(this.Courses.get(j).getCourseList().get(0))) {
							count--;
						}
					}
				} else if (this.Courses.get(j).getCourseList().size() == 2) {
					if (this.studentList.getList(i).getCourseList().contains(this.Courses.get(j).getID())) {
						if (this.studentList.getList(i).getCourseList()
								.contains(this.Courses.get(j).getCourseList().get(0))
								&& this.studentList.getList(i).getCourseList()
										.contains(this.Courses.get(j).getCourseList().get(1))) {
							count--;
						}
					}
				}

			}

			if (count != 0) {
				fail.add(this.studentList.getList(i));
			}

		}

	}

}
