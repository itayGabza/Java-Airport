import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBase {

//fields
	Connection conn;
	Statement stmt;
	Statement stmt2;


	public DataBase(){ // constructor
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String test="jdbc:mysql://localhost:3306/test";
			conn=DriverManager.getConnection(test, "root", "root");
			stmt=conn.createStatement();
			stmt2=conn.createStatement();
		} 
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) {  e.printStackTrace(); }
		
		System.out.println("constructor goood job");
	}
	
	

	public void createTables(String table1Name, String table2Name, boolean tablesExistAlready){ //create tables method
		try {
			
			if (tablesExistAlready){
				stmt.executeUpdate("Drop TABLE "+table1Name);
				stmt.executeUpdate("Drop TABLE "+table2Name);
			}
				
				String createtable1="CREATE TABLE "+table1Name+"(ID varchar(10), Passengers int, Cargo int, Cost int, isSecurityIssue bool, timeInAirfield int)"; // string to create table 1
				String createtable2="CREATE TABLE "+table2Name+"(ID varchar(10),  Passengers int, Destination varchar(30), timeInAirfield int)"; //string to create table 2

				stmt.executeUpdate(createtable1);
				stmt.executeUpdate(createtable2);
			
				
			} catch (SQLException e) { e.printStackTrace(); }

		}
	
	public void insertToTakeOffsTable(String tableName,FlightDetails fd){ //insert to A table
	String str="INSERT INTO " +tableName+"(ID, Passengers, Destination, timeInAirfield )"+" VALUES('"
			+ fd.getFlightCode().toString()+"','" 
		//	+fd.GetPeopleAmountToString()+ "','"
			+fd.getNumOfPassengers()+ "','"
			+ fd.getDestination()+"','"
		//	+fd.GetTotalTimeAtTheAirportToString()+ "')" ;
			+fd.getTotalTime()+ "')" ;
		 try {
			stmt.executeUpdate(str);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		 System.out.println("take ooffff suucsssful");
	}
	
	public void insertToLandingsTable(String tableName, FlightDetails fd){ //insert to landing table
		String str="INSERT INTO " +tableName+"(ID, Passengers, Cargo, Cost, isSecurityIssue, timeInAirfield )"+" VALUES('"
				+ fd.getFlightCode()+"','" 
			//	+fd.GetPeopleAmountToString()+ "','"
				+fd.getNumOfPassengers()+ "','"
				+fd.getCargo()+ "','"
				+fd.getRepairCost()+ "','"
				+fd.getSuspicious()+ "','"				
				+fd.getTotalTime()+ "')" ;
		
			 try {
				stmt.executeUpdate(str);
			} catch (SQLException e) {		
				e.printStackTrace();
			}
		}
	
	
	public ResultSet extract(String tableName){ //pull out data method
		ResultSet result=null;		
		String str="select * from " + tableName;
		try {
			
			result=stmt.executeQuery(str);
		
			
			
		} catch (SQLException e) { e.printStackTrace();	}
		
	return result;
		
	}
	public ResultSet extract2(String tableName){
		ResultSet result=null;		
		String str="select * from " + tableName;
		try {
			
			result=stmt2.executeQuery(str);
		
			
			
		} catch (SQLException e) { e.printStackTrace();	}
		
	return result;
		
	}
}