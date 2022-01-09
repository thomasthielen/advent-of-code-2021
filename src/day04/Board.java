package day04;

import java.util.ArrayList;

public class Board {
  private int[][] numbers = new int[5][5];
  private boolean[][] markings = new boolean[5][5];

  public Board(ArrayList<Integer> input) {
    int index = 0;
    for (int row = 0; row < numbers.length; row++) {
      for (int column = 0; column < numbers[0].length; column++) {
        numbers[row][column] = input.get(index);
        index++;
        markings[row][column] = false;
      }
    }
  }

  public boolean markNumber(int number) {
    for (int row = 0; row < numbers.length; row++) {
      for (int column = 0; column < numbers[0].length; column++) {
        if (numbers[row][column] == number) {
          markings[row][column] = true;
          break;
        }
      }
    }
    return bingo();
  }

  public int calculateUnmarkedSum() {
    int sum = 0;
    for (int row = 0; row < numbers.length; row++) {
      for (int column = 0; column < numbers[0].length; column++) {
        if (!markings[row][column]) {
          sum += numbers[row][column];
        }
      }
    }
    return sum;
  }

  private boolean bingo() {
    // check the rows
    for (int row = 0; row < numbers.length; row++) {
      boolean rowBingo = true;
      for (int column = 0; column < numbers[0].length; column++) {
        // check whether one of the columns within the row is false
        if (!markings[row][column]) {
          rowBingo = false;
          break;
        }
      }
      if (rowBingo) {
        return true;
      }
    }
    // check the columns
    for (int column = 0; column < numbers.length; column++) {
      boolean columnBingo = true;
      for (int row = 0; row < numbers[0].length; row++) {
        // check whether one of the rows within the column is false
        if (!markings[row][column]) {
          columnBingo = false;
          break;
        }
      }
      if (columnBingo) {
        return true;
      }
    }
    return false;
  }
}
