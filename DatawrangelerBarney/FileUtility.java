import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is used to load and save data from a file to a hashTableMap and its contents
 * 
 * @author barna
 *
 */
public class FileUtility {

  private File file;
  private Scanner scan = null;
  private boolean isEmpty = true;

  //The constructor takes a file as its parameter
  public FileUtility(File file) {
    this.file = file;
    Scanner temp;
    try {
      temp = new Scanner(file);
      isEmpty = !temp.hasNext();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  /**
  * This method loads the data into a hashTableMap from a text file
  * 
  */
  public void loadData(HashTableMap<String, User> users) {
    String loginUsername = null;
    String loginPassword = null;
    String[] urls = null;
    String[] usernames = null;
    String[] passwords = null;

    try {
      scan = new Scanner(file);

      while (scan.hasNext()) {
        String line = scan.nextLine();
        String info;
        if (line.equals("") || line.equals("\n")) {
          continue;
        }
        
        info = line;
        line = line.substring(0, line.indexOf(":"));
        
        if(line.equals("LoginUser")) {
          loginUsername = info.substring(info.indexOf(":")+1).trim();
        }
        
        if(line.equals("LoginPassword")) {
          loginPassword = info.substring(info.indexOf(":")+1).trim();
        }

        if (line.equals("Urls")) {
          urls = info.substring(info.indexOf(":")+1).trim().split("\\s");
        }
        
        if(line.equals("Usernames")) {
          usernames = info.substring(info.indexOf(":")+1).trim().split("\\s");
        }
        
        if(line.equals("Passwords")) {
          
          info = info.substring(info.indexOf(":")+1).trim();
          //System.out.println(info);
          passwords = new String[urls.length];
            
          String temp = "";
          boolean onPassword = false;
          int counter = 0;
          
          for(int i = 0; i < info.length();i++) {
            
            if(info.charAt(i) == '\"') {
              if(onPassword == false) {
                onPassword = true;
              }else {
                onPassword = false;
                passwords[counter] = temp.substring(1);
                counter++;
                temp = "";
              }  
            }
            
            if(onPassword == true) {
              temp += info.charAt(i);
            }
          }
        }
        
        //Add data to HashTableMap
        if(line.contains("stop")) {
          //System.out.println(loginUsername);
          users.put(loginUsername, new User(loginUsername, loginPassword));
          
          for(int i = 0; i < urls.length; i++) {
            users.get(loginUsername).addCredential(urls[i], usernames[i], passwords[i]);
          }
        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (scan != null)
        scan.close();
    }
  }
  
  public void saveData(HashTableMap<String, User> users) {
    
  }


}
