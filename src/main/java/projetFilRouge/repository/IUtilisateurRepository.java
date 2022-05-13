package projetFilRouge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Utilisateur;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	Optional<Utilisateur> findByIdentifiantAndMotDePasse(String identifiant, String password);

	Optional<Utilisateur> findByEmail(String email);



}
