package org.octopus.dashboard.entity;

/**
 * Default org Tree:-1 - root; 0 - Company; 1 - Depart ;2 - Unit ;3 - Team
 * 
 * @author llei
 *
 */
public class OrgTree {
	private String name;
	private int level;
	private OrgTree parent;

	public OrgTree getParent() {
		return parent;
	}

	public void setParent(OrgTree parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
