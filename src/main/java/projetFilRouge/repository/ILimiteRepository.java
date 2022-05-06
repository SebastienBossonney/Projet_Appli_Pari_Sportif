package projetFilRouge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Limite;

@Repository
public interface ILimiteRepository extends JpaRepository<Limite, Long> {

	Optional<Limite> findByUtilisateurId(Long utilisateurId);

}
