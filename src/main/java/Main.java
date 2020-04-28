import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
  private static final int FILE_ARRAY_DEFAULT_SIZE = 32;

  public static void main(String[] args) throws IOException {
    if (args.length < 1) {
      System.out.println("ATTENTION! Filename is needed you must provide it.");
      return;
    }
    String fileName = args[0];
    FileArray fileArray;
    File file = new File(fileName);

    if (file.exists()) {
      fileArray = new FileArray(fileName);
    } else {
      fileArray = new FileArray(fileName, 1 + (new Random().nextInt(FILE_ARRAY_DEFAULT_SIZE)));
    }

    String operation;

    for (int i = 1; i < args.length; i++) {
      operation = args[i].trim().toLowerCase();
      System.out.print("Operation: "+ operation);
      switch (operation) {
        case "p":
          fileArray.print();
          break;
        case "i":
          fileArray.incrementAll();
          System.out.println();
          break;
        default:
          System.out.println(operation + " - Invalid operation");
          break;
      }
    }
  }
}