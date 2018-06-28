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
								
				// get connection to database
				myConn = dataSource.getConnection();
								
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
					
					int id = myRs.getInt("id");
					String username = myRs.getString("user_name");
					String name = myRs.getString("name");
					String pass = myRs.getString("password");
					String email = myRs.getString("email");
					String country = myRs.getString("country");
					String town = myRs.getString("town");
					int age = myRs.getInt("age");
					
					// use the userId during construction
					theUser = new UserBean(id, username, pass, name, email, country, town, age);
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
				
				System.out.println("A intervenit aceasta exceptie"); 
				System.out.println(exc);
			}
		}

		public void updateUser(UserBean theUser) throws Exception {  
			
			Connection myConn = null;
			PreparedStatement myStmt = null;

			try {
				
				// get db connection
				myConn = dataSource.getConnection();
				
				// create SQL update statement
				String sql = "UPDATE user "
							+ "SET user_name = ?, name = ?, password = ?, country = ?, town = ?, email = ?, age = ? "
							+ "WHERE id = ?";
				
				// prepare statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setString(1, theUser.getUsername());
				myStmt.setString(2, theUser.getName());
				myStmt.setString(3, theUser.getPassword());
				myStmt.setString(4, theUser.getCountry());
				myStmt.setString(5, theUser.getTown());
				myStmt.setString(6, theUser.getEmail());
				myStmt.setInt(7, theUser.getAge());
				myStmt.setInt(8, theUser.getId());
				
				// execute SQL statement
				myStmt.execute();
			}
			finally {
				
				// clean up JDBC objects
				close(myConn, myStmt, null);
			}
			
		}

		public void deleteUser(String theUserId) throws Exception {
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				
				// convert student id to int
				int userId = Integer.parseInt(theUserId);
				
				// get connection to database
				myConn = dataSource.getConnection();
				
				// create sql to delete user
				String sql = "DELETE FROM user WHERE id = ?";
				
				// prepare statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setInt(1, userId);
				
				// execute sql statement
				myStmt.execute();
			}
			finally {
				
				// clean up JDBC code
				close(myConn, myStmt, null);
			}	
		}
}
