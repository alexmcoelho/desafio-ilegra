package br.com.springbatch.model;

public class Salesman extends GenericId{

	private String name;
	private String businessArea;
	
	public Salesman() {
	}
	public Salesman(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
}
