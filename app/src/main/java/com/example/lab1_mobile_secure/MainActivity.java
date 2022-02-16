package com.example.lab1_mobile_secure;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.MasterKeys;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class MainActivity extends AppCompatActivity {

    TextView text_area;
    EditText text_field;
    Button button;
    Button button2;
    Editable text;
    int count;
    Context context;
    String fileToRead = "my_sensitive_data.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         text_area = findViewById(R.id.text_area);
         text_field = findViewById(R.id.text_field);
         button = findViewById(R.id.button);
         button2 = findViewById(R.id.button2);
         count++;
    }

    @SuppressLint("SetTextI18n")
    public void click(View view) throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String text = String.valueOf(text_field.getText());
        byte[] plaintext = text.getBytes();
        KeyGenerator keygen;
        try {
            keygen = KeyGenerator.getInstance("AES");
            keygen.init(256);
            SecretKey key = keygen.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] ciphertext = cipher.doFinal(plaintext);
            byte[] iv = cipher.getIV();
            String ciText = "Cipher text: ";
            String viText = "Initialization vector: ";
            for (int i = 0; i < ciphertext.length; i++) {
                ciText +=  ciphertext[i] + " ";
                viText +=  iv[i] + " ";
            }
            text_area.setText(ciText + "\n" + viText);

//            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
//            try {
//                String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
//                File fileEx = context.getFileStreamPath(fileToRead);
//                if (fileEx.exists()) {
//                    System.out.println("File deleted" + fileEx.delete());
//                }
//                File file = new File(context.getFilesDir(), fileToRead);
//                EncryptedFile encryptedFile = new EncryptedFile.Builder(
//                        file,
//                        context,
//                        mainKeyAlias,
//                        EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB).build();
//
//                byte[] fileContent = text_field.getText().toString().getBytes(StandardCharsets.UTF_8);
//                OutputStream outputStream = encryptedFile.openFileOutput();
//                outputStream.write(fileContent);
//                outputStream.flush();
//                outputStream.close();
//
//            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
//                e.printStackTrace();
//            } catch (GeneralSecurityException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
//        @SuppressLint("SetTextI18n")
//    public void readFile(View view) throws GeneralSecurityException, IOException {
//        text_area.setText("");
//        Context context = getApplicationContext();
//        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
//        String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
//        EncryptedFile encryptedFile = new EncryptedFile.Builder(
//                new File(context.getFilesDir(), fileToRead),
//                context,
//                mainKeyAlias,
//                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB).build();
//
//        InputStream inputStream = encryptedFile.openFileInput();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        int nextByte = inputStream.read();
//        while (nextByte != -1) {
//            byteArrayOutputStream.write(nextByte);
//            nextByte = inputStream.read();
//        }
//
//        byte[] plaintext = byteArrayOutputStream.toByteArray();
//        String text = Arrays.toString(plaintext);
//        text_area.setText(text);
//    }
}