package projetFilRouge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Match;


@Repository
public interface IMatchRepository extends JpaRepository <Match,Long> {
	
	List<Match> findBySportId(Long sportId);
	
	Optional<Match> findByIdAndSportId(Long id, Long sportId);

}
