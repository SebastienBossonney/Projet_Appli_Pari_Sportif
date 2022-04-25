package projetFilRouge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Resultat;

@Repository
public interface IResultatRepository extends JpaRepository<Resultat,Long>{

	List<Resultat> findByMatchId(Long matchId);

	Optional<Resultat> findByIdAndMatchId(Long resultatId, Long matchId);

}
