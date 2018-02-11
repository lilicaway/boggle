import java.util.ArrayDeque;
import java.util.Deque;

public class BoogleTrie {

  private static final int R = 'Z' - 'A' + 1;
  private Node root;
  private Deque<Node> stack;

  private static class Node {
    private boolean isString;
    private Node[] next = new Node[R];
  }

  public BoogleTrie(String[] dictionary) {
    root = new Node();
    stack = new ArrayDeque<>();
    stack.addFirst(root);
    for (String word : dictionary) {
      this.put(word);
    }
  }

  public void put(String key) {
    root = put(root, key, 0);
  }

  private Node put(Node node, String key, int index) {
    if (node == null) {
      node = new Node();
    }
    if (index == key.length()) {
      node.isString = true;
      return node;
    }
    char c = key.charAt(index);
    node.next[c - 'A'] = put(node.next[c - 'A'], key, index + 1);
    return node;
  }

  public boolean contains(String key) {
    return get(key);
  }

  public boolean get(String key) {
    Node node = root;
    int i = 0;
    while (node != null && i < key.length()) {
      node = node.next[key.charAt(i) - 'A'];
      i++;
    }
    if (node == null) {
      return false;
    }
    return node.isString;
  }


  public boolean hasPrefix(String prefix) {
    int i = 0;
    Node node = root;
    while (i < prefix.length() && node != null) {
      node = node.next[prefix.charAt(i) - 'A'];
      i++;
    }
    return node != null;
  }

  public void resetCharPrefix() {
    stack = new ArrayDeque<>();
    stack.clear();
    stack.addFirst(root);
  }

  public boolean isNextCharPartOfPrefix(char c) {
    Node node = stack.peek();
    Node currentNode = node.next[c - 'A'];
    if (currentNode != null) {
      stack.addFirst(currentNode);
      return true;
    }
    return false;
  }
  
  public void goACharacterBack() {
    Node node = stack.peek();
    if (node != root) {
      stack.removeFirst();
    }
  }


}
