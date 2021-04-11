package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public int createCredential (Credential credential){
        return credentialMapper.insert(credential);
    }

    public int updateCredential(Credential credential){
        return credentialMapper.update(credential);
    }

    public List<Credential> getCredentialsByUserID (Integer userId) {
        return credentialMapper.getCredentialByUserId(userId);
    }
}
