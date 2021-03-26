package com.example.aesencryption;
/**
 * Created by RAHUL on 23-Dec-2019.
 */
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class Java_AES_Cipher {

    private static String CIPHER_NAME = "AES/CBC/PKCS5PADDING";
    private static int CIPHER_KEY_LEN = 16; //128 bits
    private static String SecretKey = "012345Abcde";
    private static String SecretIV ="Abcde012345";
    /**
     * Encrypt data using AES Cipher (CBC) with 128 bit SecretKey
     * 
     * 
     * @param //SecretKey  - SecretKey to use should be 16 bytes long (128 bits)
     * @param //SecretIV - initialization vector
     * @param data - data to encrypt
     * @return encryptedData data in base64 encoding with SecretIV attached at end after a :
     */
    public static String encrypt(String data) {
        try {
            if (SecretKey.length() < Java_AES_Cipher.CIPHER_KEY_LEN) {
                int numPad = Java_AES_Cipher.CIPHER_KEY_LEN - SecretKey.length();

                for(int i = 0; i < numPad; i++){
                    SecretKey += "0"; //0 pad to len 16 bytes
                }

            } else if (SecretKey.length() > Java_AES_Cipher.CIPHER_KEY_LEN) {
                SecretKey = SecretKey.substring(0, CIPHER_KEY_LEN); //truncate to 16 bytes
            }


            IvParameterSpec initVector = new IvParameterSpec(SecretIV.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(SecretKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(Java_AES_Cipher.CIPHER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initVector);

            byte[] encryptedData = cipher.doFinal((data.getBytes()));

            String base64_EncryptedData = Base64.getEncoder().encodeToString(encryptedData);
            String base64_IV = Base64.getEncoder().encodeToString(SecretIV.getBytes("UTF-8"));

            return base64_EncryptedData + ":" + base64_IV;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Decrypt data using AES Cipher (CBC) with 128 bit SecretKey
     * 
     * @param //SecretKey - SecretKey to use should be 16 bytes long (128 bits)
     * @param data - encrypted data with SecretIV at the end separate by :
     * @return decrypted data string
     */

    public static String decrypt( String data) {
        try {

            String[] parts = data.split(":");

            IvParameterSpec SecretIV = new IvParameterSpec(Base64.getDecoder().decode(parts[1]));
            SecretKeySpec skeySpec = new SecretKeySpec(SecretKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(Java_AES_Cipher.CIPHER_NAME);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, SecretIV);

            byte[] decodedEncryptedData = Base64.getDecoder().decode(parts[0]);

            byte[] original = cipher.doFinal(decodedEncryptedData);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}