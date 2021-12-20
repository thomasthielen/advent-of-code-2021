package day02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
  private static ArrayList<String> course = new ArrayList<String>();
  private static int horizontalPos = 0;
  private static int depth = 0;
  private static int aim = 0;

  public static void main(String[] args) {
    // read the course from the text file
    try (BufferedReader br = new BufferedReader(new FileReader("src/day02/course.txt"))) {
      String line = br.readLine();
      while (line != null) {
        course.add(line);
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
    for (String instruction : course) {
      String[] parameters = instruction.split(" ");
      if (parameters.length == 2) {
        switch (parameters[0]) {
        case "forward":
          horizontalPos += Integer.valueOf(parameters[1]);
          break;
        case "down":
          depth += Integer.valueOf(parameters[1]);
          break;
        case "up":
          depth -= Integer.valueOf(parameters[1]);
          break;
        default:
          break;
        }
      } else {
        System.out.println("An instruction not containing two parameters was read.");
      }
    }
    System.out.println("Part 1:");
    
    System.out.println("horizontal position = " + horizontalPos);
    System.out.println("depth = " + depth);
    
    System.out.println("mulitplication = " + (horizontalPos * depth));
    
    /*
     * Part 2
     */
    horizontalPos = 0;
    depth = 0;
    for (String instruction : course) {
      String[] parameters = instruction.split(" ");
      if (parameters.length == 2) {
        switch (parameters[0]) {
        case "forward":
          int movement = Integer.valueOf(parameters[1]);
          horizontalPos += movement;
          depth += aim * movement;
          break;
        case "down":
          aim += Integer.valueOf(parameters[1]);
          break;
        case "up":
          aim -= Integer.valueOf(parameters[1]);
          break;
        default:
          break;
        }
      } else {
        System.out.println("An instruction not containing two parameters was read.");
      }
    }
    
    System.out.println("\nPart 2:");
    System.out.println("horizontal position = " + horizontalPos);
    System.out.println("depth = " + depth);
    
    System.out.println("mulitplication = " + (horizontalPos * depth));
  }
}
