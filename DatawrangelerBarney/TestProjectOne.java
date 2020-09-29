// --== CS400 File Header Information ==--
// Name: Barnabas Masil Adrian anak Christopher
// Email: adriananakch@wisc.edu
// Team: GA
// Role: Data Wrangler
// TA: Daniel Kiel
// Lecturer: Gary Dahl
// Notes to Grader: Slight changes made to arjun's code
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Tests the PasswordManager and FileUtility classes
 * 
 * @author arjun
 *
 */
public class TestProjectOne {

  static File testFile = new File("TestFile.txt");;

  /**
   * Runs multiple tests on the FileUtility class
   * 
   * @return
   */
  public static boolean fileUtilityTest() {

    System.out.println();
    System.out.println();
    System.out.println("fileUtilityTest: ");
    System.out.println();
    System.out.println();

    FileUtility f;
    File test = new File("test.txt");


    try {
      // passes null File object
      f = new FileUtility(null);

      // passes filename that does not exist
      f = new FileUtility(test);

      // creating and passing HashTableMap object and LinkedList object to loadData()
      // method to convert data in test.txt to a hashTableMap assuming an empty file
      // with the same name has been created
      HashTableMap<String, User> users = new HashTableMap<String, User>();
      LinkedList<String> listOfUsernames = new LinkedList<String>();
      f.loadData(users, listOfUsernames);


      // if test.txt is created and loadData() works without errors, an empty
      // HashTableMap with an empty listofUsernames should have been initialized
      if (users.size() != 0) {
        return false;
      }
      if (listOfUsernames.size() != 0) {
        return false;
      }

      // testing saveData() method

      // adding a User to the HashTableMap and listOfUsernames
      users.put("testusername", new User("testusername", "testpassword"));
      listOfUsernames.add("testusername");

      // using saveData() method
      f.saveData(users, listOfUsernames);

      // scanning test.txt
      Scanner scan = new Scanner(test);
      String temp = scan.nextLine();
      scan.close();

      // checking presence of data
      if (!temp.equals("LoginUser:testusername")) {

        System.out.println("Oi");
        FileUtility.EmptyFile(test);
        return false;
      }

      // deleting test.txt
      new File("test.txt").delete();

    } catch (Exception e) {
      e.printStackTrace();
      FileUtility.EmptyFile(test);
      return false;
    }

    FileUtility.EmptyFile(test);
    return true;
  }

  /**
   * Tests the create new user functionality
   * 
   * @return boolean - determines the success of the test
   */
  public static boolean createUserTest() {

    System.out.println();
    System.out.println();
    System.out.println("createUserTest: ");
    System.out.println();
    System.out.println();

    // Commands that create a new user and try logging in with it - Continues with
    // standard program flow and quits
    String simulatedUserInput = "b" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "abc" + System.getProperty("line.separator") + "abc123!"
        + System.getProperty("line.separator") + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager(testFile);
      pm.userInterface();
    } catch (Exception e) { // fails the test if an Exception is caught

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * Tests the first input prompt of the User Interface with invalid input
   * 
   * @return boolean - determines the success of the test
   */
  public static boolean invalidInputTest1() {

    System.out.println();
    System.out.println();
    System.out.println("invalidInputTest1: ");
    System.out.println();
    System.out.println();

    // Commands that initially give an invalid input and check for invalid input
    // prompt - Continues with standard program flow and quits
    String simulatedUserInput =
        "t" + System.getProperty("line.separator") + "a" + System.getProperty("line.separator")
            + "abc" + System.getProperty("line.separator") + "abc123!"
            + System.getProperty("line.separator") + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager(testFile);
      pm.userInterface();
    } catch (Exception e) {// fails the test if an Exception is caught

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * Tests user menu with invalid input
   * 
   * @return boolean - determines the success of the test
   */
  public static boolean invalidInputTest2() {

    System.out.println();
    System.out.println();
    System.out.println("invalidInputTest2: ");
    System.out.println();
    System.out.println();

    // Commands that initially give an invalid input and check for invalid input
    // prompt - Continues with standard program flow and quits
    String simulatedUserInput = "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "z" + System.getProperty("line.separator") + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager(testFile);
      pm.userInterface();
    } catch (Exception e) {// fails the test if an Exception is caught

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * Tests username prompt with invalid username
   * 
   * @return boolean - determines the success of the test
   */
  public static boolean invalidUsernameTest() {

    System.out.println();
    System.out.println();
    System.out.println("invalidUsernameTest: ");
    System.out.println();
    System.out.println();

    // Commands that initially give an invalid username and check for return to
    // login function - Continues with standard program flow and quits
    String simulatedUserInput = "a" + System.getProperty("line.separator") + "truck"
        + System.getProperty("line.separator") + "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager(testFile);
      pm.userInterface();
    } catch (Exception e) {// fails the test if an Exception is caught

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * Tests enter password prompt with invalid input
   * 
   * @return boolean - determines the success of the test
   */
  public static boolean invalidPasswordTest() {

    System.out.println();
    System.out.println();
    System.out.println("invalidPasswordTest: ");
    System.out.println();
    System.out.println();

    // Commands that initially give an invalid password and check for return to
    // input password function - Continues with standard program flow and quits
    String simulatedUserInput = "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "x" + System.getProperty("line.separator") + "x"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager(testFile);
      pm.userInterface();
    } catch (Exception e) {// fails the test if an Exception is caught

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * Tests creating a new user with a pre-existing used username
   * 
   * @return boolean - determines the success of the test
   */
  public static boolean duplicateUsernameTest() {

    System.out.println();
    System.out.println();
    System.out.println("duplicateUsernameTest: ");
    System.out.println();
    System.out.println();

    // Commands that try to create a duplicate user at login screen - Continues with
    // standard program flow and quits
    String simulatedUserInput =
        "b" + System.getProperty("line.separator") + "abc" + System.getProperty("line.separator")
            + "xyz" + System.getProperty("line.separator") + "xyz123!"
            + System.getProperty("line.separator") + "a" + System.getProperty("line.separator")
            + "abc" + System.getProperty("line.separator") + "abc123!"
            + System.getProperty("line.separator") + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager(testFile);
      pm.userInterface();
    } catch (Exception e) {// fails the test if an Exception is caught

      e.printStackTrace();
      return false;
    }
    return true;
  }


  /**
   * Tests the add URL and get URL functionality
   * 
   * @return boolean - determines the success of the test
   */
  public static boolean addURLgetURLTest() {

    System.out.println();
    System.out.println();
    System.out.println("addURLgetURLTest: ");
    System.out.println();
    System.out.println();

    // Commands that add a new URL-username-password set
    String simulatedUserInput = "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "a" + System.getProperty("line.separator") + "google.com"
        + System.getProperty("line.separator") + "unmgoogle" + System.getProperty("line.separator")
        + "pswdgoogle" + System.getProperty("line.separator") + "q"
        + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager(testFile);
      pm.userInterface();
    } catch (Exception e) {// fails the test if an Exception is caught

      e.printStackTrace();
      return false;
    }

    // Commands that retrieve added URL-username-password set
    simulatedUserInput = "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "y" + System.getProperty("line.separator") + "google.com"
        + System.getProperty("line.separator") + "x" + System.getProperty("line.separator") + "q"
        + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager(testFile);
      pm.userInterface();
    } catch (Exception e) {// fails the test if an Exception is caught

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * Tests the update password functionality
   * 
   * @return boolean - determines the success of the test
   */
  public static boolean updatePasswordTest() {

    System.out.println();
    System.out.println();
    System.out.println("updatePasswordTest: ");
    System.out.println();
    System.out.println();

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
      PasswordManager pm = new PasswordManager(testFile);
      pm.userInterface();
    } catch (Exception e) {// fails the test if an Exception is caught

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * Tests the secure password functionality within update password functionality
   * 
   * @return
   */
  public static boolean unsecurePasswordTest() {

    System.out.println();
    System.out.println();
    System.out.println("unsecurePasswordTest: ");
    System.out.println();
    System.out.println();

    // commands for updating password with an insecure password
    String simulatedUserInput = "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123?" + System.getProperty("line.separator")
        + "u" + System.getProperty("line.separator") + "abc" + System.getProperty("line.separator")
        + "abc12" + System.getProperty("line.separator") + "abc123!"
        + System.getProperty("line.separator") + "a" + System.getProperty("line.separator") + "abc"
        + System.getProperty("line.separator") + "abc123!" + System.getProperty("line.separator")
        + "q" + System.getProperty("line.separator");

    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    try {
      PasswordManager pm = new PasswordManager(testFile);
      pm.userInterface();
    } catch (Exception e) {// fails the test if an Exception is caught

      e.printStackTrace();
      return false;
    }

    System.out.println();
    return true;
  }

  /**
   * Runs all tests method and utilizes functionality of FileUtility class
   * 
   * @param args
   */
  public static void main(String[] args) {

    ArrayList<Boolean> test = new ArrayList<Boolean>();

    boolean result = fileUtilityTest();

    test.add(createUserTest());
    test.add(invalidInputTest1());
    test.add(invalidInputTest2());
    test.add(invalidUsernameTest());
    test.add(invalidPasswordTest());
    test.add(duplicateUsernameTest());
    test.add(addURLgetURLTest());
    test.add(updatePasswordTest());
    test.add(unsecurePasswordTest());

    System.out.println("Test results:-");
    System.out.println();

    int i = 0;
    System.out.println("fileUtilityTest: " + result);

    System.out.println("createUserTest: " + test.get(i++));
    System.out.println("invalidInputTest1: " + test.get(i++));
    System.out.println("invalidInputTest2: " + test.get(i++));
    System.out.println("invalidUsernameTest: " + test.get(i++));
    System.out.println("invalidPasswordTest: " + test.get(i++));
    System.out.println("duplicateUsernameTest: " + test.get(i++));
    System.out.println("addURLgetURLTest: " + test.get(i++));
    System.out.println("updatePasswordTest: " + test.get(i++));
    System.out.println("unsecurePasswordTest: " + test.get(i++));

    System.out.println("Emptying testFile: " + FileUtility.EmptyFile(testFile));
  }

}
