/* ###############################################################
* Haina Confidential
*
* OCO Source Materials
*
* Haina Products: Spindle monitor platform
*
* (C) Copyright Haina Corp. 2015
*
* The source code for this program is not published or otherwise
* divested of its trade secrets, irrespective of what has been
* deposited with the China Copyright Office.
* ###############################################################
*/

package com.hdp.smp.front;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.jdbc.ClientDriver;

public class TestDerbyServer {
	
	static final String driver = "org.apache.derby.jdbc.ClientDriver";
	protected String dbName="smpdb";
	protected String connectionURL = "jdbc:derby://localhost:9005/" + dbName + ";create=true";
	
	
   Connection getConn() throws ClassNotFoundException, SQLException {
		
		Class.forName(driver);
			
		Connection	conn = DriverManager.getConnection(connectionURL);
		
		return conn;
	}
   
   void testQueryData_spindle() throws ClassNotFoundException, SQLException {
	    Connection conn = getConn();
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("select * from spindles ");
		
		while(rs.next()){
			  int id = rs.getInt(1);
			  String name = rs.getString(2);
			 // int age = rs.getInt(2);
			//  String desc = rs.getString(3);
			  
			 // System.out.println("name="+name +"  age="+age +"  desc="+desc);
			  System.out.println("id="+id+ " name="+name );
			
		}
		
		rs.close();
		stmt.close();
		conn.close();
	}

   void testInsertData_Spindle() throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		
		Statement stmt = conn.createStatement();
		
		for(int i=0 ;i<10;i++) {
			String sql ="insert into spindles ( name, description ) values "+ " ( '锭子"+i +"' , '锭子" +i+"')";
			System.out.println("sql= "+sql);
		   stmt.executeUpdate(sql);
		//stmt.executeUpdate(row_insert2);
		
		}
		
		stmt.close();
		conn.close();
	}
   
   void insertRoles() throws ClassNotFoundException, SQLException{
	    Connection conn = getConn();
		
		Statement stmt = conn.createStatement();
		String str1="insert into roles ( name_CN, name_EN, description_CN, description_EN ) values "+ " ( '管理员' ,'Admin', '具有所有权限','have all privileges.')";
		String str2="insert into roles ( name_CN, name_EN, description_CN, description_EN ) values "+ " ( '操作员' ,'Operators', '具有查看权限','have view privileges.')";
		
		  stmt.executeUpdate(str1);
		  stmt.executeUpdate(str2);
		stmt.close();
		conn.close();
   }
   
   void queryRoles() throws Exception {
		Connection conn = getConn();

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select * from roles ");

		while (rs.next()) {
			int id = rs.getInt(1);
			String name1 = rs.getString(2);
			String name2 = rs.getString(2);
			// int age = rs.getInt(2);
			// String desc = rs.getString(3);

			// System.out.println("name="+name +"  age="+age +"  desc="+desc);
			System.out.println("id=" + id + " name=" + name1 +"  name="+name2);

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
	public static void main(String[] args) throws Exception {
		TestDerbyServer tds = new TestDerbyServer();
		//tds.testQueryData_spindle();
		
		//tds.insertRoles();
		
		tds.queryRoles();

	}

}
