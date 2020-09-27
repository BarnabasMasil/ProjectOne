import java.util.ArrayList;

/**
 * This class is the user class that contains the login username and password for login to the
 * password manager. This class also has the data class which holds data on matching url, username
 * and password.
 * 
 * @author tavish
 *
 */
public class Users {
  class Data {
    private String url;
    private String username;
    private String password;

    /**
     * Initializes the constructor of the Data class
     * 
     * @param url
     * @param username
     * @param password
     */
    public Data(String url, String username, String password) {
      this.url = url;
      this.username = username;
      this.password = password;
    }

    /**
     * gets the Url
     * 
     * @return url
     */
    public String getUrl() {
      return url;
    }

    /**
     * gets the username
     * 
     * @return username
     */
    public String getUsername() {
      return username;
    }

    /**
     * gets the password
     * 
     * @return password
     */
    public String getPassword() {
      return password;
    }

    /**
     * sets the value of url
     * 
     * @param url
     */
    public void setUrl(String url) {
      this.url = url;
    }

    /**
     * sets the value of username
     * 
     * @param username
     */
    public void setUsername(String username) {
      this.username = username;
    }

    /**
     * sets the value of password
     * 
     * @param password
     */
    public void setPassword(String password) {
      this.password = password;
    }
  }

  private String userLogin;
  private String userPassword;
  private ArrayList<String> urlList;
  private HashTableMap<String, Data> details;

  /**
   * Initializes the constructor of the Users class
   * 
   * @param username
   * @param password
   */
  public Users(String username, String password) {
    userLogin = username;
    userPassword = password;
    urlList = new ArrayList<String>();
    details = new HashTableMap<>();
  }

  /**
   * Adds the url, username, and password to the hash table map and adds the url to the ArrayList
   * 
   * @param url
   * @param username
   * @param password
   * @return
   */
  public boolean addDetails(String url, String username, String password) {
    urlList.add(url);
    return details.put(url, new Data(url, username, password));
  }

  /**
   * Prints the url, username, and password of the User
   * 
   * @param url
   */
  public void printDetails(String url) {
    System.out.println(details.get(url).getUrl());
    System.out.println(details.get(url).getUsername());
    System.out.println(details.get(url).getPassword());
  }

  /**
   * gets the User's username to login to Password Manager
   * 
   * @return
   */
  public String getLoginUsername() {
    return userLogin;
  }

  /**
   * gets the User's password to login to Password Manager
   * 
   * @return
   */
  public String getLoginPassword() {
    return userPassword;
  }

  /**
   * sets the User's username for the Password Manager
   * 
   * @param userLogin
   */
  public void setLoginUsername(String userLogin) {
    this.userLogin = userLogin;
  }

  /**
   * sets the User's password for the Password Manager
   * 
   * @param userPassword
   */
  public void setLoginPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  /**
   * gets the ArrayList of urls
   * 
   * @return
   */
  public ArrayList<String> getUrlList() {
    return urlList;
  }

  /**
   * gets the hash table map that stored the urls, usernames and passwords of the User
   * 
   * @return
   */
  public HashTableMap<String, Data> getDetails() {
    return details;
  }
}
