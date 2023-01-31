package fr.su.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Professionnel")
public class Professionnel extends Personne {

    // on suppose que les rdv durent tous 1h

    @Column(name = "horaireDebut", nullable = false, length = 150)
    public String horaireDebut;
    @Column(name = "horaireFin", nullable = false, length = 150)
    public String horaireFin;

    //lundi
    @Column(name = "jourDebut", nullable = false, length = 150)
    public String jourDebut;

    //vendredi
    @Column(name = "jourFin", nullable = false, length = 150)
    public String jourFin;

    @Column(name = "specialite", nullable = false, length = 150)
    public String specialite;
  


    public Professionnel(String nom, String prenom, String adresse, String horaireD, String horaireF, String telephone,
            String mail, String jourDebut, String jourFin, String password, String role, String specialite) {
        super(nom, prenom, adresse, telephone, mail,password,role);

        horaireDebut = horaireD;
        horaireFin = horaireF; // TODO y'a pas de jour

        this.jourDebut = jourDebut;
        this.jourFin = jourFin; 

        this.specialite = specialite;

    }

    public String getSpecialite() {
        return specialite;
    }


    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getHoraireDebut() {
        return horaireDebut;
    }

    public void setHoraireDebut(String horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public String getHoraireFin() {
        return horaireFin;
    }

    public void setHoraireFin(String horaireFin) {
        this.horaireFin = horaireFin;
    }

    public String getJourDebut() {
        return jourDebut;
    }

    public void setJourDebut(String jourDebut) {
        this.jourDebut = jourDebut;
    }

    public String getJourFin() {
        return jourFin;
    }

    public void setJourFin(String jourFin) {
        this.jourFin = jourFin;
    }


    public Professionnel() {
        super();
    }

    public void addDispo(int heure) {
       
    }

    public void RemoveDispo(int heure) {
        
    }

    public int lenght() {
        return Integer.parseInt(horaireFin) - Integer.parseInt(horaireDebut);
    }

}
