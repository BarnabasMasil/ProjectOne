// --== CS400 File Header Information ==--
// Name: Tavish Vats
// Email: tvats@wisc.edu
// Team: GA
// TA: Daniel Keil
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class implements a linked list which store the key and value pair to be entered in the hash
 * map table
 * 
 * @author Tavish
 */
public class KeyValuePairs<KeyType, ValueType> {
  KeyType key;
  ValueType value;
  KeyValuePairs<KeyType, ValueType> next;

  public KeyValuePairs(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
    this.next = null;
  }
}
