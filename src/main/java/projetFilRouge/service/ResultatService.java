package projetFilRouge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetFilRouge.model.Resultat;
import projetFilRouge.repository.IMatchRepository;
import projetFilRouge.repository.IResultatRepository;

@Service(value = "resultatService")
public class ResultatService {

	@Autowired
	private IMatchRepository matchRepository;

	@Autowired
	private IResultatRepository resultatRepository;

	public Resultat getResultatsByMatch(Long matchId) {
		return resultatRepository.findByMatchId(matchId);
	}

	public Resultat getResultatById(Long resultatId) {
		return resultatRepository.findById(resultatId).get();
	}

	public Optional<Resultat> getOneResultatByMatch(Long resultatId, Long matchId) {
		return resultatRepository.findByIdAndMatchId(resultatId, matchId);
	}

	public Optional<Resultat> saveResultatByMatch(Long matchId, Resultat resultat) {
		return matchRepository.findById(matchId).map(match -> {
			resultat.setMatch(match);
			return resultatRepository.save(resultat);

//				Resultat resultat1 = resultatRepository.save(resultat);
//				ResultatDto resultatPourAfficher = new ResultatDto();
//				resultatPourAfficher.setEquipe1(resultat1.getEquipe1());
//				resultatPourAfficher.setScoreEquipe1(resultat1.getScoreEquipe1());
//				resultatPourAfficher.setEquipe2(resultat1.getEquipe2());
//				resultatPourAfficher.setScoreEquipe2(resultat1.getScoreEquipe2()) ;
//				resultatPourAfficher.setMatchNul(resultat1.isMatchNul());
//				resultatPourAfficher.setMatchId(resultat1.getMatch().getId());
//				return resultatPourAfficher;

		});
	}

	public Optional<Resultat> editOneResultatByMatch(Long resultatId, Long matchId, Resultat resultat) {
		return resultatRepository.findByIdAndMatchId(resultatId, matchId).map(resultatToUpdate -> {
			resultatToUpdate.setEquipe1(resultat.getEquipe1());
			resultatToUpdate.setScoreEquipe1(resultat.getScoreEquipe1());
			resultatToUpdate.setEquipe2(resultat.getEquipe2());
			resultatToUpdate.setScoreEquipe2(resultat.getScoreEquipe2());
			resultatToUpdate.setMatchNul(resultat.isMatchNul());
			return resultatRepository.save(resultatToUpdate);
		});
	}

	public Optional<?> deleteOneResultatByMatch(Long resultatId, Long matchId) {
		return resultatRepository.findByIdAndMatchId(resultatId, matchId).map(resultatToDelete -> {
			resultatRepository.delete(resultatToDelete);
			return "DELETED";
		});
	}

}
