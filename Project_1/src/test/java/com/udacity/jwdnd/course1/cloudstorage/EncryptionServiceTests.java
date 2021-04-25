package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptionServiceTests {

    private static final String myPassword = "My Password";
    private static  final String myKey = "My Key 16 bytes.";
    private static String encodedKey;
    private static String encodedPass;
    private static EncryptionService encryptionService;

    @BeforeAll
    public static void setUpAll(){
        encodedKey = Base64.getEncoder().encodeToString(myKey.getBytes());
        encodedPass = "HDluSrLTwHxOK+5W7lFS7Q==";
        encryptionService = new EncryptionService();
    }

    @Test
    public void testEncrypt(){
        String encryptedPassword = encryptionService.encryptValue(myPassword, encodedKey);
        assertEquals(encryptedPassword,encodedPass);
    }

    @Test
    public void testDecrypt(){
        String decryptedPassword = encryptionService.decryptValue(encodedPass, encodedKey);
        assertEquals(decryptedPassword, myPassword);
    }

}
