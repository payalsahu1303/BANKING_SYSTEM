/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class User {
	private Scanner sc;
	private Connection con;

    public User(Scanner sc, Connection con){
    	this.sc=sc;
    	this.con = con;
    }
    public void registration(){
    	sc.nextLine();
        System.out.println("Full name: ");
        String full_name = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        
        if(user_exist(email)) {
        	System.out.println("User already exists for this email addresss");
        	return;
        }
        
        String query = "INSERT INTO user(full_name, email, password) VALUES(?, ?, ?)" ;
        try {
        	
        	PreparedStatement preparedStatement = con.prepareStatement(query);
        	preparedStatement.setString(1, full_name);
        	preparedStatement.setString(2, email);
        	preparedStatement.setString(3, password);
        	int affectedRows = preparedStatement.executeUpdate();  
        		if(affectedRows > 0) {
        			System.out.println("Registration Successful.");
        		}
        		else {
        			System.out.println("Registration Failed.");
        		}
        }
        catch(Exception e){
        	System.out.println(e);
        }
    }
    
    public boolean user_exist(String email) {
		String query = "SELECT * FROM user WHERE email = ?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,  email);
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public String login(){
		sc.nextLine();
    	System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        String login_query = "SELECT * FROM User WHERE email = ? AND password = ?";
        try {
        	PreparedStatement preparedStatment = con.prepareStatement(login_query);
        	preparedStatment.setString(1, email);
        	preparedStatment.setString(2, password);
        	ResultSet resultSet = preparedStatment.executeQuery();
        	if(resultSet.next()) {
        		return email;
        	}
        	else {
        		return null;
        	}
        }
        catch(SQLException e){
        	e.printStackTrace();
        }
    
    	return null;
    }
}