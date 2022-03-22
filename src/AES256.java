import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.util.Base64;

public class AES256 implements Serializable {
    private SecretKey key;
    private final int KEY_SIZE = 256;
    private final int T_LEN = 128;
    private byte[] iv;

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

    private void export(){
        System.err.println("SecretKey : " + encode(key.getEncoded()));
        System.err.println("iv : " + encode(iv));
    }

    public static void main(String[] args) {

        try {
            Generator generator = new Generator();
            generator.generate();
            String asd = generator.getPassword();


            AES256 aes256 = new AES256();
            aes256.init();
            String encryptedKey = aes256.encrypt(asd);
            String decryptedKey = aes256.decrypt(encryptedKey);

            System.err.println("Encrypted Key : " + encryptedKey);
            System.err.println("Decrypted Key : " + decryptedKey);
            aes256.export();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
