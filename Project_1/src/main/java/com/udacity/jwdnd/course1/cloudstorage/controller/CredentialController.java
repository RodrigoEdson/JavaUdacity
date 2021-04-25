package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping
    public String insertUpdateCredential(@ModelAttribute Credential credential, Authentication auth, RedirectAttributes redirectAttributes){
        User user = (User) auth.getDetails();
        credential.setUserId(user.getUserId());

        //If ID exists then its an update action
        if (credential.getCredentialId() == null) {
            this.credentialService.createCredential(credential);
        }else{
            this.credentialService.updateCredential(credential);
        }

        redirectAttributes.addFlashAttribute("activeTab", "credentials");
        return "redirect:/home";
    }

    @PostMapping("/delete")
    public String deleteCredential(RedirectAttributes redirectAttributes, @ModelAttribute CredentialDTO credentialDTO){

        credentialService.deleteCredential(credentialDTO.getCredentialId());

        redirectAttributes.addFlashAttribute("activeTab", "credentials");

        return "redirect:/home";
    }

    @GetMapping("getCredential/{credentialId}")
    @ResponseBody
    public Credential getCredentialById(@PathVariable(name="credentialId") String credentialId, Authentication auth){
        User user = (User) auth.getDetails();
        Integer userId = user.getUserId();
        return credentialService.getCredentialById(userId,  Integer.parseInt(credentialId));
    }
}
