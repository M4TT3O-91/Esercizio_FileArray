import java.io.IOException;
import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPFileArray extends FileArray{

  public GZIPFileArray(String fileName) throws IOException {
    super(fileName);
  }

  public GZIPFileArray(String fileName, int size) throws IOException {
    super(fileName, size);
  }

  @Override
  protected int[] read() throws IOException {
    DataInputStream inputStream = new DataInputStream(new GZIPInputStream(new FileInputStream(new File(fileName))));
    int size = inputStream.readInt();
    int[] data = new int[size];

    for (int i = 0; i < size; i++) {
      data[i] = inputStream.readInt();
    }
    inputStream.close();
    return data;
  }

  @Override
  protected void write(int[] data) throws IOException {
    DataOutputStream dataOutputStream = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(new File(fileName))));
    dataOutputStream.writeInt(data.length);
    for (int number : data) {
      dataOutputStream.writeInt(number);
    }
    dataOutputStream.close();
  }
}

