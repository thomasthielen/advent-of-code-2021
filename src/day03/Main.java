package day03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  private static ArrayList<String> report = new ArrayList<String>();

  public static void main(String[] args) {
    // read the course from the text file
    try (BufferedReader br = new BufferedReader(new FileReader("src/day03/diagnosis_report.txt"))) {
      String line = br.readLine();
      while (line != null) {
        report.add(line);
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

    // create the binary numbers gamma and epsilon
    int binaryLength = report.get(0).length();

    int[] gamma = new int[binaryLength];
    int[] epsilon = new int[binaryLength];
    for (int i = 0; i < binaryLength; i++) {
      int bits0 = 0;
      int bits1 = 0;
      for (String s : report) {
        if (s.charAt(i) == '0') {
          bits0++;
        } else if (s.charAt(i) == '1') {
          bits1++;
        } else {
          System.out.println("Read character is not binary!");
        }
      }
      if (bits0 > bits1) {
        gamma[i] = 0;
        epsilon[i] = 1;
      } else {
        gamma[i] = 1;
        epsilon[i] = 0;
      }
    }

    // convert the binary numbers to decimal
    int gammaRate = 0;
    int epsilonRate = 0;
    int index = binaryLength - 1;
    for (int i = 0; i < binaryLength; i++) {
      gammaRate += gamma[index - i] * (int) Math.pow(2.0, i);
      epsilonRate += epsilon[index - i] * (int) Math.pow(2.0, i);
    }

    System.out.println("gammaRate = " + gammaRate);
    System.out.println(Arrays.toString(gamma));
    System.out.println("epsilonRate = " + epsilonRate);
    System.out.println(Arrays.toString(epsilon));

    int powerConsumption = gammaRate * epsilonRate;
    System.out.println("powerConsumption = " + powerConsumption);

    /*
     * Part 2
     */

    // oxygen generator rating
    int ogRating = 0;

    ArrayList<String> reportCopy = new ArrayList<String>(report);
    for (int i = 0; i < binaryLength; i++) {
      int bits0 = 0;
      int bits1 = 0;
      for (String s : reportCopy) {
        if (s.charAt(i) == '0') {
          bits0++;
        } else if (s.charAt(i) == '1') {
          bits1++;
        } else {
          System.out.println("Read character is not binary!");
        }
      }
      ArrayList<String> toDelete = new ArrayList<String>();
      if (bits0 > bits1) {
        // 0 more common
        for (String s : reportCopy) {
          if (s.charAt(i) == '1') {
            toDelete.add(s);
          }
        }
      } else {
        // 1 more common or both equally common
        for (String s : reportCopy) {
          if (s.charAt(i) == '0') {
            toDelete.add(s);
          }
        }
      }
      reportCopy.removeAll(toDelete);
      if (reportCopy.size() == 1) {
        ogRating = Integer.parseInt(reportCopy.get(0), 2);
        break;
      } else if (reportCopy.size() == 0) {
        System.out.println("No rating value found (should NEVER happen!)");
        break;
      }
    }

    // CO2 scrubber rating
    int co2Rating = 0;

    reportCopy.clear();
    reportCopy.addAll(report);
    for (int i = 0; i < binaryLength; i++) {
      int bits0 = 0;
      int bits1 = 0;
      for (String s : reportCopy) {
        if (s.charAt(i) == '0') {
          bits0++;
        } else if (s.charAt(i) == '1') {
          bits1++;
        } else {
          System.out.println("Read character is not binary!");
        }
      }
      ArrayList<String> toDelete = new ArrayList<String>();
      if (bits0 > bits1) {
        // 0 more common
        for (String s : reportCopy) {
          if (s.charAt(i) == '0') {
            toDelete.add(s);
          }
        }
      } else {
        // 1 more common or both equally common
        for (String s : reportCopy) {
          if (s.charAt(i) == '1') {
            toDelete.add(s);
          }
        }
      }
      reportCopy.removeAll(toDelete);
      if (reportCopy.size() == 1) {
        co2Rating = Integer.parseInt(reportCopy.get(0), 2);
        break;
      } else if (reportCopy.size() == 0) {
        System.out.println("No rating value found (should NEVER happen!)");
        break;
      }
    }

    System.out.println("\n\nogRating = " + ogRating);
    System.out.println("co2Rating = " + co2Rating);

    int lifeSupportRating = ogRating * co2Rating;
    System.out.println("lifeSupportRating = " + lifeSupportRating);
  }
}
