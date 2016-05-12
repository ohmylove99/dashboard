package org.octopus.functional.gui;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.octopus.dashboard.data.Smoke;
import org.octopus.dashboard.data.TaskData;
import org.octopus.dashboard.entity.Task;
import org.octopus.functional.BaseSeleniumTestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TaskGuiFT extends BaseSeleniumTestCase {

	@Test
	@Category(Smoke.class)
	public void viewTaskList() {
		s.open("/task/");
		WebElement table = s.findElement(By.id("contentTable"));
		assertThat(s.getTable(table, 0, 0)).isEqualTo("Release");
	}

	@Test
	@Category(Smoke.class)
	public void crudTask() {
		s.open("/task/");

		// create
		s.click(By.linkText(""));

		Task task = TaskData.randomTask();
		s.type(By.id("task_title"), task.getTitle());
		s.click(By.id("submit_btn"));

		assertThat(s.isTextPresent("")).isTrue();

		// update
		s.click(By.linkText(task.getTitle()));
		assertThat(s.getValue(By.id("task_title"))).isEqualTo(task.getTitle());

		String newTitle = TaskData.randomTitle();
		s.type(By.id("task_title"), newTitle);
		s.click(By.id("submit_btn"));
		assertThat(s.isTextPresent("")).isTrue();

		// search
		s.type(By.name("search_LIKE_title"), newTitle);
		s.click(By.id("search_btn"));
		assertThat(s.getTable(By.id("contentTable"), 0, 0)).isEqualTo(newTitle);

		// delete
		s.click(By.linkText(""));
		assertThat(s.isTextPresent("")).as("").isTrue();
	}

	@Test
	public void inputInValidateValue() {
		s.open("/task/");
		s.click(By.linkText(""));
		s.click(By.id("submit_btn"));

		assertThat(s.getText(By.xpath("//fieldset/div/div/span"))).isEqualTo("");
	}
}
