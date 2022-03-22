import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Save_Load {

    public static void savePassword(HashMap<String, String> map ,String name, String encryptedKey) throws IOException {

        FileOutputStream fos = new FileOutputStream("conf.bin", true);
        ObjectOutputStream outputStream = new ObjectOutputStream(fos);
        map.put(name, encryptedKey);
        outputStream.writeObject(map);
        //outputStream.close();
        //fos.close();

    }
    public static void callPassword(HashMap<String, String> map) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("conf.bin");
        ObjectInputStream inputStream = new ObjectInputStream(fis);
        map = (HashMap)inputStream.readObject();
        inputStream.close();
        fis.close();
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println("key :" + entry.getKey() + " & values : ");
            System.out.println(entry.getValue());
        }
    }

}
