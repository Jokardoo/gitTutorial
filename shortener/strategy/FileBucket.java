package test.shortener.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    // Конструктор
    public FileBucket() {
        try {
            // Создаем путь
            path = Files.createTempFile(Integer.toHexString(hashCode()), ".tmp");
            path.toFile().deleteOnExit();

            // Если такой файл существует, удаляем старый и создаем новый
            Files.deleteIfExists(path);
            Files.createFile(path);
        }
        catch (IOException e) {

        }
    }

    // Вычисляем размер файла
    public long getFileSize() {
        try {
            return Files.size(path);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Entry getEntry() {
        if (getFileSize() == 0L) {
            return null;
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
            return (Entry) ois.readObject();
        }
        catch (Exception e) {

        }
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        }
        catch (IOException e) {

        }
    }

    public void putEntry(Entry entry) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path));
            oos.writeObject(entry);
        }
        catch (IOException e) {

        }
    }


}