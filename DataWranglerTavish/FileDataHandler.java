import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * This class is used to load and save data from file to a hashTableMap and its contents
 * 
 * @author Tavish
 *
 */
public class FileDataHandler {

  private File file;
  private Scanner scanner = null;
  private boolean isEmptyLine = true;

  public FileDataHandler(File file) {
    try {
      file.createNewFile();
      this.file = file;
      if (file.length() == 0) {
        isEmptyLine = true;
      } else {
        isEmptyLine = false;
      }
    } catch (IOException e) {
    }
  }

  /**
   * This method writes the input string to the file
   * 
   * @author Tavish
   * @param input
   */
  private void writeFile(String input) {
    FileWriter writer = null;
    try {
      writer = isEmptyLine ? new FileWriter(file.getPath()) : new FileWriter(file.getPath(), true);
      writer.write(input + "\n");
      isEmptyLine = false;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * This method clears a file
   * 
   * @author Tavish
   */
  private void clearFile() {
    try {
      new FileWriter(file.getPath(), false).close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method loads data from a file into a HashTableMap and an ArrayList with the information of
   * the User object
   * 
   * @author Tavish
   * @param users           The HashTableMap that stores User objects
   * @param listOfUsernames The ArrayList that stores usernames
   */
  public void loadData(HashTableMap<String, Users> users, ArrayList<String> usernameList) {
    String userLogin = null;
    String userPassword = null;
    String[] urls = null;
    String[] username = null;
    String[] password = null;

    try {
      scanner = new Scanner(file);
      String line = null;
      while (scanner.hasNext()) {
        line = scanner.nextLine();
        if (line.equals("\n") || line.equals("")) {
          continue;
        }
        String details = line.substring(0, line.indexOf(":")).trim();
        switch (details) {
          case "LoginUser":
            userLogin = line.substring(line.indexOf(" ") + 1).trim();
            break;
          case "LoginPassword":
            userPassword = line.substring(line.indexOf(" ") + 1).trim();
            break;
          case "URL":
            if (!line.substring(line.indexOf(":")).equals(":")) {
              String urlString = line.substring(line.indexOf(" "));
              urls = urlString.trim().split("\\s+");
            }
            break;
          case "Usernames":
            if (!line.substring(line.indexOf(":")).equals(":")) {
              String usernameString = line.substring(line.indexOf(" "));
              username = usernameString.trim().split("\\s+");
            }
            break;
          case "Passwords":
            if (!line.substring(line.indexOf(":")).equals(":")) {
              String passwordString = line.substring(line.indexOf(" "));
              String[] temp = passwordString.trim().split("\"");
              password = new String[temp.length / 2];
              int index = 0;
              for (int i = 0; i < temp.length; i++) {
                temp[i] = temp[i].trim();
                if (temp[i].equals("")) {
                  continue;
                } else {
                  password[index] = temp[i];
                  index++;
                }
              }
            }
            break;
          case "stop":
            users.put(userLogin, new Users(userLogin, userPassword));
            usernameList.add(userLogin);
            if (urls != null) {
              for (int i = 0; i < username.length; i++) {
                users.get(userLogin).addDetails(urls[i], username[i], password[i]);
              }
            }
            userLogin = null;
            userPassword = null;
            urls = null;
            username = null;
            password = null;
            break;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }

  /**
   * This functions saves the changes that occured in the HashTableMap of Users and ArrayList of
   * usernames into a file
   * 
   * @author Tavish
   * @param users           The HashTableMap of Users
   * @param listOfUsernames LinkedList of usernames
   */
  public void saveData(HashTableMap<String, Users> users, ArrayList<String> usernameList) {
    clearFile();
    for (String username : usernameList) {
      Users userDetails = users.get(username);
      writeFile("LoginUser: " + userDetails.getLoginUsername());
      writeFile("LoginPassword: " + userDetails.getLoginPassword());
      String urls = "";
      String usernames = "";
      String passwords = "";
      for (String url : userDetails.getUrlList()) {
        urls += " " + url.trim();
        usernames += " " + userDetails.getDetails().get(url).getUsername().trim();
        passwords += " \"" + userDetails.getDetails().get(url).getPassword().trim() + "\"";
      }
      writeFile("URL:" + urls);
      writeFile("Usernames:" + usernames);
      writeFile("Passwords:" + passwords);
      writeFile("stop:" + "\n");
    }
    writeFile("end:");
  }
}
