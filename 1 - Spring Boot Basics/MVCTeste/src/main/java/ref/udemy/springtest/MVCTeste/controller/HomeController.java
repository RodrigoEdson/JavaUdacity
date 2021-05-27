package ref.udemy.springtest.MVCTeste.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ref.udemy.springtest.MVCTeste.model.PersonDataForm;
import ref.udemy.springtest.MVCTeste.services.PersonDataService;

import java.time.Instant;

@Controller
public class HomeController {

    private PersonDataService personDataService;

    public HomeController(PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping ("/home")
    public String getHomePage(@ModelAttribute("personData") PersonDataForm personData, Model model){
        model.addAttribute("personData",this.personDataService.getPersonDataForm());
        model.addAttribute("now", Instant.now().toString());

        return "home";
    }

    @PostMapping("/home")
    public String postHomePage(PersonDataForm personData, Model model){
        personDataService.setPersonDataForm(personData);

        model.addAttribute("personData",this.personDataService.getPersonDataForm());
        model.addAttribute("now", Instant.now().toString());

        return "home";
    }

}
