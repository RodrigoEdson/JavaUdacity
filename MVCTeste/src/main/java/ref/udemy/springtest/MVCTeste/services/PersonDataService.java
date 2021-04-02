package ref.udemy.springtest.MVCTeste.services;

import org.springframework.stereotype.Service;
import ref.udemy.springtest.MVCTeste.model.PersonDataForm;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonDataService {
    private PersonDataForm personDataForm;

    @PostConstruct
    public void postConstruct(){
        personDataForm = new PersonDataForm();
        personDataForm.setName("Rodrigo");
        personDataForm.setEmail("rodrigoedson@gmail.com");
        personDataForm.setAge(38);
        personDataForm.setCity("Santa Adélia");
    }

    public PersonDataForm getPersonDataForm() {
        return personDataForm;
    }

    public void setPersonDataForm(PersonDataForm personDataForm) {
        this.personDataForm = personDataForm;
    }
}
