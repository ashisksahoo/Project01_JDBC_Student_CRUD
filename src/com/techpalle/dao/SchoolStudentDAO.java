package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;

import com.techpalle.model.SchoolStudent;

public class SchoolStudentDAO 
{
	private static Connection con = null;
	private static Statement smt = null;
	private static PreparedStatement psmt = null;
	private static ResultSet res = null;
	private static final String dbDriver = "com.mysql.cj.jdbc.Driver";
	private static final String dbUrl = "jdbc:mysql://localhost:3306/jdbcproject";
	private static final String dbUsername = "root";
	private static final String dbPassword = "Ashis_admin$5";
	private static final String createTable = "CREATE TABLE student(student_id INT PRIMARY KEY AUTO_INCREMENT, student_name VARCHAR(100) NOT NULL, email VARCHAR(100) UNIQUE NOT NULL, subject VARCHAR(100) NOT NULL, age INT)";
	private static final String insertStudent = "INSERT INTO student(student_name, email, subject, age) VALUES (?, ?, ?, ?)";
	private static final String updateStudent = "UPDATE student SET student_name = ?, email = ?, subject = ?, age = ? WHERE student_id = ?";
	private static final String deleteStudent = "DELETE FROM student WHERE student_id = ?";
	private static final String showAllStudents = "SELECT * FROM student";
	
	private static Connection connectJDBC()
	{
		try 
		{
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	private static Connection closeStatement()
	{
		try 
		{
			if (smt != null)
			{
				smt.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	private static Connection closePreparedStatement()
	{
		try 
		{
			if (psmt != null)
			{
				psmt.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	private static Connection closeResultSet()
	{
		try 
		{
			if (res != null)
			{
				res.close();
			}
			if (smt != null)
			{
				smt.close();
			}
			if (con != null)
			{
				con.close();
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static void createTable()
	{
		try 
		{
			con = connectJDBC();
			smt = con.createStatement();
			smt.executeUpdate(createTable);
			System.out.println("table created successfully\n");
		}
		catch (SQLSyntaxErrorException e)
		{
			System.out.println("table already exists\n");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			closeStatement();
		}
	}
	
	public static void insertStudent(String student_name, String email, String subject, int age)
	{
		int count = 0;
		try 
		{
			con = connectJDBC();
			psmt = con.prepareStatement(insertStudent);
			psmt.setString(1, student_name);
			psmt.setString(2, email);
			psmt.setString(3, subject);
			psmt.setInt(4, age);
			if(psmt != null)
			{
				count = psmt.executeUpdate();
			}
			System.out.println(count + " record inserted\n");
		}
		catch (SQLIntegrityConstraintViolationException e)
		{
			System.out.println("duplicate entry\n");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			closePreparedStatement();
		}
	}
	
	public static void updateStudent(String student_name, String email, String subject, int age, int student_id)
	{
		try 
		{
			con = connectJDBC();
			psmt = con.prepareStatement(updateStudent);
			psmt.setString(1, student_name);
			psmt.setString(2, email);
			psmt.setString(3, subject);
			psmt.setInt(4, age);
			psmt.setInt(5, student_id);
			psmt.executeUpdate();
			System.out.println("record updated successfully\n");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			closePreparedStatement();
		}
	}
	
	public static void deleteStudent(int student_id)
	{
		try 
		{
			con = connectJDBC();
			psmt = con.prepareStatement(deleteStudent);
			psmt.setInt(1, student_id);
			psmt.executeUpdate();
			System.out.println("record deleted successfully\n");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			closePreparedStatement();
		}
	}
	
	public static ArrayList<SchoolStudent> showAllStudents()
	{
		ArrayList<SchoolStudent> stud = new ArrayList<SchoolStudent>();
		try 
		{
			con = connectJDBC();
			smt = con.createStatement();
			res = smt.executeQuery(showAllStudents);
			while (res.next())
			{
				int id = res.getInt("student_id");
				String name = res.getString("student_name");
				String mail = res.getString("email");
				String subj = res.getString("subject");
				int age = res.getInt("age");
				SchoolStudent ss = new SchoolStudent(id, name, mail, subj, age);
				stud.add(ss);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			closeResultSet();
		}
		return stud;
	}
}
