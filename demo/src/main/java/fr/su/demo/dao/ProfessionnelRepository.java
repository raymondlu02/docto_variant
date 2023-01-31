package fr.su.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.su.demo.entities.Professionnel;

public interface ProfessionnelRepository extends JpaRepository<Professionnel, Long> {
    // @Query("select p from Produit p where p.designation like :x")
    // public List<Produit> findProditByDesignation(@Param("x" )String designation);

    public List<Professionnel> getByNom(String nom);

    public List<Professionnel> findAll();

    public Professionnel getById(Long i);

    public List<Professionnel> getBySpecialite(String specialite);

    public List<Professionnel> getByNomAndSpecialite( String nom, String specialite);

    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Professionnel p set p.nom = ?2, p.prenom = ?3, p.adresse = ?4, p.horaireDebut = ?5, p.horaireFin = ?6, p.jourDebut = ?7, p.jourFin = ?8, p.telephone = ?9 WHERE p.id = ?1")
    public void updateById(long pro_id, String nom,String prenom,String adresse,
            String horaireDebut, String HoraireFin,String jourDebut,String jourFin, String telephone);


    // retourne une liste mais en finalement ya que 1 element
    public List<Professionnel> getByMail(String mail);

}
