package projetFilRouge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Sport;

@Repository
public interface ISportRepository extends JpaRepository<Sport,Long> {

}
