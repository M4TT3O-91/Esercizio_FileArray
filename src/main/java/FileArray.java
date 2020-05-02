import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class FileArray {
  private static final int UPPER_LIMIT = 1024;
  private static final int PRINT_COLUMNS = 5;

  protected final String fileName;

  public FileArray(String fileName) throws IOException {
    this.fileName = fileName;
    File file = new File(fileName);
    int[] dataOnFile = read();
    System.out.println("Read " + dataOnFile.length + " numbers from " + fileName);
  }

  public FileArray(String fileName, int dim) throws IOException {
    this.fileName = fileName;
    File file = new File(fileName);
    int[] data = new int[dim];
    Random numberGenerator = new Random();

    for (int i = 0; i < dim; i++) {
      data[i] = numberGenerator.nextInt(UPPER_LIMIT) + 1;
    }
    write(data);
    System.out.println(String.format("File '%s' have been created with %s numbers", file.getAbsolutePath(), dim));
  }

  public void print() throws IOException {
    int[] dataOnFile = read();
    int digits = digits(max(dataOnFile));
    int padding = digits(dataOnFile.length);
    int minValue;

    for (int i = 0; i < dataOnFile.length; i++) {
      if (i % PRINT_COLUMNS == 0) {
        minValue = Math.min(PRINT_COLUMNS - 1 + i, dataOnFile.length - 1);
        System.out.println();
        System.out.printf("[" + "%0" + padding + "d - " + "%0" + padding + "d]", i, minValue);
      }
      System.out.printf(" %" + digits + "d", dataOnFile[i]);
    }
    System.out.println();
  }

  public void incrementAll() throws IOException {
    int[] data = read();
    for (int i = 0; i < data.length; i++) {
      data[i] = Math.min(data[i] + 1, UPPER_LIMIT);
    }
    write(data);
  }

  protected int[] read() throws IOException {
    DataInputStream inputStream = new DataInputStream(new FileInputStream(new File(fileName)));
    int size = inputStream.readInt();
    int[] data = new int[size];

    for (int i = 0; i < size; i++) {
      data[i] = inputStream.readInt();
    }
    inputStream.close();
    return data;
  }

  protected void write(int[] data) throws IOException {
    DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(new File(fileName)));
    outputStream.writeInt(data.length);
    for (int number : data) {
      outputStream.writeInt(number);
    }
    outputStream.close();
  }

  private static int digits(int number) {
    int digit = (int) Math.ceil(Math.log10(number));
    return digit;
  }

  private static int max(int[] data) {
    int max = 0;
    for (int number : data) {
      max = Math.max(number, max);
    }
    return max;
  }
}