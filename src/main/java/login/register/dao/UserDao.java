package login.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import login.register.model.UserBean;

public class UserDao {
	
	private DataSource dataSource;
	
	public UserDao(DataSource theDataSource) {
		
		dataSource = theDataSource;
	}
	
	 public int insertUser(UserBean user) throws Exception {
		 
		 	int status = 0;
		 	
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				
				// get db connection
				myConn = dataSource.getConnection();
				
				// create sql for insert
				String sql = "INSERT INTO user "
						   + "(user_name, name, password) "
						   + "VALUES (?, ?, ?)";
				
				myStmt = myConn.prepareStatement(sql);
				
				// set the param values for the user
				myStmt.setString(1, user.getUsername());
				myStmt.setString(2, user.getName());
				myStmt.setString(3, user.getPassword());
				
				// execute sql insert
				myStmt.execute();
				
				return status;
			} finally {
				
				// clean up JDBC objects
				close(myConn, myStmt, null);
			}
			
	 }
			

	
	 public UserBean getUser(String userid, String pass) throws Exception {
		 			
		 	UserBean user = new UserBean();
		 
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null; 
			
			String query = "SELECT * FROM user WHERE username = ? AND password = ?";
					 
			try {			
				
				myConn = dataSource.getConnection();
		
				myStmt = myConn.prepareStatement(query); 
				myStmt.setString(1, userid);
				myStmt.setString(2, pass);
				
				myRs = myStmt.executeQuery(query); 
							
				while (myRs.next()) {
					
					user.setUsername(myRs.getString(1)); 
					user.setName(myRs.getString(2)); 
					user.setUsername(myRs.getString(3));  
				}
							
				return user;
						
			} finally {
					
					close(myConn, myStmt, myRs);
			}
	 }
	 
		private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

			try {
				
				if (myRs != null) {
					
					myRs.close();
				}
				
				if (myStmt != null) {
					
					myStmt.close();
				}
				
				if (myConn != null) {
					
					myConn.close();   // doesn't really close it ... just puts back in connection pool
				}                     // and makes it available for someone else to use
			} catch (Exception exc) {
				
				System.out.println(exc);
			}
		}

}
