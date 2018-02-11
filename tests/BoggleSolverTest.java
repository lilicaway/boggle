import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class BoggleSolverTest {

  @Test
  public void testGetAdjBoard4per4TopLeftCorner() {
    // Board
    // M R M N
    // B G D N
    // T T T V
    // S D F C
    BoggleBoard board = new BoggleBoard("test_data/board-points0.txt");
    String[] dic = { "" };
    BoggleSolver solver = new BoggleSolver(dic);

    // All adj of 0,0, letter M
    List<BoogleCoordinates> adj = solver.getAdj(board, 0, 0);
    assertEquals(3, adj.size());

    BoogleCoordinates coord = adj.get(0);
    assertEquals('R', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(1);
    assertEquals('G', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(2);
    assertEquals('B', board.getLetter(coord.getRow(), coord.getCol()));
  }

  @Test
  public void testGetAdjBoard4per4TopRightCorner() {
    // Board
    // M R M N
    // B G D N
    // T T T V
    // S D F C
    BoggleBoard board = new BoggleBoard("test_data/board-points0.txt");
    String[] dic = { "" };
    BoggleSolver solver = new BoggleSolver(dic);

    // All adj of 0,3 letter N
    List<BoogleCoordinates> adj = solver.getAdj(board, 0, 3);
    assertEquals(3, adj.size());

    BoogleCoordinates coord = adj.get(0);
    assertEquals('N', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(1);
    assertEquals('D', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(2);
    assertEquals('M', board.getLetter(coord.getRow(), coord.getCol()));
  }

  @Test
  public void testGetAdjBoard4per4BootomRightCorner() {
    // Board
    // M R M N
    // B G D N
    // T T T V
    // S D F C
    BoggleBoard board = new BoggleBoard("test_data/board-points0.txt");
    String[] dic = { "" };
    BoggleSolver solver = new BoggleSolver(dic);

    // All adj of 3,3 letter C
    List<BoogleCoordinates> adj = solver.getAdj(board, 3, 3);
    assertEquals(3, adj.size());

    BoogleCoordinates coord = adj.get(0);
    assertEquals('F', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(1);
    assertEquals('T', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(2);
    assertEquals('V', board.getLetter(coord.getRow(), coord.getCol()));
  }

  @Test
  public void testGetAdjBoard4per4BootomLeftCorner() {
    // Board
    // M R M N
    // B G D N
    // T T T V
    // S D F C
    BoggleBoard board = new BoggleBoard("test_data/board-points0.txt");
    String[] dic = { "" };
    BoggleSolver solver = new BoggleSolver(dic);

    // All adj of 0,3 letter S
    List<BoogleCoordinates> adj = solver.getAdj(board, 3, 0);
    assertEquals(3, adj.size());

    BoogleCoordinates coord = adj.get(0);
    assertEquals('D', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(1);
    assertEquals('T', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(2);
    assertEquals('T', board.getLetter(coord.getRow(), coord.getCol()));
  }

  @Test
  public void testGetAdjBoard4per4Middle() {
    // Board
    // M R M N
    // B G D N
    // T T T V
    // S D F C
    BoggleBoard board = new BoggleBoard("test_data/board-points0.txt");
    String[] dic = { "" };
    BoggleSolver solver = new BoggleSolver(dic);

    // All adj of 1,1 letter G
    List<BoogleCoordinates> adj = solver.getAdj(board, 1, 1);
    assertEquals(8, adj.size());

    BoogleCoordinates coord = adj.get(0);
    assertEquals('D', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(1);
    assertEquals('T', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(2);
    assertEquals('T', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(3);
    assertEquals('T', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(4);
    assertEquals('B', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(5);
    assertEquals('M', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(6);
    assertEquals('R', board.getLetter(coord.getRow(), coord.getCol()));
    coord = adj.get(7);
    assertEquals('M', board.getLetter(coord.getRow(), coord.getCol()));
  }

  @Test
  public void testGetAdjBoard1Col() {
    BoggleBoard board = new BoggleBoard("test_data/board-dichlorodiphenyltrichloroethanes.txt");
    String[] dic = { "" };
    BoggleSolver solver = new BoggleSolver(dic);
    List<BoogleCoordinates> adj = solver.getAdj(board, 0, 0);
    assertEquals(1, adj.size());
  }

  @Test
  public void testStress() throws Exception {
    // BoggleBoard board = new BoggleBoard("test_data/board4x4.txt");
    // In in = new In("test_data/dictionary-algs4.txt");
    BoggleBoard board = new BoggleBoard("test_data/board-points200.txt");
    In in = new In("test_data/dictionary-yawl.txt");
    String[] dic = in.readAllStrings();
    Iterable<String> allValidWords = null;
    BoggleSolver solver = null;
    solver = new BoggleSolver(dic);

    long startTimeMillis = System.currentTimeMillis();
    for (int i = 0; i < 10000; i++) {
      allValidWords = solver.getAllValidWords(board);
    }
    long endTimeMilis = System.currentTimeMillis();

    int totalScore = 0;
    for (String word : allValidWords) {
      totalScore = totalScore + solver.scoreOf(word);
      // System.out.println(word);
    }
    long endScoringTimeMillis = System.currentTimeMillis();
    System.out.println("Total score: " + totalScore);
    System.out.println("getAllValidWords took: " + (endTimeMilis - startTimeMillis) + " ms.");
    System.out.println("solver.scoreOf took: " + (endScoringTimeMillis - endTimeMilis) + "ms.");
  }
}
