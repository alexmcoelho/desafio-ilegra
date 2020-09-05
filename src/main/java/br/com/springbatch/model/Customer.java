package br.com.springbatch.model;

import java.math.BigDecimal;

public class Customer extends GenericId{
	private String name;
	private BigDecimal salary;
	
	public Customer(String name) {
		super();
		this.name = name;
	}
	public Customer() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
}
