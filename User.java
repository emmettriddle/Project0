package dev.riddle.models;

public abstract class User {

	private int userId;
    private String email;
    private String password;
    private String fName;
    private String lName;
    private double initialDeposit;
   
	private boolean isEmployee;
    
	public User(String email, String password, String fName, String lName) {
		super();
		this.email = email;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
	}
	
	public User() {
		
	}
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public void setCustomer(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	
	

	public double getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	@Override
	public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((fName == null) ? 0 : fName.hashCode());
			result = prime * result + (isEmployee ? 1231 : 1237);
			result = prime * result + ((lName == null) ? 0 : lName.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + userId;
			return result;
		}

	@Override
	public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (fName == null) {
				if (other.fName != null)
					return false;
			} else if (!fName.equals(other.fName))
				return false;
			if (isEmployee = other.isEmployee)
				return false;
			if (lName == null) {
				if (other.lName != null)
					return false;
			} else if (!lName.equals(other.lName))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (userId != other.userId)
				return false;
			return true;
		}

	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", firstName=" + fName + ", lastName=" + lName
				+ "]\n";
	}
	
}
