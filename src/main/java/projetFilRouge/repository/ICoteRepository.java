package projetFilRouge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Cote;

@Repository
public interface ICoteRepository extends JpaRepository<Cote,Long> {

}
