package login.register.model;

public class UserBean {
	
	 private int id;
	 private String username; 
	 private String password; 
	 private String name;
	 
	public UserBean(int id, String username, String password, String name) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public UserBean(String username, String name, String password) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
	}
	
	public int getId() {
		
		return id;
	}
	
	public void setUsername(int id) {
		
		this.id = id;
	}

	public String getUsername() {
		
		return username;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}
	
	public String getPassword() {
		
		return password;
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	 
}

