package com.db;

import com.configData_Util.Constant;

public class ConnectionTester {
	
	public static void main(String[] args) {
		
		ConnectionManager con = new ConnectionManager();
		
		String query = ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath()+"groupInterviewStage.sql");
		
		con.executeQuery(query);
		
		con.printAllText();
		
		String applicantName = con.getColumnValues("label").get(0);
		
		System.out.println(applicantName);
		
		con.closeConnection();
		
	}
	
	/**
	 * 
	 */
	/*
	 * private static void tester() { ConnectionManager con = new
	 * ConnectionManager();
	 * 
	 * String queryString =
	 * ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath()+
	 * "groupInterviewStage.sql"); con.executeQuery(queryString);
	 * con.printAllText(); System.out.println(con.getColumnValues("label",
	 * "value==2")); List<String> ls = con.getColumnValues("applicant_id");
	 * System.out.println(ls); con.closeConnection(); }
	 */
	
}
