package com.techpalle.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.techpalle.dao.SchoolStudentDAO;
import com.techpalle.model.SchoolStudent;

public class SchoolMain 
{
	private static void showOptions()
    {
        System.out.println("press 1: create student table");
        System.out.println("press 2: insert data into the table");
        System.out.println("press 3: update data of the table");
        System.out.println("press 4: show all student records");
        System.out.println("press 5: delete record from the table");
        System.out.println("press 6: exit from the program");
    }
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int opt = 0;
		showOptions();
		do 
		{
			opt = sc.nextInt();
			switch (opt) 
			{
			case 1: 
				SchoolStudentDAO.createTable();
				break;
			case 2:
				System.out.println("enter student's name");
				String nm = sc.next();
				System.out.println("enter student's email");
				String ml = sc.next();
				System.out.println("enter student's subject");
				String sj = sc.next();
				System.out.println("enter student's age");
				int ag = sc.nextInt();
				SchoolStudentDAO.insertStudent(nm, ml, sj, ag);
				break;
			case 3:
				try
				{
					System.out.println("enter student's name");
					String name = sc.next();
					System.out.println("enter student's email");
					String mail = sc.next();
					System.out.println("enter student's subject");
					String subj = sc.next();
					System.out.println("enter student's age");
					int age = sc.nextInt();
					System.out.println("enter student's id");
					int id = sc.nextInt();
					SchoolStudentDAO.updateStudent(name, mail, subj, age, id);
				}
				catch (InputMismatchException e) 
				{ 
					System.out.println("please insert proper input\n"); 
				}
				break;
			case 4:
				ArrayList<SchoolStudent> std = SchoolStudentDAO.showAllStudents();
				for (SchoolStudent sStd : std) 
				{
					System.out.println(sStd.getStudent_id()+"\t"+sStd.getStudent_name()+"\t"+sStd.getEmail()+"\t"+sStd.getSubject()+"\t"+sStd.getAge());
				}
				break;
			case 5:
				System.out.println("enter student id to delete the record");
				int id = sc.nextInt();
				SchoolStudentDAO.deleteStudent(id);
				break;
			case 6:
				System.out.println("enter number 6 to exit the program");
				int ext = sc.nextInt();
				System.out.println("program exited successfully");
				System.exit(ext);
				break;
			default:
				System.out.println("invalid input");
				break;
			}
		} 
		while (opt != 6);
	}
}
