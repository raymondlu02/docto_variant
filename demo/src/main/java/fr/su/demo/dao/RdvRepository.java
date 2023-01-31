package fr.su.demo.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.su.demo.entities.Rdv;

public interface RdvRepository extends JpaRepository<Rdv, Long> {
    @Query("SELECT u FROM Rdv u WHERE u.pro_id = ?1 and u.date = ?2")
    public List<Rdv> findRdvByDesignation(long pro_id, Date date);

    @Query("SELECT u FROM Rdv u WHERE u.pro_id = ?1 or u.user_id = ?1")
    public List<Rdv> findRdvByProId(long pro_id);

    @Query("SELECT u FROM Rdv u WHERE u.user_id = ?1")
    public List<Rdv> findRdvByUserId(long user_id);

}


