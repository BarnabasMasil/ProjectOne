// --== CS400 File Header Information ==--
// Name: Barnabas Masil Adrian anak Christopher
// Email: adriananakch@wisc.edu
// Team: GA
// Role: Data Wrangler
// TA: Daniel Kiel
// Lecturer: Gary Dahl
// Notes to Grader:
import java.util.LinkedList;

/**
 * This class is the user class that contains the login username and password for login in to the
 * actual password manager. This class also holds a hashtable for the information on urls,
 * usernames, and passwords
 * 
 * @author barna
 *
 */
public class User {

  private String loginUsername;
  private String loginPassword;

  private HashTableMap<String, Data> credentials;
  private LinkedList<String> listOfUrls;

  public User(String username, String password) {
    loginUsername = username;
    loginPassword = password;
    credentials = new HashTableMap<>();
    listOfUrls = new LinkedList<>();
  }

  public boolean addCredential(String url, String username, String password) {
    listOfUrls.add(url);
    return credentials.put(url, new Data(url, username, password));
  }

  public void printCredentials(String url) {
    System.out.println(credentials.get(url).getUrl());
    System.out.println(credentials.get(url).getUsername());
    System.out.println(credentials.get(url).getPassword());

  }

  // Getters and Setters

  public String getLoginUsername() {
    return loginUsername;
  }

  public String getLoginPassword() {
    return loginPassword;
  }

  public void setLoginUsername(String username) {
    this.loginUsername = username;
  }

  public void setLoginPassword(String password) {
    loginPassword = password;
  }

  public HashTableMap<String, Data> getCredentials() {
    return credentials;
  }

  public LinkedList<String> getListOfUrls() {
    return listOfUrls;
  }

}
