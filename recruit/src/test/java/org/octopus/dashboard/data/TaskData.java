package org.octopus.dashboard.data;

import org.octopus.dashboard.entity.Task;
import org.octopus.dashboard.entity.User;
import org.octopus.dashboard.test.data.RandomData;

public class TaskData {

	public static Task randomTask() {
		Task task = new Task();
		task.setTitle(randomTitle());
		User user = new User(1L);
		task.setUser(user);
		return task;
	}

	public static String randomTitle() {
		return RandomData.randomName("Task");
	}
}
