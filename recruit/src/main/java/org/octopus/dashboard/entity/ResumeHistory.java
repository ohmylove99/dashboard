package org.octopus.dashboard.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "ss_jobhis")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public class ResumeHistory extends BaseIdAuditEntity {
	private Job job;
	private String name;

	private String description;
	private String kvs;
	private byte[] originalDoc;
	private byte[] convertedDoc;
	//
	protected String pid;
	protected Long version;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

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

	public String getKvs() {
		return kvs;
	}

	public void setKvs(String kvs) {
		this.kvs = kvs;
	}

	public byte[] getOriginalDoc() {
		return originalDoc;
	}

	public void setOriginalDoc(byte[] originalDoc) {
		this.originalDoc = originalDoc;
	}

	public byte[] getConvertedDoc() {
		return convertedDoc;
	}

	public void setConvertedDoc(byte[] convertedDoc) {
		this.convertedDoc = convertedDoc;
	}

	@ManyToOne
	@JoinColumn(name = "job_id")
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
