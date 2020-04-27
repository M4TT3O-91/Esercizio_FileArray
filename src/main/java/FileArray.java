import java.io.*;
import java.util.Random;

public class FileArray {
  private static final int MAX_VALUE = 1024;
  private static final int COLUMNS = 5;

  private final String fileName;

  public FileArray(String fileName) throws IOException {
    this.fileName = fileName;
    File file = new File(fileName);

    int[] dataOnFile = read();
    System.out.println(String.format("Read data from file %s",file.getAbsolutePath()));
    System.out.println(String.format(" %s numbers have been read", dataOnFile.length));
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

  private static int numberOfDigit(int number) {
    return String.valueOf(number).length();
  }

  private static int max(int[] data) {
    int max = 0;
    for (int number : data) {
      max = Math.max(number, max);
    }
    return max;
  }

  public void print() throws IOException {
    int[] dataOnFile = read();
    String numberFormat = " %" + numberOfDigit(max(dataOnFile)) + "d";
    String limitFormat = "%0" + numberOfDigit(dataOnFile.length) + "d";
    String headerFormat = "%n[" + limitFormat + "-" + limitFormat + "]";

    for (int i = 0; i < dataOnFile.length; i++) {
      if (i % COLUMNS == 0) {
        // after first line we need a new line before print line header
        System.out.print(String.format(headerFormat, i, Math.min(COLUMNS - 1 + i, dataOnFile.length - 1)));
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
}