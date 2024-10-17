/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Account {
	private Connection con;
	private Scanner sc;
	public Account(Connection con, Scanner sc){
	    this.con = con;
	    this.sc = sc;

	}
	public long open_account(String email){
	    if(!account_exist(email)) {
	        String open_account_query = "INSERT INTO Accounts(account_number, full_name, email, balance, security_pin) VALUES(?, ?, ?, ?, ?)";
	        sc.nextLine();
	        System.out.print("Enter Full Name: ");
	        String full_name = sc.nextLine();
	        System.out.print("Enter Initial Amount: ");
	        double balance = sc.nextDouble();
	        sc.nextLine();
	        System.out.print("Enter Security Pin: ");
	        String security_pin = sc.nextLine();
	        try {
	            long account_number = generateAccountNumber();
	            PreparedStatement preparedStatement = con.prepareStatement(open_account_query);
	            preparedStatement.setLong(1, account_number);
	            preparedStatement.setString(2, full_name);
	            preparedStatement.setString(3, email);
	            preparedStatement.setDouble(4, balance);
	            preparedStatement.setString(5, security_pin);
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                return account_number;
	            } else {
	                throw new RuntimeException("Account Creation failed!!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    throw new RuntimeException("Account Already Exist");

	}

	public long getAccount_number(String email) {
	    String query = "SELECT account_number from Accounts WHERE email = ?";
	    try{
	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        preparedStatement.setString(1, email);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if(resultSet.next()){
	            return resultSet.getLong("account_number");
	        }
	    }catch (SQLException e){
	        e.printStackTrace();
	    }
	    throw new RuntimeException("Account Number Doesn't Exist!");
	}

	private long generateAccountNumber() {
	    try {
	        Statement statement = con.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT account_number from Accounts ORDER BY account_number DESC LIMIT 1");
	        if (resultSet.next()) {
	            long last_account_number = resultSet.getLong("account_number");
	            return last_account_number+1;
	        } else {
	            return 10000100;
	        }
	    }catch (SQLException e){
	        e.printStackTrace();
	    }
	    return 10000100;
	}

	public boolean account_exist(String email){
	    String query = "SELECT account_number from Accounts WHERE email = ?";
	    try{
	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        preparedStatement.setString(1, email);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if(resultSet.next()){
	            return true;
	        }else{
	            return false;
	        }
	    }catch (SQLException e){
	        e.printStackTrace();
	    }
	    return false;

	}
	}
