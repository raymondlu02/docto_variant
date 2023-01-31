package fr.su.demo.controllers;


import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.su.demo.dao.RdvRepository;
import fr.su.demo.dao.UtilisateurRepository;
import fr.su.demo.entities.Rdv;
import fr.su.demo.entities.Utilisateur;

@Controller
@RequestMapping("utilisateur")
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @Autowired
    private RdvRepository rdvRepository;

    @GetMapping("/liste")
    public String liste(Model model, @RequestParam(name ="nom", defaultValue="") String nom){
        // List<Produit> produits = produitRepository.findProditByDesignation("%"+designation+"%");
        List<Utilisateur> utilisateur = utilisateurRepository.getByNom(nom);
        model.addAttribute ("listUtilisateur", utilisateur);
        return "Utilisateur.html";
    }   


    // suite a l'appuie du bouton profil
    @GetMapping("/profilUser")
    public String rdvUser(Model model) {
        
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // System.out.println("-------------------------- ICI !!!!"+utilisateurRepository.getByMail(authentication.getName()));
        Long id = utilisateurRepository.getByMail(authentication.getName()).get(0).getId();
        model.addAttribute("id", id);
        
        System.out.println("---------------test----------"+id);
        // Utilisateur utilisateur = utilisateurRepository.getById(id);
        List<Rdv> myListRdv = rdvRepository.findRdvByUserId(id);
        // System.out.println("----------------------------"+myListRdv.get(0));    


        for (Rdv r : myListRdv){
            // convertie la date dans le bon format 
            String sfd =new SimpleDateFormat("yyyy-MM-dd").format((r.getDate())); 
            r.setDate(java.sql.Date.valueOf(sfd));
        }
        

        model.addAttribute("user_id",id);
        model.addAttribute("myListRdv",myListRdv);
        return "vuUserRdv.html";
    }

    // suite a l'appuie du bouton changer ses coordonnees
    @GetMapping("/profilUser/ChangeCoordPro")
    public String CoordUser(Model model ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long id = utilisateurRepository.getByMail(authentication.getName()).get(0).getId();
        model.addAttribute("id", id);
        
        Utilisateur utilisateur = utilisateurRepository.getById(id);

        model.addAttribute("utilisateur",utilisateur);
        return "ChangeCoordUser.html";
    }


    // quand on est sur la page de ses coordonnees, et qu'on clique sur le bouton pour les modif
    @GetMapping("/profilUser/ModfierCoord")
    public String ModifCoordUser(Model model , 
        @RequestParam(value = "nom") String nom,
        @RequestParam(value = "prenom") String prenom,@RequestParam(value = "adresse") String adresse,
        @RequestParam(value = "telephone") String telephone
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id = utilisateurRepository.getByMail(authentication.getName()).get(0).getId();
        model.addAttribute("id", id);
        
        utilisateurRepository.updateById(id,nom,prenom, adresse, telephone);

        // rdvRepository.updateByIdPatient(id,nom,prenom, adresse, telephone);

        List<Rdv> myListRdv = rdvRepository.findRdvByProId(id);
        for (Rdv r : myListRdv){
            // convertie la date dans le bon format 
            String sfd =new SimpleDateFormat("yyyy-MM-dd").format((r.getDate())); 
            r.setDate(java.sql.Date.valueOf(sfd));
        }
        

        model.addAttribute("user_id",id);
        model.addAttribute("myListRdv",myListRdv);
        return "vuUserRdv.html";
    }



    @GetMapping("/profilUser/supprimerRdv")
    public String CoordUser(Model model , @RequestParam(value = "rdv_id") String rdv_id) {
        // Utilisateur utilisateur = utilisateurRepository.getById(Long.parseLong(user_id));
        

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long user_id = utilisateurRepository.getByMail(authentication.getName()).get(0).getId();
        model.addAttribute("id", user_id);

        rdvRepository.deleteById(Long.parseLong(rdv_id));

        List<Rdv> myListRdv = rdvRepository.findRdvByUserId(user_id);

        for (Rdv r : myListRdv){
            // convertie la date dans le bon format 
            String sfd =new SimpleDateFormat("yyyy-MM-dd").format((r.getDate())); 
            r.setDate(java.sql.Date.valueOf(sfd));
        }



        model.addAttribute("user_id",user_id);
        model.addAttribute("myListRdv",myListRdv);        
        return "vuUserRdv.html";
    }


}


