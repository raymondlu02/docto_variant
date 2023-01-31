package fr.su.demo.entities;

import javax.persistence.Entity;

@Entity
public class Specialiste extends Professionnel {
 
    public Specialiste(String nom, String prenom, String adresse, String horaireD, String horaireF, String telephone,
                        String mail, String jourDebut, String jourFin, String password, String role){

        super(nom, prenom, adresse, horaireD, horaireF, telephone, mail, jourDebut, jourFin,password,role, "specialiste");
    }

    public Specialiste(){}


}
