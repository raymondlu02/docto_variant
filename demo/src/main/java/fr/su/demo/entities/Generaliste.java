package fr.su.demo.entities;

import javax.persistence.Entity;

@Entity
public class Generaliste extends Professionnel{

    public Generaliste(String nom, String prenom, String adresse, String horaireD, String horaireF, String telephone,
                        String mail, String jourDebut, String jourFin, String password, String role){
        super(nom, prenom, adresse, horaireD, horaireF, telephone, mail, jourDebut, jourFin,password,role, "generaliste");
    }

    public Generaliste(){}
    
}
