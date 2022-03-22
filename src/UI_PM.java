import javax.swing.*;
import java.util.HashMap;
import java.util.Scanner;

public class UI_PM extends JPanel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Generator generator = new Generator();
        AES256 aes256 = new AES256();
        HashMap<String, String> map = new HashMap<String,String>();
        while (true) {
            System.out.println("**************************************");
            System.out.println("Yapmak istediginiz islemi secin\n" +
                    "1. Yeni sifre yaratmak\n" +
                    "2. Kayitli sifreyi ogrenmek\n" +
                    "3. Cikis");
            String islem = scanner.nextLine();
            System.out.println("**************************************");
            if (islem.equals("1")) {
                System.out.println("Sifreyi hangi platform icin istiyorsaniz adini girin (not: sifreniz programda bu isimle kaydedilecektir!) : ");
                String name = scanner.nextLine();
                generator.generate();
                try {
                    aes256.init();
                    String encryptedKey = aes256.encrypt(generator.getPassword());
                    String decryptedKey = aes256.decrypt(encryptedKey);
                    System.err.println("Encrypted Key : " + encryptedKey);
                    System.err.println("Sifreniz : " + decryptedKey);
                    Save_Load.savePassword(map,name, encryptedKey);
                    System.out.println("Sifreniz basariyla sisteme kaydedildi");
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (islem.equals("2")) {
                //System.out.println("Sifreyi hangi platform icin istiyorsaniz adini girin (not: sifreniz programda bu isimle kaydedilecektir!) : ");
                //String name = scanner.nextLine();
                //generator.generate();
                try {
                    //aes256.init();
                    //String encryptedKey = aes256.encrypt(generator.getPassword());
                    //String decryptedKey = aes256.decrypt(encryptedKey);
                    //System.err.println("Encrypted Key : " + encryptedKey);
                    //System.err.println("Sifreniz : " + decryptedKey);
                    Save_Load.callPassword(map);
                    //System.out.println("Sifreniz basariyla sisteme kaydedildi");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (islem.equals("3")){
                System.out.println("Programdan cikiliyor...");
                break;
            }
            else {
                System.out.println("Lutfen gecerli bir islem giriniz");
            }
        }

    }

}
