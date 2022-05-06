package projetFilRouge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Cote;

@Repository
public interface ICoteRepository extends JpaRepository<Cote, Long> {

	List<Cote> findByMatchId(Long matchId);

	Optional<Cote> findByIdAndMatchId(Long id, Long matchId);
}
