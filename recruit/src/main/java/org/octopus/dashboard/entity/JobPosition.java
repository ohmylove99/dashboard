package org.octopus.dashboard.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class JobPosition extends IdEntity {

	private String name;
	private String description;
	private String grade;
	private Date openTime;
	private Date closedTime;
	private String status;// opening/closed/

	private List<Recume> recumes = new ArrayList<Recume>();
	private OrgTree org;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Recume> getRecumes() {
		return recumes;
	}

	public OrgTree getOrg() {
		return org;
	}

	public void setOrg(OrgTree org) {
		this.org = org;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
