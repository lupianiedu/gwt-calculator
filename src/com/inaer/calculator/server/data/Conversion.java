package com.inaer.calculator.server.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Conversion {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private Float number;
	@Persistent
	private String binary;

	public Conversion(Float number, String binary) {
		super();
		this.number = number;
		this.binary = binary;
	}

	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}

	public String getBinary() {
		return binary;
	}

	public void setBinary(String binary) {
		this.binary = binary;
	}
}
