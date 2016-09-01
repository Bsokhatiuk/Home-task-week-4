import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by lost on 28.08.2016.
 */
public class Main {
    public static final String NAME_FALE="C:\\Users\\lost\\Desktop\\Course\\lesson 1\\1.txt";
    public static final String NAME_FALE1="C:\\Users\\lost\\Desktop\\Course\\lesson 1\\2.txt";
    public static final String NAME_FALE2="C:\\Users\\lost\\Desktop\\Course\\lesson 1\\6.txt";
    public static final String NAME_FALE3="C:\\Users\\lost\\Desktop\\Course\\lesson 1\\test.txt";
    public static final String URL="http://prologistic.com.ua/kak-skachat-fajl-v-java.html";
    public static final String DOWNLOAD="C:\\Users\\lost\\Desktop\\Course\\lesson 1\\TestDowload.html";

    public static void main(String[] args) throws Exception {
        User o=new User();
        User o2=new User();
        User ann = new User("Ann");
        File dir = new File("C:\\Users\\lost\\Desktop\\Course\\lesson 1");
        User ann2 =new User();
        IOTest test = new IOTest();


        test.less(NAME_FALE);
        try {
            test.writeInto(NAME_FALE1,"Hello2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.less(NAME_FALE1);

        test.delete(NAME_FALE2);

        test.appendTo(NAME_FALE1,"World!");

        test.ls("C:\\Users\\lost\\Desktop\\Course\\lesson 1");

        test.grep(NAME_FALE3,"the");

        test.toByteArr(ann);

        System.out.println((User)test.fromByteArr(test.toByteArr(ann)));
        ann2=(User)test.fromByteArr(test.toByteArr(ann));
        System.out.println(ann2.getName());

        test.downloadFile(URL,DOWNLOAD);

        test.find(dir,"test");

        test.saveObjToFile(ann,NAME_FALE);
        System.out.println(test.clone(ann));

        o2=(User)test.cloneDeep(ann);
        System.out.println(o2.getName());

    }
    static class User implements Serializable,Cloneable{
        private String name;
        private int age;
        public  User(){

        }
        public  User(String name){
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {

            return age;
        }

        public String getName() {
            return name;
        }


    }
}
