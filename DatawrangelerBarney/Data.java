// --== CS400 File Header Information ==--
// Name: Barnabas Masil Adrian anak Christopher
// Email: adriananakch@wisc.edu
// Team: GA
// Role: Data Wrangler
// TA: Daniel Kiel
// Lecturer: Gary Dahl
// Notes to Grader:

/**
 * This class holds data on matching url, username, and password
 * 
 * @author barna
 *
 */
public class Data {

  private String url;
  private String username;
  private String password;

  public Data(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  // Getters and Setters

  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }


}
