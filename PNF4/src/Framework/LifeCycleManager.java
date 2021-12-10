/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Middle.MiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
	public static void main(String[] args) {
		try {
			CommonFilter studentSourceFilter = new SourceFilter(1, 1);
			CommonFilter courseSourceFilter = new SourceFilter(1, 1);
			CommonFilter middleFilter = new MiddleFilter(2, 2);
			CommonFilter successSinkFilter = new SinkFilter(1, 1);
			CommonFilter failSinkFilter = new SinkFilter(1, 1);

			studentSourceFilter.setFilePath("Students.txt");
			courseSourceFilter.setFilePath("Courses.txt");
			successSinkFilter.setFilePath("Success.txt");
			failSinkFilter.setFilePath("fail.txt");

			studentSourceFilter.connectOutputTo(0, middleFilter, 0);
			courseSourceFilter.connectOutputTo(0, middleFilter, 1);

			middleFilter.connectOutputTo(0, successSinkFilter, 0);
			middleFilter.connectOutputTo(1, failSinkFilter, 0);

			Thread thread1 = new Thread(studentSourceFilter);
			Thread thread2 = new Thread(courseSourceFilter);
			Thread thread3 = new Thread(middleFilter);
			Thread thread4 = new Thread(successSinkFilter);
			Thread thread5 = new Thread(failSinkFilter);

			thread1.start();
			thread2.start();
			thread3.start();
			thread4.start();
			thread5.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
