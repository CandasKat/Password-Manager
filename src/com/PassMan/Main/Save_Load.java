package com.PassMan.Main;

import com.PassMan.Main.AES256;

import java.io.*;
import java.util.*;


public class Save_Load {
    static Scanner scanner = new Scanner(System.in);

    private static File file = new File("conf.bin");

    public static boolean add(HashMap<String, String[]> map, String name, String encryptedKey){
        boolean status = false;

        if (map != null){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("conf.bin", true);
                if (file.length() == 0){
                    ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
                    outputStream.writeObject(new AES256().export(map, name, encryptedKey));
                }
                else {
                    NewObjectoutputStream outputStream = new NewObjectoutputStream(fileOutputStream);
                    outputStream.writeObject(new AES256().export(map, name, encryptedKey));
                    outputStream.close();
                }
                fileOutputStream.close();
            }
            catch (Exception e){
                System.out.println("Exception olustu!..");
            }
            status = true;
        }
        return status;
    }

    public static boolean readFile(String name){
        AES256 aes256 = new AES256();
        String encryptedKey;
        String secretKey;
        String iv;
        boolean status = false;
        try{
            file.createNewFile();
        }
        catch (Exception e){
            System.out.println("Exception olustu!..");
        }
        if (file.length() != 0){
            try{
                FileInputStream fileInputStream = new FileInputStream("conf.bin");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                HashMap<String, String[]> map = new HashMap<String, String[]>();

                while(fileInputStream.available() != 0){
                    map.putAll((HashMap<String,String[]>) objectInputStream.readObject());
                }
                objectInputStream.close();
                fileInputStream.close();
                String[] keys = map.get(name);

                if (keys == null){
                    System.out.println("Vous n'avez aucun mot de passe enregistré pour cette plateforme." +
                            "\nSi vous êtes sûr d'avoir un mot de passe enregistré, veuillez réessayer en faisant attention aux majuscules.");
                }
                else{

                    encryptedKey = keys[0];
                    iv = keys[1];
                    secretKey = keys[2];

                    aes256.initFromStrings(secretKey,iv);
                    String sifre = aes256.decrypt(encryptedKey);
                    System.out.println("Votre mot de passe = " + sifre);
                }

                status = true;
            }
            catch (Exception e){
                System.out.println("Exception olustu!..");
            }
        }
        return status;
    }



}
