package com.techpalle.model;

public class SchoolStudent 
{
	private int student_id;
	private String student_name;
	private String email;
	private String subject;
	private int age;
	
	public int getStudent_id() 
	{
		return student_id;
	}
	public void setStudent_id(int student_id) 
	{
		this.student_id = student_id;
	}
	public String getStudent_name() 
	{
		return student_name;
	}
	public void setStudent_name(String student_name) 
	{
		this.student_name = student_name;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getSubject() 
	{
		return subject;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	public int getAge() 
	{
		return age;
	}
	public void setAge(int age) 
	{
		this.age = age;
	}
	
	public SchoolStudent() 
	{
		super();
	}
	public SchoolStudent(String student_name, String email, String subject, int age) 
	{
		super();
		this.student_name = student_name;
		this.email = email;
		this.subject = subject;
		this.age = age;
	}
	public SchoolStudent(int student_id, String student_name, String email, String subject, int age) 
	{
		super();
		this.student_id = student_id;
		this.student_name = student_name;
		this.email = email;
		this.subject = subject;
		this.age = age;
	}
}
