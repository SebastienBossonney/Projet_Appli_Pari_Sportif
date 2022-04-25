package projetFilRouge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Joueur;

@Repository
public interface IJoueurRepository  extends JpaRepository<Joueur,Long>{

}
