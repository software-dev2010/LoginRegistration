package login.register.model;

public class UserBean {
	
	 private int id;
	 private String username; 
	 private String password; 
	 private String name;
	 private String email;
	 private String country;
	 private String town;
	 private int age;
	 
	 
	 
	public UserBean(int id, String username, String password, String name, String email, 
			String country, String town, int age) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.country = country;
		this.town = town;
		this.age = age;
	}
	
	public UserBean(String username, String password, String name, String email, 
			String country, String town, int age) {
		
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.country = country;
		this.town = town;
		this.age = age;
	}

	public UserBean() {
		
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

