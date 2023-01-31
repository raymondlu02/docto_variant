package fr.su.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import fr.su.demo.dao.ProfessionnelRepository;
import fr.su.demo.entities.Generaliste;
import fr.su.demo.entities.Infirmier;
import fr.su.demo.entities.Specialiste;
import fr.su.demo.dao.UtilisateurRepository;
import fr.su.demo.entities.Utilisateur;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		ProfessionnelRepository Professionnels = context.getBean(ProfessionnelRepository.class);
		
		if(Professionnels.findAll().isEmpty()){
			
			Professionnels.save( new Generaliste("dupont", "jean", 
			"11 rue du poivron", "10", "19", "01 20 39 20 70", "dup@jean.com", "lundi", "vendredi", "{noop}mdp", "ROLE_PRO"));
			
			Professionnels.save( new Generaliste("haddock", "capitaine", 
			"12 rue de la tomate", "10", "19", "01 20 39 20 71", "haddock@capitaine.fr", "lundi", "vendredi", "{noop}mdp", "ROLE_PRO"));

			Professionnels.save( new Specialiste("tournesol", "fred", 
			"13 rue de l'artichaux", "10", "19", "01 20 39 20 72", "tournesol@fred.be", "lundi", "vendredi", "{noop}mdp", "ROLE_PRO"));

			Professionnels.save( new Specialiste("tintin", "tintin", 
			"14 rue du navet", "10", "17", "01 20 39 20 73", "tintin@tintin.jp", "lundi", "vendredi", "{noop}mdp", "ROLE_PRO"));

			Professionnels.save( new Infirmier("lucke", "lucky", 
			"15 rue du haricot", "10", "19", "01 20 39 20 74", "lucke@lucky.com", "lundi", "vendredi", "{noop}mdp", "ROLE_PRO"));
			
			UtilisateurRepository utilisateurs = context.getBean(UtilisateurRepository.class);
			utilisateurs.save( new Utilisateur("vinci", "leonard", 
					"246 rue des maladie sans fin", "06 90 00 00 01", "vinci@leonar.fr", "{noop}mdp", "ROLE_USER"));

			utilisateurs.save( new Utilisateur("jourdan", "gil", 
					"11 rue de ladresse", "06 90 00 00 02", "jourdan@gil.com", "{noop}mdp", "ROLE_USER"));

			utilisateurs.save( new Utilisateur("caporal", "blutch", 
					"11 rue de ladresse", "06 90 00 00 03", "caporal@blutch.fr", "{noop}mdp", "ROLE_USER"));


		}
	
	}

}

