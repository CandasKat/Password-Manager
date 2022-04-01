package com.PassMan.Main;

import com.PassMan.Main.AES256;
import com.PassMan.Main.Generator;
import com.PassMan.Main.Save_Load;

import java.util.HashMap;
import java.util.Scanner;


public class UI_PM{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Generator generator = new Generator();
        AES256 aes256 = new AES256();
        HashMap<String, String[]> map = new HashMap<String,String[]>();

        while (true) {
            System.out.println("**************************************");
            System.out.println("Sélectionnez l'action que vous voulez faire\n" +
                    "1. Création d'un nouveau mot de passe\n" +
                    "2. Connaître le mot de passe enregistré\n" +
                    "3. Sortir");
            String islem = scanner.nextLine();
            System.out.println("**************************************");
            if (islem.equals("1")) {
                System.out.println("Entrez le nom de la plateforme pour laquelle vous voulez le mot de passe (attention : votre mot de passe sera enregistré avec ce nom dans le programme !) :");
                String name = scanner.nextLine();

                while (true) {
                    System.out.println("Choisissez votre taille de mot de passe préférée\n" +
                            "a. 16 caractère\n" +
                            "b. 28 caractère");
                    String charTaille = scanner.nextLine();
                    if (charTaille.equals("a") || charTaille.equals("A")) {
                        generator.generateFor16();
                        try {
                            aes256.init();
                            String encryptedKey = aes256.encrypt(generator.generateFor16());
                            String decryptedKey = aes256.decrypt(encryptedKey);
                            System.err.println("Votre mot de passe : " + decryptedKey);
                            Save_Load.add(map, name, encryptedKey);
                            System.out.println("Votre mot de passe a été enregistré avec succès dans le système");
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (charTaille.equals("b") || charTaille.equals("B")) {
                        try {
                            aes256.init();
                            String encryptedKey = aes256.encrypt(generator.generateFor28());
                            String decryptedKey = aes256.decrypt(encryptedKey);
                            System.err.println("Votre mot de passe : " + decryptedKey);
                            Save_Load.add(map, name, encryptedKey);
                            System.out.println("Votre mot de passe a été enregistré avec succès dans le système");
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        System.out.println("You have not entered a valid transaction");
                        System.out.println();
                        System.out.println("**************************************");
                    }

                }




            } else if (islem.equals("2")) {

                try {
                    System.out.println("Saisissez la plateforme dont vous souhaitez connaître le mot de passe : ");
                    String name = scanner.nextLine();

                    Save_Load.readFile(name);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (islem.equals("3")){
                System.out.println("Quitter le programme...");
                break;
            }
            else {
                System.out.println("Veuillez entrer une transaction valide");
            }
        }

    }

}
