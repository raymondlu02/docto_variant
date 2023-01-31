package fr.su.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;


@Entity
// @Table(name = "rdv")
public class RdvNonConnecte {
    
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "pro_id", nullable = false, length = 150)
    private long pro_id;

    @Column(name = "nom", nullable = false, length = 150)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 150)
    private String prenom;

    @Column(name = "date", nullable = false, length = 150)
    public Date date;

    @Column(name = "heure", nullable = false, length = 150)
    public String heure;



    @ManyToOne  
    @JoinColumn( name="idPro" )
    public Professionnel pro;

    public Professionnel getPro() {
        return pro;
    }
    public void setPro(Professionnel pro) {
        this.pro = pro;
    }
    public RdvNonConnecte(Date date,String heure, long pro_id, Professionnel pro, String nom, String prenom){
        this.date = date;
        this.heure = heure;
        this.pro_id = pro_id;
        this.nom = nom; 
        this.prenom = prenom;
        this.pro=pro;

    }

    public RdvNonConnecte(){
        
    }


    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public String getHeure() {
        return heure;
    }


    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public long getPro_id() {
        return pro_id;
    }
    public void setPro_id(long pro_id) {
        this.pro_id = pro_id;
    }




}
