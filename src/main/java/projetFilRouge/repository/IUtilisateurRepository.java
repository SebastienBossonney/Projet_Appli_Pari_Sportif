package projetFilRouge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Utilisateur;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
