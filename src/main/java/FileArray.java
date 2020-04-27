import java.io.*;
import java.util.Random;

public class FileArray {
  private static final int MAX_VALUE = 1024;
  private static final int NUMBERS_IN_A_ROW = 5;
  private final String fileName;

  public FileArray(String fileName) throws IOException {
    this.fileName = fileName;
    File file = new File(fileName);

    int[] data = read();
    System.out.println(String.format("read %s numbers from file '%s'", data.length, file.getAbsolutePath()));
  }

  public FileArray(String fileName, int size) throws IOException {
    this.fileName = fileName;
    File file = new File(fileName);
    int[] data = new int[size];
    Random numberGenerator = new Random();

    for (int i = 0; i < size; i++) {
      data[i] = numberGenerator.nextInt(MAX_VALUE) + 1;
    }
    write(data);
    System.out.println(String.format("file '%s' created with %s numbers", file.getAbsolutePath(), size));
  }

  private static int countCharacters(int number) {
    return String.valueOf(number).length();
  }

  private static int findMaxNumber(int[] data) {
    int maxNumber = 0;
    for (int number : data) {
      maxNumber = Math.max(number, maxNumber);
    }
    return maxNumber;
  }

  public void print() throws IOException {
    int[] data = read();
    String numberFormat = " %" + countCharacters(findMaxNumber(data)) + "d";
    String limitFormat = "%0" + countCharacters(data.length) + "d";
    String headerFormat = "%n[" + limitFormat + "-" + limitFormat + "]";

    for (int i = 0; i < data.length; i++) {
      if (i % NUMBERS_IN_A_ROW == 0) {
        // after first line we need a new line before print line header
        System.out.print(String.format(headerFormat, i, Math.min(NUMBERS_IN_A_ROW - 1 + i, data.length - 1)));
      }

      System.out.print(String.format(numberFormat, data[i]));
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