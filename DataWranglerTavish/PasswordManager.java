import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the main class where Userinterface occurs and where altertion or changes are stored
 * or loaded
 * 
 * @author barna, aneesh
 *
 */
public class PasswordManager {

  private FileDataHandler utility;
  private Scanner scan;
  private HashTableMap<String, Users> users;
  private ArrayList<String> listOfUsernames;
  private boolean isRunning = true;

  public PasswordManager() {
    utility = new FileDataHandler(new File("database.txt"));
    users = new HashTableMap<>();
    listOfUsernames = new ArrayList<>();
    utility.loadData(users, listOfUsernames);
    scan = new Scanner(System.in);
  }

  /**
   * This method is the userInterface where the user can interact with the program
   * 
   * @author barna, aneesh
   */
  public void userInterface() {
    String tempName, tempPass, tempUrl;
    Users tempUser = null;
    boolean loginSuccess = false;
    System.out.println("**********************************************************************");
    System.out.println("");
    System.out.println("*************   WELCOME TO PASSWORD MANAGER APPLICATION   ************");
    System.out.println("");
    System.out.println("**********************************************************************");
    System.out.println("");

    // Login interface to login into the password manager
    while (isRunning) {

      System.out.println(
          "If you're an existing member, press 'a'. If you're new, press 'b' to register: ");
      boolean loginOrRegister = true;

      while (loginOrRegister) {

        String tempResponse = scan.nextLine();

        if (tempResponse.equals("b")) {
          addNewUserHelper();
          loginOrRegister = false;
          break;
        } else if (!tempResponse.equals("a") && !tempResponse.equals("b")) {
          System.out.println("Please enter a valid input: ");
        } else {
          // loginOrRegister = false;
          break;
        }
      }
      System.out.print("Enter your username: ");
      tempName = scan.nextLine();

      if (users.containsKey(tempName)) {
        tempUser = (Users) users.get(tempName);

        boolean incorrectPass = true;

        int count = 0;

        while (incorrectPass) {
          System.out.print("Enter password: ");
          tempPass = scan.nextLine();
          if (tempUser.getLoginPassword().equals(tempPass)) {
            loginSuccess = true;
            incorrectPass = false;
          } else {
            count++;
            if (count == 3) {
              System.out
                  .println("Too many incorrect attempts. Please wait for a few moments to retry.");
              try {
                Thread.sleep(60000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              count = 0;
            }
            System.out.println("Incorrect password");
            System.out.print("Enter any key to retry or 'q' to exit: ");
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

        System.out.println("Authentication Successful. Logging you in...");

        while (isRunning) {
          System.out.println("");
          System.out
              .println("**********************************************************************");
          System.out.println("Welcome Back " + tempName + "!");
          System.out.println("");
          System.out.println("Which of the following would you like to perform? ");
          System.out.println("Search a new URL: press 'y'");
          System.out.println("Add a new URL: press 'a'");
          System.out.println("Update account password: press 'u'");
          System.out.println("Change user: press 'c'");
          System.out.println("Add new user: press 'b'");
          System.out.println("Quit the app: press 'q'");

          // System.out.print(
          // "Press y(search new Url), q(exit app), c(change user), a(add new url), b(add
          // new user), u(update password)");

          String input = scan.nextLine().toLowerCase();

          if (input.equals("y")) {

            boolean retry = true;

            while (retry) {

              System.out.println("What URL's credentials would you like to retrieve? ");
              tempUrl = scan.nextLine().trim();
              if (tempUser.getDetails().containsKey(tempUrl)) {
                tempName = tempUser.getDetails().get(tempUrl).getUsername();
                tempPass = tempUser.getDetails().get(tempUrl).getPassword();

                System.out.println("");
                System.out.println("Retrieving username and password...");
                System.out.println("");
                System.out.println("Username for " + tempUrl + ": " + tempName);
                System.out.println("Password for " + tempUrl + ": " + tempPass + "\n");
                System.out.println("");
                System.out.println("Returning to main menu...");
                try {
                  Thread.sleep(3000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                retry = false;

              }

              else {
                System.out.println("Account does not exist for this URL. Please try again");
              }
            }

          } else if (input.equals("q")) {

            this.isRunning = false;
            isRunning = false;
            System.out
                .println("**********************************************************************");
            System.out.println("");
            System.out
                .println("*************    Thank you for using Password Manager    ************");
            System.out.println("");
            System.out
                .println("**********************************************************************");
            System.out.println("");
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
            System.out.println(
                "Enter password of at least 6 characters including letters, numbers and ! or ?: ");
            String userPass = scan.nextLine().trim();
            updatePassword(userName, userPass);
            break;
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

    users.put(username, new Users(username, password));
    listOfUsernames.add(username);

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
   * This method adds a chosen Url, username and a password and associate it with a User object
   * 
   * @param loginUsername The String of the initial login username to associate the url, username,
   *                      and password to
   * @param url           The String that contains a chosen url
   * @param username      The String that contains a chosen username
   * @param password      The String that contains a chosen password
   */
  public void addNewCredential(String loginUsername, String url, String username, String password) {
    ((Users) users.get(loginUsername)).addDetails(url, username, password);

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
    while (!validatePassword(newPassword)) {
      System.out.println("Password not Secure\n");
      System.out
          .print("Enter password of at least 6 characters including letters, numbers and ! or ?: ");
      newPassword = scan.nextLine().trim();
    }
    Users tempUser = null;

    if (users.containsKey(username)) {
      tempUser = (Users) users.get(username);
    }
    tempUser.setLoginPassword(newPassword);

    System.out.println("Password Updated Successfully\n");
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
