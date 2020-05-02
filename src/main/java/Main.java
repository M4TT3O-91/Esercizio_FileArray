import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
  private static final int FILE_SIZE = 32;

  public static void main(String[] args) throws IOException {

    if (args.length < 1) {
      System.out.println("ATTENTION! Filename is needed you must provide it on command line.");
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

    String operation;

    for (int i = 1; i < args.length; i++) {
      operation = args[i].trim();
      System.out.print("Operation: " + operation);
      if("p".equalsIgnoreCase(operation)) {
          fileArray.print();
      }else if ("i".equalsIgnoreCase(operation)) {
        fileArray.incrementAll();
        System.out.println();
      }else{
          System.out.println(operation + " - Invalid operation");
      }
    }
  }

}