// --== CS400 File Header Information ==--
// Name: Jacob Petrie
// Email: jcpetrie@wisc.edu
// Team: GA
// TA: Daniel Kiel
// Lecturer: Prof. Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.Scanner;


/**
 * Interactive tests for various aspects of CS 400 P01 Team GA
 *  made for compatability with barna code
 *  
 *  YOU WILL NEED TO CHANGE validatePassword TO PUBLIC TO RUN
 * 
 * @author petri
 *
 */
public class PWMTestSuite {
  
  private static String menu = "What would you like to do?\n" 
      + "     Test PasswordManager Class: Press \"p\"\n"
      + "     Test User Class: Press \"u\"\n" 
      + "     Test Data Class: Press \"d\"\n"
      + "     Quit: Press \"q\"\n" 
      + "     Test Everything: Press Any other key";
  
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    boolean isTesting = true;
    System.out.println("************************************************************");
    System.out.println("");
    System.out.println("************ Welcome to Password Manager tester ************");
    System.out.println("");
    System.out.println("************************************************************");
    System.out.println("");
    
    while (isTesting) {
      
      System.out.println(menu);
      
      String testChoice = scnr.nextLine().toLowerCase();
      
      if (testChoice.equals("q")) {
        isTesting = false;
        break;
      }
      
      switch (testChoice) {
        case "p":
          testPasswordManager();
          break;
          
        case "u":
          testUser();
          break;
        
        case "d":
          testData();
          break;
          
        default:
          testUser();
          testData();
          testPasswordManager();
          break;

      }

    }

  }



  /**
   * tester for the User Class and its setCredentials, toString, and get methods
   * 
   * @return true if User Class passes all created tests, false otherwise
   */
  public static boolean testUser() {
    System.out.println("Begin Testing User Class");
    boolean passTest = true;
    boolean isTesting = true;
    Scanner scnr = new Scanner(System.in);

    while (isTesting) {
      passTest = true;
      String username = "User1";
      String password = "password1";
      String url = "website.com";
      String websiteUsername = "websiteUsername";
      String websitePassword = "websitePassword";

      System.out.println("Press \"c\" for custom test input any other key for standard tests");
      String testChoice = scnr.nextLine();

      if (testChoice.equals("c")) {
        System.out.println("Username to test: ");
        username = scnr.nextLine();
        System.out.println("Password to test: ");
        password = scnr.nextLine();
      }

      // create instance of User
      User user1 = new User(username, password);


      System.out.println("     Begining testUsername and testPassword");
      // test getLoginUsername
      if (!user1.getLoginUsername().equals(username)) {
        passTest = false;
        System.out.println("getUsername returned: " + user1.getLoginUsername()
            + ", for user with username: " + username);
      }

      // test getLoginPassword
      if (!user1.getLoginPassword().equals(password)) {
        passTest = false;
        System.out.println("getPassword returned: " + user1.getLoginPassword()
            + ", for user with password: " + password);
      }
      System.out.println("     Ending testUsername and testPassword");

      // test setLoginUsername and setLoginPassword
      System.out.println("     Begining to test changing username and password");

      if (testChoice.equals("c")) {
        System.out.println("New username to test: ");
        username = scnr.nextLine();
        System.out.println("New password to test: ");
        password = scnr.nextLine();
      }

      username = "newUser1";
      password = "newPassword1";

      user1.setLoginUsername(username);
      user1.setLoginPassword(password);

      if (!user1.getLoginPassword().equals(password)) {
        passTest = false;
        System.out.println("setCredentials changed the password to " + user1.getLoginPassword()
            + " instead of " + password);
      }

      if (!user1.getLoginUsername().equals(username)) {
        passTest = false;
        System.out.println("setCredentials changed the username to " + user1.getLoginUsername()
            + " instead of " + username);
      }

      System.out.println("     Ending test changing username and password");
      System.out.println("     Begining testing website credential adder");

      if (testChoice.equals("c")) {
        System.out.println("Website Url to test: ");
        url = scnr.nextLine();
        System.out.println("Password to test: ");
        websitePassword = scnr.nextLine();
        System.out.println("Username to test: ");
        websiteUsername = scnr.nextLine();
      }

      // test addCredential and print credential
      user1.addCredential(url, websiteUsername, websitePassword);

      if (!user1.getListOfUrls().toString().contains(url)) {
        passTest = false;
        System.out.println("After calling add credential passed with: " + url + ", "
            + websiteUsername + ", " + websitePassword + " getListOfUrls did not contain: " + url);
      }

      System.out.println("     Ending testing website credential adder");

      // post test output
      if (passTest == true) {
        System.out.println("End testing User Class: all tests Passed\n");
      } else {
        System.out.println("End testing User Class: error(s) detected\n");
      }

      System.out.println("Press \"c\" to continue testing, any other key to exit");
      testChoice = scnr.nextLine();
      if (testChoice.equals("c")) {
        isTesting = true;
      } else {
        isTesting = false;
      }

    }
    return passTest;
  }



  /**
   * Performs test on various username and password related methods in PasswordManager
   * 
   * @return
   */
  public static boolean testPasswordManager() {
    System.out.println("Begin Testing PasswordManger Class\n");
    boolean passTest = true;
    boolean isTesting = true;
    Scanner scnr = new Scanner(System.in);
    PasswordManager PWM = new PasswordManager(); // instantiate a PasswordManager
    PWM.addNewUser("testUser", "originalTestPassw0rd!");
    String[] passwords =
      {"", "short", "noNumbers", "noNumbersOrCharacters", "th1sSh0uldW0rk!", "Passw0rds!"};
    boolean[] testPasswords = {false, false, false, false, true, true};
    
    
    
    while (isTesting) {
      
      // test validatePassword
        System.out.println("     Begin Testing validatePassword");
        System.out.println("     Press \"c\" for custom test input any other key for standard tests");
        String testChoice = scnr.nextLine();
        
        if (testChoice.equals("c")) {
          System.out.print("What Password would you like to test?");
          String customPW = scnr.nextLine();
          System.out.println(
              "The password " + customPW + " returned " + PasswordManager.validatePassword(customPW));
        }
  
        System.out.println("          Begin standard validatePassword tests");
        for (int i = 0; i < passwords.length; i++) {
          if (PasswordManager.validatePassword(passwords[i]) != testPasswords[i]) {
            System.out.println("The password " + passwords[i] + " returned " + !testPasswords[i]
                + " under validatePassword, when it should have returned " + testPasswords[i]);
          }
        }
        System.out.println("          End standard validatePassword tests");
        System.out.println("     End Testing validatePassword");
        
        
      /*
        System.out.println("     Begin Testing updatePassword");
        System.out.println("     Press \"c\" for custom test input any other key for standard tests");
        testChoice = scnr.nextLine();
        String customPW = "";
        
        if (testChoice.equals("c")) {
          System.out.print("What Password would you like to update with?");
          customPW = scnr.nextLine();
        }
        
  
        System.out.println("          Begin standard updatePassword tests");
        for (int i = 0; i < passwords.length; i++) {
          if () {
            System.out.println();
          }
        }
        
        System.out.println("          End standard updatePassword tests");
        System.out.println("     End Testing updatePassword");
        */
        
      // test userNameExist
        System.out.println("     Begin Testing userNameExist");
        System.out.println("     Note: currently only a user with the name \"testUser\" exists.");
        System.out.println("     Press \"c\" for custom test input any other key for standard tests");
        testChoice = scnr.nextLine();
        String username = "testUser";
        if (testChoice.equals("c")) {
          System.out.print("What username would you like to look for?");
          username = scnr.nextLine();
          if( !username.equals("testUser") && PWM.userNameExist(username)) {
            System.out.println("usernameExist determined \"" + username + "\" existed when it doesn't");
          }
        }
        
        System.out.println("          Begin standard userNameExists tests");
        if(PWM.userNameExist("abc")) {
          System.out.println("Error: nonexistent username detected");
        }
        
        if(PWM.userNameExist("")) {
          System.out.println("Error: nonexistent username detected");
        }
        if(!PWM.userNameExist("testUser")) {
          System.out.println("Error: testUser was not found");
        }
        System.out.println("          End standard userNameExists tests");
        System.out.println("     End Testing userNameExists");
        
      // test addNewCredential

      if (passTest == true) {
        System.out.println("End testing PasswordManger Class: all tests Passed\n");
      } else {
        System.out.println("End testing PasswordManger: error(s) detected\n");
      }
      
      System.out.println("Press \"c\" to continue testing, any other key to exit");
      testChoice = scnr.nextLine();
      if (testChoice.equals("c")) {
        isTesting = true;
      } else {
        isTesting = false;
      }
      
    }
    return passTest;
  }


  /**
   * Tests the get and set methods for the Data class
   * 
   * @return
   */
  public static boolean testData() {
    System.out.println("Begin Testing Data Class");
    boolean passTest = true;
    boolean isTesting = true;
    Scanner scnr = new Scanner(System.in);

    while (isTesting) {
      String url = "website1.com";
      String websiteUsername = "User1";
      String websitePassword = "password1";

      System.out.println("Press \"c\" for custom test input any other key for standard tests");
      String testChoice = scnr.nextLine();

      if (testChoice.equals("c")) {
        System.out.println("Url to test: ");
        url = scnr.nextLine();
        System.out.println("Username to test: ");
        websiteUsername = scnr.nextLine();
        System.out.println("Password to test: ");
        websitePassword = scnr.nextLine();
      }


      // create instance of data
      Data loginInfo1 = new Data(url, websiteUsername, websitePassword); // normal user


      System.out.println("     Begin testing getUrl, getUsername, and getPassword");
      // test getUrl
      if (!loginInfo1.getUrl().equals(url)) {
        passTest = false;
        System.out.println("After constructing a new instance of Data with url " + url
            + ", getUrl returned " + loginInfo1.getUrl());
      }

      // test getUsername
      if (!loginInfo1.getUsername().equals(websiteUsername)) {
        passTest = false;
        System.out.println("After constructing a new instance of Data with username "
            + websiteUsername + ", getUsername returned " + loginInfo1.getUsername());
      }

      // test getPassword
      if (!loginInfo1.getPassword().equals(websitePassword)) {
        passTest = false;
        System.out.println("After constructing a new instance of Data with password "
            + websitePassword + ", getPassword returned " + loginInfo1.getPassword());
      }

      System.out.println("     End testing getUrl, getUsername, and getPassword");
      System.out.println("     Begin testing setUsername and setPassword");

      websiteUsername = "newUsername";
      websitePassword = "newPassword";

      if (testChoice.equals("c")) {
        System.out.println("Username to test: ");
        websiteUsername = scnr.nextLine();
        System.out.println("Password to test: ");
        websitePassword = scnr.nextLine();
      }
      // test setUsername and setPassword
      loginInfo1.setUsername(websiteUsername);
      loginInfo1.setPassword(websitePassword);

      if (!loginInfo1.getUsername().equals(websiteUsername)) {
        passTest = false;
        System.out.println("After using setUsername, getUsername returned: "
            + loginInfo1.getUsername() + " instead of " + websiteUsername);
      }

      if (!loginInfo1.getPassword().equals(websitePassword)) {
        passTest = false;
        System.out.println("After using setPassword, it returned " + loginInfo1.getPassword()
            + " instead of " + websitePassword);
      }
      System.out.println("     End testing setUsername and setPassword");


      if (passTest == true) {
        System.out.println("End testing Data Class: all tests Passed\n");
      } else {
        System.out.println("End testing Data Class: error(s) detected\n");
      }


      System.out.println("Press \"c\" to continue testing, any other key to exit");
      testChoice = scnr.nextLine();
      if (testChoice.equals("c")) {
        isTesting = true;
      } else {
        isTesting = false;
      }
    }

    return passTest;
  }



}

