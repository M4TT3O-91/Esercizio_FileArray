import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
  private static final int FILE_SIZE = 32;

  public static void main(String[] args) throws IOException {
    String operation;
    if (args.length < 1) {
      System.out.println("ATTENTION! No filename detected");
      System.out.println("For run the code: java Main filename operation");
      System.out.println("Operation: p for print and i for increment all");
      return;
    }
    String fileName = args[0];
    FileArray fileArray;
    File file = new File(fileName);

    if (file.exists()) {
      if (fileName.toLowerCase().endsWith(".zip")) {
        fileArray = new GZIPFileArray(fileName);
      }else{
        fileArray = new FileArray(fileName);
      }
    } else {
      if (fileName.toLowerCase().endsWith(".zip")) {
        fileArray = new GZIPFileArray(fileName, 1 + (new Random().nextInt(FILE_SIZE)));
      }else{
        fileArray = new FileArray(fileName, 1 + (new Random().nextInt(FILE_SIZE)));
      }
    }

    for (int i = 1; i < args.length; i++) {
      operation = args[i].trim();

      if("p".equalsIgnoreCase(operation)) {
        System.out.print("Print Operation : ");
        fileArray.print();
      }else if ("i".equalsIgnoreCase(operation)) {
        System.out.print("Increment All Operation");
        fileArray.incrementAll();
        System.out.println();
      }else{
        System.out.println("Unknown operation: " + operation);
      }
    }
  }

}