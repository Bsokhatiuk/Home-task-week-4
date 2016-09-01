import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.lang.Object;

/**
 * Created by lost on 28.08.2016.
 */
public class IOTest implements IBash, Cloneable {

    @Override
    public String less(String path) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        StringBuilder result = new StringBuilder();
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null)
                result.append(line).append("\n");
        } catch (IOException e) {
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException t) {
                t.printStackTrace();
            }
        }
        System.out.println("Reading "+ path+ " has been successful");
        return result.toString();
    }

    @Override
    public void writeInto(String path, String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(path)));

        try {
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Write into "+ path+ " has been successful");
    }

    @Override
    public void appendTo(String path, String content) throws IOException {
        try {
            String result;
            result = less(path);
            result += content;
            writeInto(path, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successful");
    }

    @Override
    public List<String> ls(String currentDirPath) {
        File dir = new File(currentDirPath);
        List<String> list = new ArrayList<String>();
        File[] folderEntries = dir.listFiles();

        for (File entry : folderEntries) {
            list.add(entry.getName());
        }
        System.out.println("Current directory consist of "+list);
        return list;
    }

    @Override
    public boolean touch(String path) throws IOException {
        File file = new File(path);
        try {
            if (file.exists() == false) {
                file.createNewFile();
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.exists());
        return file.exists();
    }

    @Override
    public boolean delete(String path) {
        File file = new File(path);
        System.out.println(file.delete());
        return file.delete();
    }


    @Override
    public List<String> grep(String src, String keyWord) {
        List<String> list = new ArrayList<String>();
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(src)));

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(keyWord) == true) {
                    list.add(line + "\n");}
            }}
        catch (IOException e) {
            e.printStackTrace();}
        System.out.println(list);
        return list;
    }

    @Override
    public Object clone(Object obj) throws CloneNotSupportedException{
        return super.clone();
    }

    @Override
    public byte[] toByteArr(Object obj) {
        ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
        ObjectOutput out = null;

        try {
            out = new ObjectOutputStream(byteArr);
            out.writeObject(obj);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                byteArr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] resultBytes = byteArr.toByteArray();
            return resultBytes;
        }


    }

    @Override
    public Object fromByteArr(byte[] arr) {
        ByteArrayInputStream byteArr = new ByteArrayInputStream(arr);
        ObjectInput in = null;
        Object object=null;
        try {
            in = new ObjectInputStream(byteArr);

            try {
                object = in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                byteArr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    @Override
    public Object cloneDeep(Object obj) {
        return  fromByteArr(toByteArr(obj));
    }

    @Override
    public void saveObjToFile(Object obj, String filePath) {
        ObjectOutputStream objectOutputStream;

        try {
            objectOutputStream= new ObjectOutputStream(new FileOutputStream(new File(filePath)));
            objectOutputStream.writeObject(obj);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadFile(String url, String localPathName) {
        URL url1 = null;
        FileOutputStream fos=null;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ReadableByteChannel rbc = null;
        try {
            rbc = Channels.newChannel(url1.openStream());
            fos = new FileOutputStream(localPathName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
                rbc.close();
            } catch (IOException e) {
                e.printStackTrace();}
        }
        System.out.println("Dowload has been successful");
    }

    @Override
    public List<String> find(File dir, String keyWord) {
        File dirs = new File(dir.getAbsolutePath());
        List<String> list = new ArrayList<String>();
        File[] folderEntries = dirs.listFiles();

        for (File entry : folderEntries) {
            if (entry.isDirectory()==true){
                list.addAll(find(entry,keyWord));
            }
            if (entry.getName().contains(keyWord)==true){
                list.add(entry.getName());}
        }
        System.out.println(list);
        return list;
    }
}