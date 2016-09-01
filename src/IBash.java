import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by serhii on 28.08.16.
 */
public interface IBash {

    // show file content
    String less(String path) throws FileNotFoundException;

    void writeInto(String path, String content) throws IOException;

    void appendTo(String path, String content) throws IOException ;

    // show files in current directory
    List<String> ls(String currentDirPath);
    // use File
    // create new file
    boolean touch(String path) throws IOException;

    boolean delete(String path);

    List<String> grep(String src, String keyWord);

    //shallow copy
    // use standard clone mechanism in java
    Object clone(Object obj) throws CloneNotSupportedException;

    byte[] toByteArr(Object obj);

    Object fromByteArr(byte[] arr);

    // do deep copy via Java Serialization, use above methods
    Object cloneDeep(Object obj);

    void saveObjToFile(Object obj, String filePath);

    void downloadFile(String url, String localPathName);

    // implement recursive search (find in curr directory, then find in child dir, then repeat...)
    List<String> find(File dir, String keyWord);
}
