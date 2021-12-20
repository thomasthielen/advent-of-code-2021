package day01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

  private static ArrayList<Integer> measurementsList = new ArrayList<Integer>();
  private static int mLarger = 0;
  private static int swLarger = 0;

  public static void main(String[] args) {
    // read the measurements from the text file
    try (BufferedReader br = new BufferedReader(new FileReader("src/day01/measurements.txt"))) {
      String line = br.readLine();

      while (line != null) {
        measurementsList.add(Integer.valueOf(line));
        line = br.readLine();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // convert to an array
    int[] measurements = new int[measurementsList.size()];
    for (int i = 0; i < measurements.length; i++) {
      measurements[i] = measurementsList.get(i);
    }

    /*
     * PART 1
     * 
     * count how many measurements are larger than the previous measurement
     */
    for (int i = 1; i < measurements.length; i++) {
      if (measurements[i] > measurements[i - 1]) {
        mLarger++;
      }
    }

    System.out.println("amount of measurements larger than the previous measurement = " + mLarger);

    /*
     * PART 2
     * 
     * count the number of times the sum of measurements in this sliding window
     * increases
     */
    int[] slidingWindows = new int[measurements.length - 2];
    for (int i = 0; i < slidingWindows.length; i++) {
      slidingWindows[i] = measurements[i] + measurements[i + 1] + measurements[i + 2];
    }
    for (int i = 1; i < slidingWindows.length; i++) {
      if (slidingWindows[i] > slidingWindows[i - 1]) {
        swLarger++;
      }
    }

    System.out.println("\nnumber of times the sum of measurements in this sliding window increases = " + swLarger);
  }
}
