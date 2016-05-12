package org.octopus.functional.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.octopus.dashboard.data.Smoke;
import org.octopus.dashboard.data.TaskData;
import org.octopus.dashboard.entity.Task;
import org.octopus.dashboard.shared.mapper.JsonMapper;
import org.octopus.functional.BaseFunctionalTestCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class TaskRestFT extends BaseFunctionalTestCase {

	private static String resourceUrl = baseUrl + "/api/v1/task";

	private final RestTemplate restTemplate = new RestTemplate();
	private final JsonMapper jsonMapper = new JsonMapper();

	@Test
	@Category(Smoke.class)
	public void listTasks() {
		TaskList tasks = restTemplate.getForObject(resourceUrl, TaskList.class);
		assertThat(tasks).hasSize(5);
		assertThat(tasks.get(0).getTitle()).isEqualTo("Study PlayFramework 2.0");
	}

	@Test
	@Category(Smoke.class)
	public void getTask() {
		Task task = restTemplate.getForObject(resourceUrl + "/{id}", Task.class, 1L);
		assertThat(task.getTitle()).isEqualTo("Study PlayFramework 2.0");
	}

	@Test
	@Category(Smoke.class)
	public void createUpdateAndDeleteTask() {

		// create
		Task task = TaskData.randomTask();

		URI createdTaskUri = restTemplate.postForLocation(resourceUrl, task);
		System.out.println(createdTaskUri.toString());
		Task createdTask = restTemplate.getForObject(createdTaskUri, Task.class);
		assertThat(createdTask.getTitle()).isEqualTo(task.getTitle());

		// update
		String id = StringUtils.substringAfterLast(createdTaskUri.toString(), "/");
		task.setId(new Long(id));
		task.setTitle(TaskData.randomTitle());

		restTemplate.put(createdTaskUri, task);

		Task updatedTask = restTemplate.getForObject(createdTaskUri, Task.class);
		assertThat(updatedTask.getTitle()).isEqualTo(task.getTitle());

		// delete
		restTemplate.delete(createdTaskUri);

		try {
			restTemplate.getForObject(createdTaskUri, Task.class);
			fail("Get should fail while feth a deleted task");
		} catch (HttpStatusCodeException e) {
			assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void invalidInput() {

		// create
		Task titleBlankTask = new Task();
		try {
			restTemplate.postForLocation(resourceUrl, titleBlankTask);
			fail("Create should fail while title is blank");
		} catch (HttpStatusCodeException e) {
			assertThat(e.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			Map messages = jsonMapper.fromJson(e.getResponseBodyAsString(), Map.class);
			assertThat(messages).hasSize(1);
			assertThat(messages.get("title")).isIn("may not be empty", "不能为空");
		}

		// update
		titleBlankTask.setId(1L);
		try {
			restTemplate.put(resourceUrl + "/1", titleBlankTask);
			fail("Update should fail while title is blank");
		} catch (HttpStatusCodeException e) {
			assertThat(e.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			Map messages = jsonMapper.fromJson(e.getResponseBodyAsString(), Map.class);
			assertThat(messages).hasSize(1);
			assertThat(messages.get("title")).isIn("may not be empty", "不能为空");
		}
	}

	private static class TaskList extends ArrayList<Task> {
	}
}
