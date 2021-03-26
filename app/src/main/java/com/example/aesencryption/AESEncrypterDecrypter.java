package com.example.aesencryption;

/**
 * Created by RAHUL on 23-Dec-2019.
 */
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encrypt/Decrypt data using symmetric encryption and key
 * @author Rahul Chandore
 *
 */
public class AESEncrypterDecrypter {
    private static final byte[] IV = "1234567890123456".getBytes();
    private static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";
    private static final String AES = "AES";

    /**
     * Encrypt data using AES/CBC/PKCS5Padding. IV appears first in the returned data array followed with encrypted request.
     * @param data to be encrypted
     * @param key used for encryption
     * @return encrypted result
     */
    public byte[] encrypt(String data, byte[] key) {
        byte[] encryptedValue = null;
        try {
            SecretKeySpec secretKeySpec=new SecretKeySpec(key,AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec, new IvParameterSpec(IV));
            byte[] dataArr = data.getBytes();
            byte[] ivAndData = new byte[IV.length + dataArr.length];
            System.arraycopy(IV, 0, ivAndData, 0, IV.length);
            System.arraycopy(dataArr, 0, ivAndData, IV.length,dataArr.length);
            encryptedValue = cipher.doFinal(ivAndData);
        }
        catch(NoSuchAlgorithmException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        catch(NoSuchPaddingException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        catch(IllegalBlockSizeException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        catch(BadPaddingException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        catch(InvalidKeyException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        catch(InvalidAlgorithmParameterException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        return encryptedValue;
    }

    /**
     * Decrypt encrypted data using AES/CBC/PKCS5Padding and a secret key. IV should appear first in the byte array argument followed with encrypted value of response
     * @param data to be decrypted
     * @param key
     * @return decrypted value
     */
    public byte[] decrypt(byte[] encrypted, byte[] key) {
        byte[] decryptedData = null;
        try {
            SecretKeySpec secretKeySpec=new SecretKeySpec(key,AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec, new IvParameterSpec(IV));
            byte[] decryptedDataWithIV = cipher.doFinal(encrypted);
            decryptedData = new byte[decryptedDataWithIV.length-IV.length];
            System.arraycopy(decryptedDataWithIV, IV.length, decryptedData, 0, decryptedData.length);
            //System.out.println(new String(decryptedData));
        }
        catch(NoSuchAlgorithmException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        catch(NoSuchPaddingException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        catch(IllegalBlockSizeException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        catch(BadPaddingException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        catch(InvalidKeyException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        catch(InvalidAlgorithmParameterException exp) {
            //TODO handle exception
            exp.printStackTrace();
        }
        return decryptedData;
    }
}
