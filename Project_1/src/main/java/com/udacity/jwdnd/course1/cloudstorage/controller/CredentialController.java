package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;

    public CredentialController(CredentialService createService) {
        this.credentialService = createService;
    }

    @PostMapping
    public String insertUpdateCredential(@ModelAttribute Credential credential, Authentication auth, RedirectAttributes redirectAttributes){
        User user = (User) auth.getDetails();
        credential.setUserId(user.getUserId());

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
