package projetFilRouge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Limite;

@Repository
public interface ILimiteRepository extends JpaRepository<Limite, Long> {


	List<Limite> findAllByUtilisateurId(Long utilisateurId);

	Optional<Limite> findLimitByUtilisateurId(Long utilisateurId);
	
	

}
