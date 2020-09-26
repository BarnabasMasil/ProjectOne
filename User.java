public class User {
  private String username, password;

  public User() {
    this.password = this.username = "";
  }

  /**
   * Create a new user with set password and username
   *
   */
  public User(String username, String password) {
    setCredentials(username, password);
  }

  /**
   * Gets username of User
   * 
   * @return username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets password of User
   * 
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * This method sets the inputed username and password combination
   *
   */
  public void setCredentials(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * ToString method prints out text of user + password
   * 
   * @return a string representation of outputs
   */
  @Override
  public String toString() {
    return ("Username: " + getUsername() + ", Password: " + getPassword());
  }
}
