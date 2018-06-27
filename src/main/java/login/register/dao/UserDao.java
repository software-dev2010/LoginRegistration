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
			

	 public UserBean getUser(String theUsername, String thePassword) throws Exception {
			
			UserBean theUser = null;
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
					 
			try {	
				
				System.out.println("Asta se afisaza 1");
				
				// get connection to database
				myConn = dataSource.getConnection();
				
				System.out.println("Asta se afisaza 2");
				
				// create sql to get selected student
				String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";
				
				// create prepared statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setString(1, theUsername);
				myStmt.setString(2, thePassword);
				
				// execute statement
				myRs = myStmt.executeQuery();
							
				if (myRs.next()) {
					
					String username = myRs.getString("user_name");
					String name = myRs.getString("name");
					String pass = myRs.getString("password");
					
					// use the studentId during construction
					theUser = new UserBean(username, name, pass);
				}
							
				return theUser;
						
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
