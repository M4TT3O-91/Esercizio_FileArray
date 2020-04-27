import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
  private static final int FILE_ARRAY_DEFAULT_SIZE = 32;
  private static final String PRINT_OPERATION = "p";
  private static final String INCREMENT_OPERATION = "i";

  public static void main(String[] args) throws IOException {
    System.out.println("File Array exercise");

    if (args.length < 1) {
      System.out.println("Filename must be provided. Exit");
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
      System.out.print(operation);
      switch (operation) {
        case PRINT_OPERATION:
          fileArray.print();
          break;
        case INCREMENT_OPERATION:
          fileArray.incrementAll();
          System.out.println();
          break;
        default:
          System.out.println(String.format(" - skipped invalid operation"));
          break;
      }
    }
  }
}