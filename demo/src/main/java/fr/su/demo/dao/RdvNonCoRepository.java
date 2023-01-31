package fr.su.demo.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.su.demo.entities.RdvNonConnecte;

public interface RdvNonCoRepository extends JpaRepository<RdvNonConnecte, Long> {
    @Query("SELECT u FROM RdvNonConnecte u WHERE u.pro_id = ?1 and u.date = ?2")
    public List<RdvNonConnecte> findRdvNonConnecteByDesignation(long pro_id, Date date);

    @Query("SELECT u FROM RdvNonConnecte u WHERE u.pro_id = ?1 ")
    public List<RdvNonConnecte> findRdvNonConnecteByProId(long pro_id);

    @Query("SELECT u FROM RdvNonConnecte u WHERE u.nom = ?1 and u.prenom = ?2")
    public List<RdvNonConnecte> findRdvNonConnecteByUserNomAndPrenom(String nom, String prenom);


}


