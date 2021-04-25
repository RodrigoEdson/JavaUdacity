package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int createCredential (Credential credential){

       credential = encryptCredential(credential);

        return credentialMapper.insert(credential);
    }

    public int updateCredential(Credential credential){

        credential = encryptCredential(credential);

        return credentialMapper.update(credential);
    }

    public List<Credential> getCredentialsByUserID (Integer userId) {
        return credentialMapper.getCredentialsByUserId(userId);
    }

    public void deleteCredential(Integer crecentialID) {
        credentialMapper.delete(crecentialID);
    }


    public Credential getCredentialById(Integer userId, int credentialId) {
        Credential credential = credentialMapper.getCredentialByCredentialId(userId, credentialId);

        credential = decryptCredential(credential);

        return credential;
    }

    private Credential decryptCredential(Credential credential) {
        //decrypt the password
        String encryptedPassword = credential.getPassword();
        String key = credential.getKey();
        String plainTextPassword = encryptionService.decryptValue(encryptedPassword, key);

        //update the object
        credential.setPassword(plainTextPassword);

        return credential;
    }

    private Credential encryptCredential(Credential credential){
        //Encrypt the password
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        //update object
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);

        return credential;
    }

}
