import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver {

  private final BoogleTrie trie;

  /**
   * Initializes the data structure using the given array of strings as the dictionary. (You can
   * assume each word in the dictionary contains only the uppercase letters A through Z.)
   * 
   * @param dictionary
   */
  public BoggleSolver(String[] dictionary) {
    trie = new BoogleTrie(dictionary);
  }

  // Returns the set of all valid words in the given Boggle board, as an Iterable.
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    Set<String> words = new HashSet<>();
    boolean[][] visited = new boolean[board.rows()][board.cols()];

    // Need to get all possible paths for each word
    for (int i = 0; i < board.rows(); i++) {
      for (int j = 0; j < board.cols(); j++) {
        Deque<BoogleCoordinates> currentWord = new ArrayDeque<>();
        dfsRecursive(board, new BoogleCoordinates(board, i, j), visited, words, currentWord);
      }
    }
    return words;
  }



  private void dfsRecursive(BoggleBoard board, BoogleCoordinates node,
      boolean[][] visited, Set<String> words, Deque<BoogleCoordinates> currentWord) {
    visited[node.getRow()][node.getCol()] = true;

    currentWord.add(node);
    String wordCandidate = toStringWord(currentWord);
    if (wordCandidate.length() > 2 && trie.get(wordCandidate)) {
      words.add(wordCandidate);
    }

    if (trie.hasPrefix(wordCandidate)) {
      Deque<BoogleCoordinates> adj = getNonVisitedAdj(board, visited, node);
      if (!adj.isEmpty()) {
        for (BoogleCoordinates adjNode : adj) {
          dfsRecursive(board, adjNode, visited, words, currentWord);
        }
      }
    }
    visited[node.getRow()][node.getCol()] = false;
    currentWord.removeLast();

  }

  private Deque<BoogleCoordinates> getNonVisitedAdj(BoggleBoard board, boolean[][] visited,
      BoogleCoordinates c) {
    List<BoogleCoordinates> adj = getAdj(board, c.getRow(), c.getCol());
    Deque<BoogleCoordinates> nonVisitedAdj = new ArrayDeque<>();
    for (BoogleCoordinates coord : adj) {
      if (!visited[coord.getRow()][coord.getCol()]) {
        nonVisitedAdj.add(coord);
      }
    }
    return nonVisitedAdj;
  }

  private String toStringWord(Iterable<BoogleCoordinates> currentWord) {
    StringBuilder sb = new StringBuilder();
    for (BoogleCoordinates letterCoordinates : currentWord) {
      sb.append(letterCoordinates.getLetter());
      if (letterCoordinates.getLetter() == 'Q') {
        sb.append('U');
      }
    }
    return sb.toString();
  }

  private List<BoogleCoordinates> getAdj(BoggleBoard board, int row, int col) {
    if (row < 0 || row >= board.rows() || col < 0 || col >= board.cols()) {
      throw new IllegalArgumentException(
          "Argument for colums or row are invalid row: " + row + " col: " + col);
    }
    // Go around clockwise
    List<BoogleCoordinates> adj = new ArrayList<>();

    if (BoogleCoordinates.hasEast(board, row, col)) {
      adj.add(new BoogleCoordinates(board, row, col + 1));
    }
    if (BoogleCoordinates.hasDiagSouthEast(board, row, col)) {
      adj.add(new BoogleCoordinates(board, row + 1, col + 1));
    }
    if (BoogleCoordinates.hasSouth(board, row, col)) {
      adj.add(new BoogleCoordinates(board, row + 1, col));
    }
    if (BoogleCoordinates.hasDiagSouthWest(board, row, col)) {
      adj.add(new BoogleCoordinates(board, row + 1, col - 1));
    }
    if (BoogleCoordinates.hasWest(board, row, col)) {
      adj.add(new BoogleCoordinates(board, row, col - 1));
    }
    if (BoogleCoordinates.hasDiagNorthWest(board, row, col)) {
      adj.add(new BoogleCoordinates(board, row - 1, col - 1));
    }
    if (BoogleCoordinates.hasNorth(board, row, col)) {
      adj.add(new BoogleCoordinates(board, row - 1, col));
    }
    if (BoogleCoordinates.hasDiagNorthEast(board, row, col)) {
      adj.add(new BoogleCoordinates(board, row - 1, col + 1));
    }
    return adj;
  }

  // Returns the score of the given word if it is in the dictionary, zero otherwise.
  // (You can assume the word contains only the uppercase letters A through Z.)
  public int scoreOf(String word) {
    if (trie.get(word)) {
      if (word.length() >= 8) {
        return 11;
      } else if (word.length() == 7) {
        return 5;
      } else if (word.length() == 6) {
        return 3;
      } else if (word.length() == 5) {
        return 2;
      } else if (word.length() >= 3 && word.length() <= 4) {
        return 1;
      } else {
        return 0;
      }
    }
    return 0;
  }

  private <T> String toStringStack(Deque<Deque<T>> deque) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (Iterator<Deque<T>> iterator = deque.descendingIterator(); iterator.hasNext();) {
      Deque<T> innerDeque = iterator.next();
      sb.append("[");
      for (Iterator<T> innerIterator = innerDeque.descendingIterator(); innerIterator.hasNext();) {
        T object = innerIterator.next();
        sb.append(object);
        if (innerIterator.hasNext()) {
          sb.append(", ");
        }
      }
      sb.append("]");
      if (iterator.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append("]");

    return sb.toString();
  }

  /**
   * Main provided for this exercise
   * 
   * @param args
   */
  public static void main(String[] args) {
    In in = new In(args[0]);
    String[] dictionary = in.readAllStrings();
    BoggleSolver solver = new BoggleSolver(dictionary);
    BoggleBoard board = new BoggleBoard(args[1]);
    int score = 0;
    for (String word : solver.getAllValidWords(board)) {
      StdOut.println(word);
      score += solver.scoreOf(word);
    }
    StdOut.println("Score = " + score);
  }
}
