package projetFilRouge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Avertissement;

@Repository
public interface IAvertissementRepository extends JpaRepository<Avertissement, Long> {

	List<Avertissement> findByUtilisateurId(Long utilisateurId);

	Optional<Avertissement> findByIdAndUtilisateurId(Long avertissementId, Long utilisateurId);

}
