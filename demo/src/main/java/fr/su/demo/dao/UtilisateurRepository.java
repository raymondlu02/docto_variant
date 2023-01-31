package fr.su.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.su.demo.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // @Query("insert into utilisateur VALUES ''")
    // public <> insertUser(@Param("x" )String designation);

    // public List<Produit> getByDesignation(String designation);


    public List<Utilisateur> getByNom(String nom);

    public List<Utilisateur> findAll();

    public Utilisateur getById(Long i);

    public List<Utilisateur> getByMail(String mail);

    // public void updateById(long parseLong, String nom, String prenom, String adresse, String horaireDebut,
    //         String horaireFin, String jourDebut, String jourFin, String telephone);

    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Utilisateur p set p.nom = ?2, p.prenom = ?3, p.adresse = ?4, p.telephone = ?5 WHERE p.id = ?1")
    public void updateById(long user_id, String nom,String prenom,String adresse, String telephone);
}
