package projetFilRouge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetFilRouge.model.Pari;

@Repository
public interface IPariRepository extends JpaRepository <Pari,Long>{

}
