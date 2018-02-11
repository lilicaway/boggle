import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class BoogleTrieTest {

  @Test
  public void testPutValue() throws Exception {
    String[] dictionary = { "SI" };
    BoogleTrie trie = new BoogleTrie(dictionary);

    String key = "NO";
    trie.put(key);
    assertTrue(trie.get(key));
  }

  @Test
  public void testContainsKey() {
    String[] dictionary = { "SI" };
    BoogleTrie trie = new BoogleTrie(dictionary);

    String key = "SI";
    assertTrue(trie.contains(key));
  }

  @Test
  public void testIterateThroughPrefix() throws Exception {
    String[] dictionary = { "LINE" };
    BoogleTrie trie = new BoogleTrie(dictionary);

    String key = "LI";

    assertTrue(trie.isNextCharPartOfPrefix(key.charAt(0)));
    assertTrue(trie.isNextCharPartOfPrefix(key.charAt(1)));

    trie.goACharacterBack();
    assertTrue(trie.isNextCharPartOfPrefix(key.charAt(1)));
  }

  @Test
  public void testHasPrefix() throws Exception {
    String[] dictionary = { "LINE" };
    BoogleTrie trie = new BoogleTrie(dictionary);

    String key = "LI";

    assertTrue(trie.hasPrefix(key));
    String keyNotPresent = "NO";
    assertFalse(trie.hasPrefix(keyNotPresent));
  }

  @Test
  public void testSuccesfullCreation() throws Exception {
    In in = new In("test_data/dictionary-common.txt");
    String[] dic = in.readAllStrings();
    BoogleTrie trie = new BoogleTrie(dic);

    assertTrue(trie.contains("ABBOT"));
    assertTrue(trie.hasPrefix("AB"));
    assertTrue(trie.contains("BARE"));
    assertTrue(trie.contains("CAT"));
    assertTrue(trie.contains("KING"));
    assertTrue(trie.contains("PROFILE"));

    assertTrue(trie.contains("ZYGOTE"));
    assertTrue(trie.isNextCharPartOfPrefix('Z'));
    assertTrue(trie.isNextCharPartOfPrefix('Y'));
    assertTrue(trie.isNextCharPartOfPrefix('G'));
  }
}
