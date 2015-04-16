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

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		TestDerbyServer tds = new TestDerbyServer();
		tds.testQueryData_spindle();

	}

}
