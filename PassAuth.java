import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class PassAuth {


  private HashTableMap<String, String> PassMap = new HashTableMap<>();

  public PassAuth() {
    this.PassMap = new HashTableMap<>();
  }

  /**
   * Check if username exists by using the containsKey method.
   * 
   * @return true if PassMap contains the correct key
   */
  public boolean userNameExist(String username) {
    return (PassMap.containsKey(username));
  }

  /**
   * Reads the file line by line to find password/username
   * 
   * @param filename - file that contains password and username combo
   * @throws FileNotFoundException if file is not found
   */
  public void readFile(String filename) {
    Scanner fileReader;
    try {
      fileReader = new Scanner(new File(filename));
      while (fileReader.hasNextLine()) {
        String[] data = fileReader.nextLine().trim().split(",");
        String username = data[0];
        String password = data[1];
        addUser(username, password);
      }
      fileReader.close();
    } catch (FileNotFoundException fnfe) {
      System.out.println(filename + " could not be found! Exiting..");
      System.exit(0);
    }
  }

  /**
   * Uses HashTableMap's put method to add username and password
   * 
   * @param username- user
   * @param password- pass
   *
   */
  public void addUser(String username, String password) {
    PassMap.put(username, password);
  }

  /**
   * Uses HashTableMap's get method to get username and password and matches it with the inputed
   * password
   * 
   * @param username- user
   * @param password- pass
   *
   */
  public void login(String username, String password) {
    if (userNameExist(username)) {
      String regPassword = (String) PassMap.get(username);
      if (regPassword.equals(password))
        System.out.println("Authentication Successful\n");
      else
        System.out.println("Authentication Failed\n");
    } else
      System.out.println("Username does not exist!\n");
  }

  /**
   * This method updates a password for a user
   * 
   * @param username- user
   * @param password- pass
   *
   */
  public void updatePassword(String username, String newPassword) {
    if (userNameExist(username)) {
      PassMap.remove(username);
      PassMap.put(username, newPassword);
      System.out.println("Password Updated Successfully\n");
    } else
      System.out.println("Username does not exist!\n");
  }


}
