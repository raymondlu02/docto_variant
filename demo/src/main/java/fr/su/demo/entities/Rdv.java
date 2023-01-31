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
public class Rdv {
    
    


    @Id
    @GeneratedValue
    private Long id;

    // @ManyToOne
    // @JoinTable(name = "rdv_test",
    //             joinColumns = @JoinColumn(name = "professionnel"),
    //             inverseJoinColumns = @JoinColumn(name = "utilisateur")
    // )
    // private Utilisateur utilisateur;

    // @ManyToOne
    // @JoinTable(name = "professionnel")


    @Column(name = "pro_id", nullable = false, length = 150)
    private long pro_id;

    @Column(name = "user_id", nullable = false, length = 150)
    private long user_id;

    @Column(name = "date", nullable = false, length = 150)
    public Date date;

    @Column(name = "heure", nullable = false, length = 150)
    public String heure;

    
    @ManyToOne  
    @JoinColumn( name="idpersonne" )
    public Personne personne;

    @ManyToOne  
    @JoinColumn( name="idPro" )
    public Professionnel pro;


    public Personne getPersonne() {
        return personne;
    }
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    public Professionnel getPro() {
        return pro;
    }
    public void setPro(Professionnel pro) {
        this.pro = pro;
    }
    public Rdv(Date date,String heure, long pro_id, long user_id, 
                    Personne personne, Professionnel pro){
        this.date = date;
        this.heure = heure;
        this.pro_id = pro_id;
        this.user_id = user_id; 
        this.personne= personne;
        this.pro=pro;

    }

    public Rdv(){
        
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
    public long getUser_id() {
        return user_id;
    }
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }



}
