package projetFilRouge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.EquipeMatch;


@Repository
public interface IEquipeMatchRepository extends JpaRepository<EquipeMatch,Long> {

	
	List<EquipeMatch> findAllByEquipe(@Param("id") Long idEquipe);

	List<EquipeMatch> findAllByMatch(@Param("id") Long idMatch);
}
