/*
* This class holds data on the Urls, Password, and Usernames
*/
public class Data{
  
  private String url;
  private String username;
  private String password;
  
  public Data(String url, String username, String password){
    this.url = url;
    this.username = username;
    this.password = password;
  }
  
  public String getUrl(){
    return url;
  }

  public String getUsername(){
    return username;
  }
 
  public String getPassword(){
    return password;
  }

}
