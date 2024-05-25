import java.io.*;

public class FileReaderThread extends Thread {
    int size;
    byte[] bytes;
    int even;
    int part;
    private String fileName;

    public FileReaderThread(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(fileName));

            size = dis.readInt();
            bytes = dis.readNBytes(size);
            even = dis.readInt();
            part = dis.readInt();
            int evenCheck = calculateEvenParity(bytes);
            if (even != evenCheck) {
                throw new EvenNotMatchException("Контрольные числа четности не совпадают.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (EvenNotMatchException e) {
            throw new RuntimeException(e);
        }

    }
    public static int calculateEvenParity(byte[] bytes) {
        int sum = 0;
        for (byte b : bytes) {
            sum += Integer.bitCount(b);
        }
        return sum % 2;
    }
}

