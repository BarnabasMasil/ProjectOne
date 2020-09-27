// --== CS400 File Header Information ==--
// Name: Tavish Vats
// Email: tvats@wisc.edu
// Team: GA
// TA: Daniel Keil
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;

/**
 * This class implements a hash map table
 * 
 * @author Tavish Vats
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private int capacity; // capacity of the hash map table
  private int size; // size of the hash map table
  private KeyValuePairs<KeyType, ValueType>[] keyValueArray; // key value pairs array

  /**
   * This constructor initializes the size, capactiy (10), and the array which stores the key value
   * pairs
   */
  public HashTableMap() {
    this.capacity = 10;
    this.size = 0;
    this.keyValueArray = new KeyValuePairs[this.capacity];
  }

  /**
   * This constructor initializes the size, capactiy, and the array which stores the key value pairs
   * 
   * @param capacity - capacity of the hash map table
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.keyValueArray = new KeyValuePairs[this.capacity];
  }

  @Override
  /**
   * This method stores a new key value pair in the hash table, it returns true when the pair has
   * been stored. When this method is passed a key already stored in the hash table it returns
   * false.
   * 
   * @param key   - key of data
   * @param value - value of data
   * @return true if the value is stored in hash table, false otherwise
   */
  public boolean put(KeyType key, ValueType value) {
    // find index based on key and capacity, and then store the value and the index in currEntry and
    // the new value in newEntry
    int index = hashFunction(key, this.capacity);
    KeyValuePairs<KeyType, ValueType> currEntry = keyValueArray[index];
    KeyValuePairs<KeyType, ValueType> newEntry = new KeyValuePairs<KeyType, ValueType>(key, value);

    // if the current entry in the hash table at the index is null, then add the newEntry and
    // increment the size
    if (currEntry == null) {
      keyValueArray[index] = newEntry;
      size++;
    } else {
      // if the current entry is not null, check if the key is already present at current entry.
      if (currEntry.key.equals(key)) {
        return false;
      }
      // iterate through the linked list and store the newEntry at the end of the linked list and
      // increment the size.
      while (currEntry.next != null) {
        currEntry = currEntry.next;
        if (currEntry.key.equals(key)) {
          return false;
        }
      }
      currEntry.next = newEntry;
      size++;
    }
    // check if the loadfactor is greater than or equal to 80%, if it is then double and rehash
    if (checkLoadFactor()) {
      doubleAndRehash();
    }
    return true;
  }

  @Override
  /**
   * This method get the value stored based on the key. If the key is not found in the hash table,
   * the method throws a NoSuchElementException.
   * 
   * @param key - key of data
   * @return value - value of the data stored corresponding to key
   * @throws NoSuchElementException if the key is not found in the hash table
   */
  public ValueType get(KeyType key) throws NoSuchElementException {
    // find index based on key and capacity, and then store the key value pair at that index in
    // currEntry
    int index = hashFunction(key, this.capacity);
    KeyValuePairs<KeyType, ValueType> currEntry = keyValueArray[index];
    // if the current entry is null throw NoSuchElementException, otherwise find the value stored in
    // the hash table matching the key
    if (currEntry != null) {
      // if the key is equal to the current key, then return the value matching that key
      if (currEntry.key.equals(key)) {
        return currEntry.value;
      } else {
        // if the key isn't equal to the current key, iterate through the linked list to find the
        // matching key and return the value.
        KeyValuePairs<KeyType, ValueType> nextEntry = keyValueArray[index].next;
        while (nextEntry != null) {
          if (nextEntry.key.equals(key)) {
            return nextEntry.value;
          } else {
            nextEntry = nextEntry.next;
          }
        }
        // if after iterating through the linked list at the index in the array the matching key is
        // not found, then throw a NoSuchElementException
        throw new NoSuchElementException();
      }
    } else {
      throw new NoSuchElementException();
    }
  }

  @Override
  /**
   * This method returns the size of the hash table
   * 
   */
  public int size() {
    return this.size;
  }

  @Override
  /**
   * This method return true if the key value pair is found in the hash table based on the key,
   * false otherwise.
   * 
   * @param key - key of data
   * @return true is key found, false otherwise
   */
  public boolean containsKey(KeyType key) {
    // find index based on key and capacity, and then store the key value pair at that index in
    // currEntry
    int index = hashFunction(key, this.capacity);
    KeyValuePairs<KeyType, ValueType> currEntry = keyValueArray[index];
    // if the currEntry is not null, then return true if the key matches the key of currEntry.
    if (currEntry != null) {
      if (currEntry.key.equals(key)) {
        return true;
      } else {
        // if matching key is not found, then iterate through the linked list to find the matching
        // key and return true when it is found
        KeyValuePairs<KeyType, ValueType> nextEntry = keyValueArray[index].next;
        while (nextEntry != null) {
          if (nextEntry.key.equals(key)) {
            return true;
          } else {
            nextEntry = nextEntry.next;
          }
        }
      }
    }
    return false;
  }

  @Override
  /**
   * This method returns the value of the key value pair that is removed from the hash table based
   * on the key. If the key is not found in the hash table it returns null.
   * 
   * @param key of the key value pair to be removed
   * @return the value removed from the hash table
   */
  public ValueType remove(KeyType key) {
    // if the key is present in the hash table, look for it and remove it, otherwise return null
    if (containsKey(key)) {
      ValueType val = null; // val is the value to be removed
      int index = hashFunction(key, this.capacity); // index at which key to be removed is present
      // if the matching key is found at the index, remove key value pair, decrement size and return
      // the value that is removed from the hash table
      if (keyValueArray[index].key.equals(key)) {
        val = keyValueArray[index].value;
        keyValueArray[index] = keyValueArray[index].next;
        size--;
        return val;
      } else {
        // if the matching key is not found at index, iterate through linked list to find the
        // matching key and then when found, remove key value pair, decrement size and return the
        // value that is removed from the hash table
        KeyValuePairs<KeyType, ValueType> currEntry = keyValueArray[index];
        KeyValuePairs<KeyType, ValueType> prevEntry = null;
        while (currEntry != null) {
          if (currEntry.key.equals(key)) {
            val = currEntry.value;
            prevEntry.next = currEntry.next;
            size--;
            break;
          }
          prevEntry = currEntry;
          currEntry = currEntry.next;
        }
        return val;
      }
    } else {
      return null;
    }
  }

  @Override
  /**
   * This method clears the hash table and sets the size to 0.
   * 
   */
  public void clear() {
    keyValueArray = new KeyValuePairs[this.capacity];
    size = 0;
  }

  /**
   * This method returns the index in the array where the key value pairs are stored.
   * 
   * @param key  - key of data
   * @param size - size of the hash table
   * @return index where the key value pairs are stored in the hash map
   */
  private int hashFunction(KeyType key, int size) {
    // returns the index which is the absolute value of the key's hashCode() modulus the size
    int hash = Math.abs(key.hashCode()) % size;
    return hash;
  }

  /**
   * This method check if the load factor becomes greater than or equal to 80%
   * 
   * @return true is greater than or equal to 80%, false otherwise
   */
  private boolean checkLoadFactor() {
    // calculate the load factor, which is size / capacity, and checks if the load factor is greater
    // than or equal to 80 percent.
    double loadFactor = ((double) this.size) / this.capacity;
    return (loadFactor >= 0.8);
  }

  /**
   * This method is used to double the capacity of the hash map and rehash and store the key value
   * pairs in the new array with double capacity.
   * 
   */
  private void doubleAndRehash() {
    // the capacity is doubled and stored in newSize
    // a new key value pairs array newHashTable is initilaized with newSize as its capacity
    int newSize = this.capacity * 2;
    KeyValuePairs<KeyType, ValueType>[] newHashTable = new KeyValuePairs[newSize];
    // iterate through the hashTable, calculate the index based on each entry key and newSize of the
    // hash table and store the entries in the newHashTable at the new index.
    for (KeyValuePairs<KeyType, ValueType> entry : this.keyValueArray) {
      while (entry != null) {
        int index = hashFunction(entry.key, newSize);
        if (newHashTable[index] == null) {
          newHashTable[index] = new KeyValuePairs<KeyType, ValueType>(entry.key, entry.value);
        } else {
          KeyValuePairs<KeyType, ValueType> currEntry = newHashTable[index];
          while (currEntry.next != null) {
            currEntry = currEntry.next;
          }
          currEntry.next = new KeyValuePairs<KeyType, ValueType>(entry.key, entry.value);
        }
        entry = entry.next;
      }
    }
    // update the capacity and key value pair array
    this.keyValueArray = newHashTable;
    this.capacity = newSize;
  }
}
