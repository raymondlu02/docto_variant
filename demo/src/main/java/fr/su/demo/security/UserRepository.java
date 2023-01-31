package fr.su.demo.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.su.demo.entities.Personne;
import fr.su.demo.entities.Professionnel; 

@Repository public interface UserRepository extends JpaRepository<Personne, String> { 
   public Personne findUserByMail(String mail); 
}
