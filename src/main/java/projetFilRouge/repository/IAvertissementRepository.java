package projetFilRouge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Avertissement;

@Repository
public interface IAvertissementRepository extends JpaRepository<Avertissement, Long> {

}
