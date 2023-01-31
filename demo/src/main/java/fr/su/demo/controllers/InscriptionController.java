package fr.su.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.su.demo.dao.ProfessionnelRepository;
import fr.su.demo.dao.UtilisateurRepository;
import fr.su.demo.entities.Generaliste;
import fr.su.demo.entities.Infirmier;
import fr.su.demo.entities.Professionnel;
import fr.su.demo.entities.Specialiste;
import fr.su.demo.entities.Utilisateur;

@Controller
@RequestMapping("/inscription")
public class InscriptionController {

    @Autowired
    private ProfessionnelRepository professionnelRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/addUser")
    public String addUser(Model model, @RequestParam String nom,
            @RequestParam String prenom, @RequestParam String adresse, @RequestParam String telephone,
            @RequestParam String mail, @RequestParam String password) {

                List<Utilisateur> users = utilisateurRepository.getByMail(mail);
                List<Professionnel> pros = professionnelRepository.getByMail(mail);
        
                if (users.isEmpty() && pros.isEmpty()){
                    System.out.println("incription user");   
                    utilisateurRepository.save(new Utilisateur(nom, prenom, adresse, telephone,
                                            mail, "{noop}"+password, "ROLE_USER"));
                    System.out.println("FIN incription user");   

                }else{
                    model.addAttribute("nom",nom);
                    model.addAttribute("prenom",prenom);
                    model.addAttribute("adresse",adresse);
                    model.addAttribute("telephone",telephone);
                    model.addAttribute("mail",mail);
                    model.addAttribute("+password",password);
                    model.addAttribute("msgMail","le mail existe deja");
                    return "InscriptionUtilisateur.html";
                }

        return "redirect:/";
    }


    // --------------------------------Ajouter un pro

    @GetMapping("/addPro")
    public String addPro(Model model, @RequestParam String nom,
            @RequestParam String prenom,    @RequestParam String adresse,
            @RequestParam String horaireD,  @RequestParam String horaireF,
            @RequestParam String telephone, @RequestParam String mail,
            @RequestParam String jourDebut, @RequestParam String jourFin,
            @RequestParam String password,  @RequestParam String specialite
            ) {


        List<Professionnel> pros = professionnelRepository.getByMail(mail);
        List<Utilisateur> users = utilisateurRepository.getByMail(mail);



        if (users.isEmpty() && pros.isEmpty()){
            System.out.println("--------------------------------------specialitie = "+specialite);
            if (specialite.equals("generaliste"))
                professionnelRepository.save(new Generaliste(nom, prenom, adresse, horaireD, horaireF, telephone, mail, jourDebut, jourFin, "{noop}"+password, "ROLE_PRO"));
            if (specialite.equals("specialiste"))
                professionnelRepository.save(new Specialiste(nom, prenom, adresse, horaireD, horaireF, telephone, mail, jourDebut, jourFin, "{noop}"+password, "ROLE_PRO"));
            if (specialite.equals("infirmier"))
                professionnelRepository.save(new Infirmier(nom, prenom, adresse, horaireD, horaireF, telephone, mail, jourDebut, jourFin, "{noop}"+password, "ROLE_PRO"));
            
        }else{

            model.addAttribute("nom",nom);
            model.addAttribute("prenom",prenom);
            model.addAttribute("adresse",adresse);
            model.addAttribute("horaireD",horaireD);
            model.addAttribute("horaireF",horaireF);
            model.addAttribute("telephone",telephone);
            model.addAttribute("mail",mail);
            model.addAttribute("jourDebut",jourDebut);
            model.addAttribute("jourFin",jourFin);
            model.addAttribute("+password",password);
            model.addAttribute("specialite",specialite);
            model.addAttribute("msgMail","le mail existe deja");

            return "InscriptionPro.html";
        }

        return "redirect:/";
    }

    

}
