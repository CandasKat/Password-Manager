package com.PassMan.Main;

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
            System.out.println("Select the action you want to do\n" +
                    "1. Creating a new password\n" +
                    "2. Knowing the saved password\n" +
                    "3. Exit");
            String islem = scanner.nextLine();
            System.out.println("**************************************");
            if (islem.equals("1")) {
                System.out.println("Enter the name of the platform for which you want the password (note: your password will be saved with this name in the program!) : ");
                String name = scanner.nextLine();

                while (true) {
                    System.out.println("Choose your preferred password size\n" +
                            "a. 16 character\n" +
                            "b. 28 character");
                    String charTaille = scanner.nextLine();
                    if (charTaille.equals("a") || charTaille.equals("A")) {
                        generator.generateFor16();
                        try {
                            aes256.init();
                            String encryptedKey = aes256.encrypt(generator.generateFor16());
                            String decryptedKey = aes256.decrypt(encryptedKey);
                            System.err.println("Your password : " + decryptedKey);
                            Save_Load.add(map, name, encryptedKey);
                            System.out.println("Your password has been successfully saved in the system");
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
                            System.err.println("Your password : " + decryptedKey);
                            Save_Load.add(map, name, encryptedKey);
                            System.out.println("Your password has been successfully saved in the system");
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
                    System.out.println("Enter the platform whose password you want to know : ");
                    String name = scanner.nextLine();

                    Save_Load.readFile(name);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (islem.equals("3")){
                System.out.println("Exiting the program...");
                break;
            }
            else {
                System.out.println("Please enter a valid transaction");
            }
        }

    }

}
