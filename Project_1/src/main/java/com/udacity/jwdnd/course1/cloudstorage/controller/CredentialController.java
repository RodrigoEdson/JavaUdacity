package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @PostMapping
    public String insertUpdateCredential(@ModelAttribute Credential credential, Authentication auth, RedirectAttributes redirectAttributes){
        User user = (User) auth.getDetails();

        //Encrypt the password
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        //update object
        credential.setUserId(user.getUserId());
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);

        //If ID exists then its an update action
        if (credential.getCredentialId() == null) {
            this.credentialService.createCredential(credential);
        }else{
            this.credentialService.updateCredential(credential);
        }

        List<Credential> credentialsList = this.credentialService.getCredentialsByUserID(user.getUserId());
        redirectAttributes.addFlashAttribute("credentialsList", credentialsList);
        redirectAttributes.addFlashAttribute("activeTab", "credentials");
        return "redirect:/home";
    }
}
