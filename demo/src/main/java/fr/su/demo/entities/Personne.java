package fr.su.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nom", nullable = false, length = 150)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 150)
    private String prenom;

    @Column(name = "adresse", nullable = false, length = 150)
    private String adresse;

    @Column(name = "mail", nullable = false, length = 150)
    private String mail;

    @Column(name = "telephone", nullable = false, length = 150)
    private String telephone;

    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @Column(name = "role", nullable = false, length = 150)
    private String role;

    public Personne(String nom, String prenom, String adresse, String telephone, String mail, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail; // TODO verif format mail
        this.password = password;
        this.role = role;
    }

    public Personne() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getRoles() {
        List<String> Lrole = new ArrayList<String>();
        Lrole.add(this.role);
        return Lrole;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}