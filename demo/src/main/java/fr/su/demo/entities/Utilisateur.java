package fr.su.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur extends Personne {

    public Utilisateur(String nom, String prenom, String adresse, String telephone, String mail, String password, String role) {
        super(nom, prenom, adresse, telephone, mail, password, role);
    }
    
    public Utilisateur() {
        super();
    }

    

}
