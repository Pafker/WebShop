package com.pafker.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {

		final String jdbcUrl = "jdbc:mysql://localhost:3306/web-shop?useSSL=false";
		final String user = "hbstudent";
		final String password = "hbstudent";

		try {
			System.out.println("Connecting to database: " + jdbcUrl);

			Connection conn = DriverManager.getConnection(jdbcUrl, user,
					password);

			System.out.println("Connection successful.");

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
