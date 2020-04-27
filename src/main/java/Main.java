import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
  private static final int FILE_ARRAY_DEFAULT_SIZE = 32;
  private static final String PRINT = "p";
  private static final String INCREMENT_ALL = "i";

  public static void main(String[] args) throws IOException {
    if (args.length < 1) {
      System.out.println("ATTENTION! Filename is needed you must provide it.");
      return;
    }
    String fileName = args[0];
    FileArray fileArray;
//    File file = new File(fileName);
//
//    if (file.exists()) {
//      fileArray = new FileArray(fileName);
//    } else {
//      fileArray = new FileArray(fileName, 1 + (new Random().nextInt(FILE_ARRAY_DEFAULT_SIZE)));
//    }
    fileArray = new FileArray(fileName, 1 + (new Random().nextInt(FILE_ARRAY_DEFAULT_SIZE)));
    String operation;

    for (int i = 1; i < args.length; i++) {
      operation = args[i].trim().toLowerCase();
      System.out.print(operation);
      if(operation == PRINT){
        fileArray.print();
      }else if(operation == INCREMENT_ALL ){
        fileArray.incrementAll();
        System.out.println();
      }else{
        System.out.println(String.format("No operation for '%s'",operation));
      }
//      switch (operation) {
//        case PRINT:
//          fileArray.print();
//          break;
//        case INCREMENT_ALL:
//          fileArray.incrementAll();
//          System.out.println();
//          break;
//        default:
//          System.out.println(String.format(" - skipped invalid operation"));
//          break;
//      }
    }
  }
}