package org.octopus.dashboard.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Recume extends IdEntity {

	private String name;
	private String description;
	private String kvs;
	private String originalDoc;
	private String convertedDoc;

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

	public String getOriginalDoc() {
		return originalDoc;
	}

	public void setOriginalDoc(String originalDoc) {
		this.originalDoc = originalDoc;
	}

	public String getConvertedDoc() {
		return convertedDoc;
	}

	public void setConvertedDoc(String convertedDoc) {
		this.convertedDoc = convertedDoc;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
