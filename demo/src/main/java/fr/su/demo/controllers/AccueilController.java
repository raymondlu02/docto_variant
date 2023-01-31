package fr.su.demo.controllers;


import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.su.demo.dao.ProfessionnelRepository;
import fr.su.demo.dao.RdvRepository;
import fr.su.demo.dao.UtilisateurRepository;
import fr.su.demo.entities.Personne;
import fr.su.demo.entities.Professionnel;
import fr.su.demo.entities.Rdv;
import fr.su.demo.dao.RdvNonCoRepository;
import fr.su.demo.entities.RdvNonConnecte;

@Controller
@RequestMapping("/")
public class AccueilController {

    @Autowired
    private ProfessionnelRepository professionnelRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RdvRepository rdvRepository;

    @Autowired
    private RdvNonCoRepository rdvNonCoRepository;
    
    
    @GetMapping("/")
    public String listeProfession(Model model) {
        return "AccueilNew.html";
    }
    
    @GetMapping("/liste")
    public String listeProfession(Model model, @RequestParam(value = "nom", defaultValue = "") String nom,
                                        @RequestParam(value = "specialite", defaultValue = "") String specialite) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("------ auth : "+ authentication.getAuthorities().iterator().next().toString());
        System.out.println("------ auth : "+ authentication);
        if(authentication.getAuthorities().iterator().next().toString().equals("ROLE_PRO")){
            // ajoute dans le modele l'id avec le professionnel qui a le bon mail 
            Long id = professionnelRepository.getByMail(authentication.getName()).get(0).getId();
            model.addAttribute("id", id);
        }else{

            if (authentication.getAuthorities().iterator().next().toString().equals("ROLE_USER")){
                Long id = utilisateurRepository.getByMail(authentication.getName()).get(0).getId();
                model.addAttribute("id", id);
            }
        }

        // ya une specialite
        if(nom.isBlank() && !specialite.isBlank()){
            List<Professionnel> professionnels = professionnelRepository.getBySpecialite(specialite);
            System.out.println("get by spe");
            model.addAttribute("listProfessionnel", professionnels);
        }
        // ya un nom
        if (specialite.isBlank() && !nom.isBlank()){
            List<Professionnel> professionnels = professionnelRepository.getByNom(nom);
            model.addAttribute("listProfessionnel", professionnels);
        }

        // les 2 ont une valeur
        if (!nom.isBlank() && !specialite.isBlank()){
            List<Professionnel> professionnels = professionnelRepository.getByNomAndSpecialite(nom, specialite);
            System.out.println("get by nom et spe");
            model.addAttribute("listProfessionnel", professionnels);

        }
        // ya aucun des 2
        if (nom.isBlank() && specialite.isBlank()){
            return liste(model);
        }


        return "Accueil.html";
    }


    @GetMapping("/listeByNom")
    public String listeParNom(Model model, @RequestParam(value = "nom", defaultValue = "") String nom) {
        List<Professionnel> professionnels = professionnelRepository.getByNom(nom);
        model.addAttribute("listProfessionnel", professionnels);
        return "Accueil.html";
    }


    
    // plus utiliser
    @GetMapping("/listetlm")
    public String liste(Model model) {
        List<Professionnel> professionnels = professionnelRepository.findAll();
        model.addAttribute("listProfessionnel", professionnels);
        return "Accueil.html";
    }



    @GetMapping("/inscriptionUtilisateur") 
    public String inscriptionUtilisateur(Model model) {
        return "InscriptionUtilisateur.html";
    }

    @GetMapping("/inscriptionPro") 
    public String inscriptionPro(Model model) {
        // model.addAttribute("professionnelRepository", professionnelRepository);
        return "InscriptionPro.html";
    }

    @GetMapping("/connexion")
    public String connexion() {
        return "Connexion.html";
    }

    // recup les dispos de 1 medecin pour les montrer au client  
    @GetMapping("/disponibilite")
    public String disponibilite(Model model, @RequestParam(name = "id") String id, @RequestParam(name = "date")String date) {
        int jourMax = 30;  // on suppose qu'on peut prendre que des rdv que 30 jours en avance.


        Professionnel pro = professionnelRepository.getById(Long.parseLong(id)); 

        // ---------------------- DATE ------------------------

        LocalDate date1 = LocalDate.parse(date.replaceAll(" ", "-"));

        // si le client cherche une date qui est deja passé
        if(date1.compareTo(java.time.LocalDate.now()) <0 ){
            date1 = java.time.LocalDate.now();
        } 


        model.addAttribute("datePlus1", date1.plusDays(1).toString());
        model.addAttribute("dateMoins1", date1.minusDays(1).toString());
        model.addAttribute("dateActu", date1.toString());
        model.addAttribute("id", id);

        // pour limiter le nb de jours ( peut etre enlever au pire)
        if (date1.compareTo(java.time.LocalDate.now().plusDays(jourMax))>0 ){
            return "PasDeDispo.html";
        }
    
        //------------------------------------------------------------------

        Map<String, Integer> jourDeSemaine = new HashMap<String, Integer>();
        jourDeSemaine.put("lundi", 1);
        jourDeSemaine.put("mardi", 2);
        jourDeSemaine.put("mercredi", 3);
        jourDeSemaine.put("jeudi", 4);
        jourDeSemaine.put("vendredi", 5);
        jourDeSemaine.put("samedi", 6);
        jourDeSemaine.put("dimanche", 7);

        
        String jourDebut = pro.getJourDebut();
        String jourFin = pro.getJourFin();
        // String jourDemande = date1.getDayOfWeek().toString();
        String jourDemande=date1.format(DateTimeFormatter.ofPattern("EEEE",Locale.FRENCH));


        if (jourDeSemaine.get(jourFin)<jourDeSemaine.get(jourDebut)){
            if (!(jourDeSemaine.get(jourDemande)>= jourDeSemaine.get(jourDebut) || jourDeSemaine.get(jourFin)>=jourDeSemaine.get(jourDemande))){
                return "PasDeDispo.html"; 
            }
        }else{
            if (!(jourDeSemaine.get(jourDemande)>= jourDeSemaine.get(jourDebut) 
                    && jourDeSemaine.get(jourDemande)<=jourDeSemaine.get(jourFin))){
                return "PasDeDispo.html";
            }
        }



        // ---------------------- Comparaison avec la table Rdv ---------------------------------------
        Date dateSql = Date.valueOf(date1);
        List<Rdv> listRdv = rdvRepository.findRdvByDesignation(Long.parseLong(id), dateSql);
        List<RdvNonConnecte> listRdvNonConnectes = rdvNonCoRepository.findRdvNonConnecteByDesignation(Long.parseLong(id), dateSql);

        List<Integer> listHeureRdv = new ArrayList<Integer>();
        for (Rdv rdv : listRdv){
            listHeureRdv.add(Integer.parseInt(rdv.getHeure()));
        }

        for (RdvNonConnecte rdv : listRdvNonConnectes){
            listHeureRdv.add(Integer.parseInt(rdv.getHeure()));
        }

        // -------------------------INITIALISATION -------------------------------
       
           
        List<Integer> listHeure = new ArrayList<Integer>();
        for (int i = 0 ; i< pro.lenght(); i++){
            if (!listHeureRdv.contains(Integer.parseInt(pro.getHoraireDebut())+i)){
                listHeure.add(Integer.parseInt(pro.getHoraireDebut())+i);// horaire de debut de taff + i (qui est l'heure de decalage)
            }
        }
        
        // -------------------- Renvoie la liste de dispo 

        

        model.addAttribute("listHeure", listHeure);
        // model.addAttribute("user_id", user_id );

        return "Disponibilite.html";
    }

    // 


    @GetMapping("/disponibilite/prendreRDV")
    public String prendreRDV(Model model, @RequestParam(name = "pro_id")  String pro_id, 
                    @RequestParam(name = "nom") String nom, @RequestParam(name = "prenom") String prenom,
                    @RequestParam(name = "date") String date,@RequestParam(name = "heure") String heure) {
        // convertir en date en Date
        LocalDate date1 = LocalDate.parse(date.replaceAll(" ", "-"));
        Date dateSql = java.sql.Date.valueOf(date1);


        Professionnel pro = professionnelRepository.getById(Long.parseLong(pro_id));


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long id = -1;
        Personne personne =null; 

        if (authentication.getAuthorities().iterator().next().toString().equals("ROLE_USER")){
            id =            utilisateurRepository.getByMail(authentication.getName()).get(0).getId();
            personne =      utilisateurRepository.getById(id);
        }
        if (authentication.getAuthorities().iterator().next().toString().equals("ROLE_PRO")){
            id =            professionnelRepository.getByMail(authentication.getName()).get(0).getId();      
            personne =      professionnelRepository.getById(id);
        }

        Rdv rdv = new Rdv(dateSql, heure, Long.parseLong(pro_id), id, personne , pro);
        rdvRepository.save(rdv);

        RdvNonConnecte rdvnonco = new RdvNonConnecte(dateSql, heure, Long.parseLong(pro_id), pro, nom, prenom );
        rdvNonCoRepository.save(rdvnonco);
        


        List<Professionnel> professionnels = professionnelRepository.findAll();
        model.addAttribute("listProfessionnel", professionnels);       

        return "redirect:/";
    }


    @GetMapping("/disponibilite/prendreRDVNonCo")
    public String prendreRDVNonCoFormulaire(Model model, @RequestParam(name = "pro_id")  String pro_id, 
                            @RequestParam(name = "date") String date,@RequestParam(name = "heure") String heure) {

        model.addAttribute("pro_id", pro_id);
        model.addAttribute("date", date);
        model.addAttribute("heure", heure);

        return "RdvNonConnecte.html";
    }


    // n'est plus utilisé
    @GetMapping("/delete")
    public String delete(Model model, @RequestParam(value = "id", defaultValue = "") String id) {

        professionnelRepository.deleteById(Long.parseLong(id));
         
        // faudra aussi supprimer dans la table rdv


        return "redirect:/";
    }

}
