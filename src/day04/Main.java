package day04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

  private static ArrayList<String> input = new ArrayList<String>();
  private static ArrayList<Integer> drawOrder = new ArrayList<Integer>();

  private static HashSet<Board> boards = new HashSet<Board>();

  public static void main(String[] args) {
    // read the course from the text file
    try (BufferedReader br = new BufferedReader(new FileReader("src/day04/input.txt"))) {
      String line = br.readLine();
      while (line != null) {
        input.add(line);
        line = br.readLine();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    /*
     * Part 1
     */

    // extract the drawing order
    String drawOrderLine = input.get(0);
    String[] drawOrderParts = drawOrderLine.split(",");
    for (String s : drawOrderParts) {
      drawOrder.add(Integer.valueOf(s));
    }

    // extract the boards
    ArrayList<Integer> boardInput = new ArrayList<Integer>();
    for (int i = 2; i < input.size(); i++) {
      String line = input.get(i);
      if (line.isEmpty()) {
        boards.add(new Board(boardInput));
        boardInput.clear();
      } else {
        String[] lineParts = line.trim().split("\\s+");
        for (String s : lineParts) {
          boardInput.add(Integer.valueOf(s));
        }
      }
    }
    
    // check for the winner
    System.out.println("Winner Score = " + checkWinner());
    
    /*
     * Part 2
     */
    
    // check for the ultimate loser
    System.out.println("Ultimate Loser Score = "+ checkUltimateLoser());
  }
  
  private static int checkWinner() {
    for (int number : drawOrder) {
      for (Board b : boards) {
        boolean bingo = b.markNumber(number);
        if (bingo) {
          int score = b.calculateUnmarkedSum() * number;
          return score;
        }
      }
    }
    return -1;
  }
  
  private static int checkUltimateLoser() {
    ArrayList<Board> boardsCopy = new ArrayList<Board>(boards);
    for (int number : drawOrder) {
      ArrayList<Board> boardsToDelete = new ArrayList<Board>();
      for (Board b : boardsCopy) {
        boolean bingo = b.markNumber(number);
        if (bingo) {
          boardsToDelete.add(b);
        }
        if (boardsCopy.size() == 1 && bingo) {
          int score = b.calculateUnmarkedSum() * number;
          return score;
        }
      }
      boardsCopy.removeAll(boardsToDelete);
    }
    return -1;
  }
}
