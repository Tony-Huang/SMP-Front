package com.hdp.smp.front;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestEmbeddedDerby {


	static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	protected String dbName="smpdb";
	protected String connectionURL = "jdbc:derby:" + dbName + ";create=true";
	
	String table_DDL= "create table users( name varchar(50), age int, description varchar(50))";
	String row_insert1="insert into users(name, age,description) values('tony',41,'tony')";
	String row_insert2="insert into users(name, age,description) values('王凯',38,'王凯')";
	
	String sql_query = "select * from users";
	
	Connection getConn() throws ClassNotFoundException, SQLException {
		
		Class.forName(driver);
			
		Connection	conn = DriverManager.getConnection(connectionURL);
		
		return conn;
	}
	
	void testCreateTable() throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate(table_DDL);
		
		stmt.close();
		conn.close();
	}
	
	void testInsertData() throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate(row_insert1);
		stmt.executeUpdate(row_insert2);
		
		stmt.close();
		conn.close();
	}
	
	void testQueryData() throws ClassNotFoundException, SQLException {
	    Connection conn = getConn();
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql_query);
		
		while(rs.next()){
			  String name = rs.getString(1);
			  int age = rs.getInt(2);
			  String desc = rs.getString(3);
			  
			  System.out.println("name="+name +"  age="+age +"  desc="+desc);
			
		}
		
		rs.close();
		stmt.close();
		conn.close();
	}

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		TestEmbeddedDerby te = new TestEmbeddedDerby();
		//te.testCreateTable();
		
		//te.testInsertData();
		
		te.testQueryData();

	}


}
