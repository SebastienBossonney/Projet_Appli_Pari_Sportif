package projetFilRouge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Equipe;

@Repository
public interface IEquipeRepository extends JpaRepository<Equipe, Long> {

	List<Equipe> findBySportId(Long sportId);

	Optional<Equipe> findByIdAndSportId(Long equipeId, Long sportId);

}
