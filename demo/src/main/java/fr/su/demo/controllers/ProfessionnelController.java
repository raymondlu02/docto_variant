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

import fr.su.demo.dao.ProfessionnelRepository;
import fr.su.demo.dao.RdvNonCoRepository;
import fr.su.demo.dao.RdvRepository;
import fr.su.demo.entities.Professionnel;
import fr.su.demo.entities.Rdv;
import fr.su.demo.entities.RdvNonConnecte;

@Controller
@RequestMapping("professionnel")
public class ProfessionnelController {
    @Autowired
    private ProfessionnelRepository professionnelRepository;
    
    @Autowired
    private RdvRepository rdvRepository;

    @Autowired
    private RdvNonCoRepository rdvNonCoRepository;

    

    @GetMapping("/liste")
    public String liste(Model model, @RequestParam(name ="nom", defaultValue="") String nom){
        // List<Produit> produits = produitRepository.findProditByDesignation("%"+designation+"%");
        List<Professionnel> professionnel = professionnelRepository.getByNom(nom);
        model.addAttribute ("listProfessionnel", professionnel);
        return "recherchePro.html";
    }   



    @GetMapping("/profilPro")
    public String rdvPro(Model model ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id = professionnelRepository.getByMail(authentication.getName()).get(0).getId();
        model.addAttribute("id", id);


        // CustomUser customUser = (CustomUser)authentication.getPrincipal();
        // int userId = customUser.getUserId();

        System.out.println("------------------------"+authentication.getName()+id);
        //System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId()); 
        
        // Professionnel professionnel = professionnelRepository.getById(id);
        List<Rdv> myListRdv = rdvRepository.findRdvByProId(id);
        // System.out.println("----------------------------"+myListRdv.get(0));    
        for (Rdv r : myListRdv){
            // convertie la date dans le bon format 
            String sfd =new SimpleDateFormat("yyyy-MM-dd").format((r.getDate())); 
            r.setDate(java.sql.Date.valueOf(sfd));
        }

        List<RdvNonConnecte> myListRdvNonCo = rdvNonCoRepository.findRdvNonConnecteByProId(id);
        for (RdvNonConnecte r1 : myListRdvNonCo){
            // convertie la date dans le bon format 
            String sfd =new SimpleDateFormat("yyyy-MM-dd").format((r1.getDate())); 
            r1.setDate(java.sql.Date.valueOf(sfd));
        }


        model.addAttribute("pro_id",id);
        model.addAttribute("myListRdv",myListRdv);
        model.addAttribute("myListRdvNonCo",myListRdvNonCo);

        return "vuProRdv.html";
    }

    // c'est l'affichage de la page 
    @GetMapping("/profilPro/ChangeCoordPro")
    public String CoordPro(Model model ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long id = professionnelRepository.getByMail(authentication.getName()).get(0).getId();
        model.addAttribute("id", id);
        
        Professionnel professionnel = professionnelRepository.getById(id);

        model.addAttribute("professionnel",professionnel);
        return "ChangeCoordPro.html";
    }

    // c'est quand on est sur la page et qu'on modifie les coordonnees
    @GetMapping("/profilPro/ModfierCoord")
    public String ModifCoordPro(Model model , 
        @RequestParam(value = "nom") String nom,
        @RequestParam(value = "prenom") String prenom,@RequestParam(value = "adresse") String adresse,
        @RequestParam(value = "horaireDebut") String horaireDebut,@RequestParam(value = "horaireFin") String horaireFin,
        @RequestParam(value = "jourDebut") String jourDebut,@RequestParam(value = "jourFin") String jourFin,
        @RequestParam(value = "telephone") String telephone
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id = professionnelRepository.getByMail(authentication.getName()).get(0).getId();
        model.addAttribute("id", id);

        professionnelRepository.updateById(id,nom,prenom, adresse,horaireDebut,horaireFin,jourDebut,jourFin, telephone);



        List<Rdv> myListRdv = rdvRepository.findRdvByProId(id);
        for (Rdv r : myListRdv){
            // convertie la date dans le bon format 
            String sfd =new SimpleDateFormat("yyyy-MM-dd").format((r.getDate())); 
            r.setDate(java.sql.Date.valueOf(sfd));
        }

        List<RdvNonConnecte> myListRdvNonCo = rdvNonCoRepository.findRdvNonConnecteByProId(id);
        for (RdvNonConnecte r1 : myListRdvNonCo){
            // convertie la date dans le bon format 
            String sfd =new SimpleDateFormat("yyyy-MM-dd").format((r1.getDate())); 
            r1.setDate(java.sql.Date.valueOf(sfd));
        }
        

        model.addAttribute("pro_id",id);
        model.addAttribute("myListRdv",myListRdv);
        model.addAttribute("myListRdvNonCo",myListRdvNonCo);

        return "vuProRdv.html";
    }



    @GetMapping("/profilPro/supprimerRdv")
    public String CoordPro(Model model ,  @RequestParam(value = "rdv_id") String rdv_id) {
        // Professionnel professionnel = professionnelRepository.getById(Long.parseLong(pro_id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long pro_id = professionnelRepository.getByMail(authentication.getName()).get(0).getId();
        model.addAttribute("id", pro_id);

        rdvRepository.deleteById(Long.parseLong(rdv_id));

        List<Rdv> myListRdv = rdvRepository.findRdvByProId(pro_id);

        for (Rdv r : myListRdv){
            // convertie la date dans le bon format 
            String sfd =new SimpleDateFormat("yyyy-MM-dd").format((r.getDate())); 
            r.setDate(java.sql.Date.valueOf(sfd));
        }


        rdvNonCoRepository.deleteById(Long.parseLong(rdv_id));

        List<RdvNonConnecte> myListRdvNonCo = rdvNonCoRepository.findRdvNonConnecteByProId(pro_id);

        for (RdvNonConnecte r : myListRdvNonCo){
            // convertie la date dans le bon format 
            String sfd =new SimpleDateFormat("yyyy-MM-dd").format((r.getDate())); 
            r.setDate(java.sql.Date.valueOf(sfd));
        }


        model.addAttribute("pro_id",pro_id);
        model.addAttribute("myListRdv",myListRdv);        
        model.addAttribute("myListRdvNonCo",myListRdvNonCo);        
        return "vuProRdv.html";
    }

    


}


