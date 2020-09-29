// --== CS400 File Header Information ==--
// Name: Barnabas Masil Adrian anak Christopher
// Email: adriananakch@wisc.edu
// Team: GA
// Role: Data Wrangler
// TA: Daniel Kiel
// Lecturer: Gary Dahl
// Notes to Grader:

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 * THis is a tester class that will test the functionality of the passwordManager class
 * 
 * @author arjun
 *
 */
public class TestProjectOne {

  /**
   * THis method tests if the program can create new users properly
   * 
   * @return
   */
  public static boolean createUserTest() {

    // Commands that create a new user and try logging in with it - Continues with
    // standard program flow and quits
    String simulatedUserInput = "b" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "abc" + System.getProperty("line.separator") + "abc123!"
        + System.getProperty("line.separator") + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager();
      pm.userInterface();
    } catch (Exception e) {

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * This method test for invalid inputs
   * 
   * @return false if the test fails
   */
  public static boolean invalidInputTest1() {

    // Commands that initially give an invalid input and check for invalid input
    // prompt - Continues with standard program flow and quits
    String simulatedUserInput =
        "t" + System.getProperty("line.separator") + "a" + System.getProperty("line.separator")
            + "abc" + System.getProperty("line.separator") + "abc123!"
            + System.getProperty("line.separator") + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager();
      pm.userInterface();
    } catch (Exception e) {

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * This method test for invalid inputs
   * 
   * @return false if the test fails
   */
  public static boolean invalidInputTest2() {

    // Commands that initially give an invalid input and check for invalid input
    // prompt - Continues with standard program flow and quits
    String simulatedUserInput = "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "z" + System.getProperty("line.separator") + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager();
      pm.userInterface();
    } catch (Exception e) {

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * This method tests the for an invalid username
   * 
   * @return false if the test fails
   */
  public static boolean invalidUsernameTest() {

    // Commands that initially give an invalid username and check for return to
    // login function - Continues with standard program flow and quits
    String simulatedUserInput = "a" + System.getProperty("line.separator") + "truck"
        + System.getProperty("line.separator") + "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager();
      pm.userInterface();
    } catch (Exception e) {

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * This method tests the for an invalid password
   * 
   * @return false if the test fails
   */
  public static boolean invalidPasswordTest() {

    // Commands that initially give an invalid password and check for return to
    // input password function - Continues with standard program flow and quits
    String simulatedUserInput = "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "x" + System.getProperty("line.separator") + "x"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager();
      pm.userInterface();
    } catch (Exception e) {

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * This method tests the for duplicates in usernames
   * 
   * @return false if the test fails
   */
  public static boolean duplicateUsernameTest() {

    // Commands that create a new user and try logging in with it - Continues with
    // standard program flow and quits
    String simulatedUserInput = "b" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc1234!" + System.getProperty("line.separator")
        + "a" + System.getProperty("line.separator") + "abc" + System.getProperty("line.separator")
        + "abc123!" + System.getProperty("line.separator") + "q"
        + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager();
      pm.userInterface();
    } catch (Exception e) {

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * This method tests the if the addUrl method works
   * 
   * @return false if the test fails
   */
  public static boolean addURLgetURLTest() {

    // Commands that add a new URL-username-password set
    String simulatedUserInput = "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "a" + System.getProperty("line.separator") + "google.com"
        + System.getProperty("line.separator") + "unmgoogle" + System.getProperty("line.separator")
        + "pswdgoogle" + System.getProperty("line.separator") + "q"
        + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager();
      pm.userInterface();
    } catch (Exception e) {

      e.printStackTrace();
      return false;
    }

    /**
     * This method tests the if the getUrl method works
     * 
     * @return false if the test fails
     */
    simulatedUserInput = "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "y" + System.getProperty("line.separator") + "google.com"
        + System.getProperty("line.separator") + "x" + System.getProperty("line.separator") + "q"
        + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager();
      pm.userInterface();
    } catch (Exception e) {

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * This method tests the if the updatePassword method works
   * 
   * @return false if the test fails
   */
  public static boolean updatePasswordTest() {

    // commands for updating password
    String simulatedUserInput = "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "u" + System.getProperty("line.separator") + "abc" + System.getProperty("line.separator")
        + "abc123?" + System.getProperty("line.separator") + "a"
        + System.getProperty("line.separator") + "abc" + System.getProperty("line.separator")
        + "abc123?" + System.getProperty("line.separator") + "q"
        + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager();
      pm.userInterface();
    } catch (Exception e) {

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * This is the main method where all the test methods will run
   * 
   * @param args
   */
  public static void main(String[] args) {

    FileUtility object = new FileUtility(new File("Data.txt"));
    HashTableMap<String, User> users = new HashTableMap<String, User>();
    LinkedList<String> listOfUsernames = new LinkedList<String>();
    object.loadData(users, listOfUsernames);

    ArrayList<Boolean> test = new ArrayList<Boolean>();

    test.add(createUserTest());
    test.add(invalidInputTest1());
    test.add(invalidInputTest2());
    test.add(invalidUsernameTest());
    test.add(invalidPasswordTest());
    test.add(duplicateUsernameTest());
    test.add(addURLgetURLTest());
    test.add(updatePasswordTest());

    object.saveData(users, listOfUsernames);

    System.out.println("Test results:-");
    System.out.println();

    int i = 0;
    System.out.println("createUserTest: " + test.get(i++));
    System.out.println("invalidInputTest1: " + test.get(i++));
    System.out.println("invalidInputTest2: " + test.get(i++));
    System.out.println("invalidUsernameTest: " + test.get(i++));
    System.out.println("invalidPasswordTest: " + test.get(i++));
    System.out.println("duplicateUsernameTest: " + test.get(i++));
    System.out.println("addURLgetURLTest: " + test.get(i++));
    System.out.println("updatePasswordTest: " + test.get(i++));

  }

}
