package com.example.aesencryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    EditText etRpcode, etEncrypt, etDecrypt;
    TextView txtSubmit, txtDecrypt;
   // MCrypt mcrypt;
    Java_AES_Cipher java_aes_cipher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();


        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java_aes_cipher = new Java_AES_Cipher();
                Log.i("@AES", "RpCode: " + etRpcode.getText().toString());
                String rp_code = etRpcode.getText().toString();
                try {
                    /* Encrypt */
                   // String encrypted = MCrypt.bytesToHex( mcrypt.encrypt(etRpcode.getText().toString()) );
                    String encrypted = java_aes_cipher.encrypt(rp_code);
                    /* Decrypt */
                   // String decrypted = new String( mcrypt.decrypt( encrypted ) );
                   String decrypted = java_aes_cipher.decrypt(etEncrypt.getText().toString());


                Log.i("@AES", "RpCode: " + etRpcode.getText().toString());
                Log.i("@AES", "Encrypted Key: " + encrypted);
                Log.i("@AES", "Decrypted Key: " + decrypted);

                etEncrypt.setText(encrypted);
                etDecrypt.setText(decrypted);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("@AES", "Exception " + e);
                }
            }
        });


    }

    private void initUI() {
        etRpcode = findViewById(R.id.etRpcode);
        etEncrypt = findViewById(R.id.etEncrypt);
        etDecrypt = findViewById(R.id.etDecrypt);
        txtSubmit = findViewById(R.id.txtSubmit);
    }


}
