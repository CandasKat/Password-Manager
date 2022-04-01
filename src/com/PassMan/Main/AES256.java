package com.PassMan.Main;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;

public class AES256 implements Serializable {
    private static SecretKey key;
    private final int KEY_SIZE = 256;
    private final int T_LEN = 128;
    private static byte[] iv;

    public void init() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        key = generator.generateKey();
    }

    public void initFromStrings(String secretKey, String iv){
        key = new SecretKeySpec(decode(secretKey), "AES");
        this.iv = decode(iv);
    }

    public String encrypt(String strEncrypt) throws Exception {
        byte[] keyInBytes = strEncrypt.getBytes();
        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE,key);
        iv = encryptionCipher.getIV();
        byte[] encryptedBytes = encryptionCipher.doFinal(keyInBytes);
        return encode(encryptedBytes);

    }

    public String decrypt(String encryptedKey) throws Exception{
        byte[] keyInBytes = decode(encryptedKey);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, iv);
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(keyInBytes);
        return new String(decryptedBytes);
    }

    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

    public Object export(HashMap<String, String[]> map, String name, String encryptedKey){

        String[] keys = {encryptedKey, encode(iv), encode(key.getEncoded())};
        map.put(name, keys);

        return map;
    }


}
