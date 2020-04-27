
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class FileArray {
  private static final int MAX_VALUE = 2^10;
  private static final int PRINT_COLUMNS = 5;

  private final String fileName;

  public FileArray(String fileName) throws IOException {
    this.fileName = fileName;
    File file = new File(fileName);

    int[] dataOnFile = read();
    System.out.printf("Read data from file %s%n", file.getAbsolutePath());
    System.out.printf("%s numbers have been read%n", dataOnFile.length);
  }

  public FileArray(String fileName, int dim) throws IOException {
    this.fileName = fileName;
    File file = new File(fileName);
    int[] data = new int[dim];
    Random numberGenerator = new Random();

    for (int i = 0; i < dim; i++) {
      data[i] = numberGenerator.nextInt(MAX_VALUE) + 1;
    }
    write(data);
    System.out.println(String.format("File '%s' have been created with %s numbers", file.getAbsolutePath(), dim));
  }

  public void print() throws IOException {
    int[] dataOnFile = read();
    String numberFormat = " %" + digits(max(dataOnFile)) + "d";
    String limitFormat = "%0" + digits(dataOnFile.length) + "d";
    String headerFormat = "%n[" + limitFormat + "-" + limitFormat + "]";

    for (int i = 0; i < dataOnFile.length; i++) {
      if (i % PRINT_COLUMNS == 0) {
        // after first line we need a new line before print line header
        System.out.print(String.format(headerFormat, i, Math.min(PRINT_COLUMNS - 1 + i, dataOnFile.length - 1)));
      }
      System.out.print(String.format(numberFormat, dataOnFile[i]));
    }
    System.out.println();
  }

  public void incrementAll() throws IOException {
    int[] data = read();
    for (int i = 0; i < data.length; i++) {
      data[i] = Math.min(data[i] + 1, MAX_VALUE);
    }
    write(data);
  }

  private int[] read() throws IOException {
    DataInputStream inputStream = new DataInputStream(new FileInputStream(new File(fileName)));
    int size = inputStream.readInt();
    int[] data = new int[size];

    for (int i = 0; i < size; i++) {
      data[i] = inputStream.readInt();
    }
    inputStream.close();
    return data;
  }

  private void write(int[] data) throws IOException {
    DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(new File(fileName)));
    outputStream.writeInt(data.length);
    for (int number : data) {
      outputStream.writeInt(number);
    }
    outputStream.close();
  }

  private static int digits(int number) {
    return String.valueOf(number).length();
  }

  private static int max(int[] data) {
    int max = 0;
    for (int number : data) {
      max = Math.max(number, max);
    }
    return max;
  }
}





