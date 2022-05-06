package projetFilRouge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Pari;

@Repository
public interface IPariRepository extends JpaRepository<Pari, Long> {

	List<Pari> findByUtilisateurId(Long utilisateurId);

	Optional<Pari> findByIdAndUtilisateurId(Long id, Long utilisateurId);
}
