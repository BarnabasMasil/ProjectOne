
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class is the main class where Userinterface occurs and where altertion or changes are stored
 * or loaded
 * 
 * @author barna
 *
 */
public class PasswordManager {

  private FileUtility utility;
  private Scanner scan;
  private HashTableMap<String, User> users;
  private LinkedList<String> listOfUsernames;
  private boolean isRunning = true;

  public PasswordManager() {
    utility = new FileUtility(new File("Data.txt"));
    users = new HashTableMap<>();
    listOfUsernames = new LinkedList<>();
    utility.loadData(users, listOfUsernames);
    scan = new Scanner(System.in);
  }


  /**
   * This method is the userInterface where the user can interact with the program
   * 
   * @author barna
   */
  public void userInterface() {
    String tempName, tempPass, tempUrl;
    User tempUser = null;
    Data tempData = null;
    boolean loginSuccess = false;

    // Login interface to login into the password manager
    while (isRunning) {
      System.out.print("Enter your username:");
      tempName = scan.nextLine();

      if (users.containsKey(tempName)) {
        tempUser = (User) users.get(tempName);

        boolean incorrectPass = true;

        while (incorrectPass) {
          System.out.print("Enter password:");
          tempPass = scan.nextLine();
          if (tempUser.getLoginPassword().equals(tempPass)) {
            loginSuccess = true;
            incorrectPass = false;
          } else {
            System.out.println("Incorrect password");
            System.out.print("Enter any key to retry or 'q' to exit:");
            String input = scan.nextLine();

            if (input.trim().equals("quit")) {
              isRunning = false;
              break;
            }
          }
        }

      } else {
        System.out.println("No such username exists");
        continue;
      }

      // User Interface where user is in the actual password manager
      if (loginSuccess == true) {

        boolean isRunning = true;

        System.out.println("Login successful");

        while (isRunning) {
          System.out.println("Enter a Url(website) to retrieve password and username");
          tempUrl = scan.nextLine().trim();

          if (tempUser.getCredentials().containsKey(tempUrl)) {
            tempData = (Data) tempUser.getCredentials().get(tempUrl);
            tempName = tempData.getUsername();
            tempPass = tempData.getPassword();

            System.out.println("Obtaining username/password for " + tempUrl);
            System.out.println("Username: " + tempName);
            System.out.println("Password: " + tempPass + "\n");

            System.out.print(
                "Press y(search new Url), q(exit app), c(change user), a(add new url), b(add new user), u(update password)");
            String input = scan.nextLine().toLowerCase();

            if (input.equals("y")) {
              continue;
            } else if (input.contentEquals("q")) {
              this.isRunning = false;
              isRunning = false;
              break;
            } else if (input.equals("c")) {
              isRunning = false;
              break;
            } else if (input.equals("a")) {

              addNewURLHelper();
              break;
            } else if (input.equals("b")) {

              addNewUserHelper();
              break;
            } else if (input.equals("u")) {
              System.out.println("To update password please enter your username.");
              String userName = scan.nextLine().trim();
              System.out.println("Enter new password.");
              String userPass = scan.nextLine().trim();
              updatePassword(userName, userPass);
              break;
            }
          } else {
            System.out.println("Url does not exist");
          }
        }
      }
    }
    System.out.println("Exiting and saving application");
    utility.saveData(users, listOfUsernames);// Dont forget this as this will save the changes
  }

  /**
   * This method adds new user with a new username and password String into the users hashTable.
   * duplicates of usernames are not allowed
   * 
   * @param username The String that contains a chosen username
   * @param password The String that contains a chosen password
   */
  public void addNewUser(String username, String password) {
    for (int i = 0; i < listOfUsernames.size(); i++) {
      if (username.equals(listOfUsernames.get(i))) {
        System.out.println("Username already taken");
        return;
      }
    }

    users.put(username, new User(username, password));
    listOfUsernames.add(username);


  }

  /**
   * This method adds a chosen Url, username and a password and associate it with a User object
   * 
   * @param loginUsername The String of the initial login username to associate the url, username,
   *                      and password to
   * @param url           The String that contains a chosen url
   * @param username      The String that contains a chosen username
   * @param password      The String that contains a chosen password
   */
  public void addNewCredential(String loginUsername, String url, String username, String password) {
    ((User) users.get(loginUsername)).addCredential(url, username, password);
    ((User) users.get(loginUsername)).getListOfUrls().add(url);// Debugging case with duplicates
  }

  /**
   * This helper method aids in adding a new user with a strong password
   * 
   * @author Jeff
   */
  public void addNewUserHelper() {
    System.out.println("Enter new username");
    String user = scan.nextLine().trim();

    System.out
        .print("Enter password of at least 6 characters including letters, numbers and ! or ?: ");
    String pass = scan.nextLine().trim();
    while (!validatePassword(pass)) {
      System.out.println("Password not Secure\n");
      System.out
          .print("Enter password of at least 6 characters including letters, numbers and ! or ?: ");
      pass = scan.nextLine().trim();
    }
    addNewUser(user, pass);
    System.out.println("Added new user");

  }

  /**
   * This helper method aids in adding a new url with a username password combo
   * 
   * @author Jeff
   */
  public void addNewURLHelper() {
    String usernameInp;
    String urlInp;
    String urlPass;
    String urlUser;

    System.out.print("Enter the username");
    usernameInp = scan.nextLine().trim();

    while (!users.containsKey(usernameInp)) {
      System.out.println("Username doesn't exist\n");
      System.out.print("Enter the username");
      usernameInp = scan.nextLine().trim();
    }

    System.out.println("Enter new URL ");

    urlInp = scan.nextLine().trim();

    System.out.println("add username for url");
    urlUser = scan.nextLine().trim();

    System.out.println("add password for url");
    urlPass = scan.nextLine().trim();

    System.out.println("URL with User and Pass added.");

    addNewCredential(usernameInp, urlInp, urlUser, urlPass);
    this.isRunning = false;
    isRunning = false;


  }

  /**
   * This method checks if a username exists
   * 
   * @author Jeff
   * @return true if it exists
   */
  public boolean userNameExist(String username) {
    return (users.containsKey(username));
  }

  /**
   * This method updates a existing user's password with a new password
   * 
   * @author Jeff
   * @param username-    existing username
   * @param newPassword- new password being added
   */
  public void updatePassword(String username, String newPassword) {
    if (userNameExist(username)) {
      users.remove(username);
      listOfUsernames.remove(username);
      users.put(username, new User(username, newPassword));
      listOfUsernames.add(username);

      System.out.println("Password Updated Successfully\n");
    } else
      System.out.println("Username does not exist!\n");
  }

  /**
   * This helper method checks to see if password matches the criteria of having length 6 with
   * numbers and letters and either a ! or ? symbol.
   * 
   * @author Jeff
   * @param pass - password input
   */
  private static boolean validatePassword(String pass) {
    boolean checkLength = false;
    if (pass.length() >= 6)
      checkLength = true;

    boolean containLetters = false;
    for (int i = 0; i < pass.length(); i++) {
      if (Character.isLetter(pass.charAt(i))) {
        containLetters = true;
        break;
      }
    }

    boolean containDigits = false;
    for (int i = 0; i < pass.length(); i++) {
      if (Character.isDigit(pass.charAt(i))) {
        containDigits = true;
        break;
      }
    }

    boolean containSymbol = false;
    for (int i = 0; i < pass.length(); i++) {
      if (pass.charAt(i) == '!' || pass.charAt(i) == '?') {
        containSymbol = true;
        break;
      }
    }
    return (checkLength && containLetters && containDigits && containSymbol);
  }

  /**
   * Main method that runs the program.
   * 
   */
  public static void main(String[] args) {
    PasswordManager pm = new PasswordManager();
    pm.userInterface();
  }

}