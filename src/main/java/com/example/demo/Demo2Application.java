package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}
	
	public static class DatabaseConnection {
		public static Connection getConnection() throws SQLException {
			String url = "jdbc:h2:mem:testdb";
			String username = "sa";
			String password = "";
			return DriverManager.getConnection(url, username, password);
		}
	}

}
