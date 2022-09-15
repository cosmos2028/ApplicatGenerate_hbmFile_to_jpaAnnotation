package com.github.javaparser.pojo;

import java.io.Serializable;

public class entityTestPeso implements Serializable
{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String text;
	
	public entityTestPeso() 
	{
		super();
	}

	public entityTestPeso(String text) {
		super();
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	

}
