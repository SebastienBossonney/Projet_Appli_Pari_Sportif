package projetFilRouge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetFilRouge.dto.PariDto;
import projetFilRouge.model.Cote;
import projetFilRouge.model.Pari;
import projetFilRouge.repository.ICoteRepository;
import projetFilRouge.repository.IPariRepository;
import projetFilRouge.repository.IUtilisateurRepository;

@Service(value = "pariService")
public class PariService {

	@Autowired
	private IPariRepository pariRepository;

	@Autowired
	private IUtilisateurRepository utilisateurRepository;

	@Autowired
	private ICoteRepository coteRepository;

	public List<Pari> getPariByUtilisateur(Long utilisateurId) {

		return pariRepository.findByUtilisateurId(utilisateurId);
	}

	public Optional<Pari> getOnePariByUtilisateur(Long pariId, Long utilisateurId) {
		return pariRepository.findByIdAndUtilisateurId(pariId, utilisateurId);
	}

	public Optional<Pari> savePariByUtilisateur(Long utilisateurId, Pari pari) {

		return utilisateurRepository.findById(utilisateurId).map(utilisateur -> {
			pari.setUtilisateur(utilisateur);

			// Cote cote = coteRepository.findById(pari.getCote());

			Pari pariResult = pariRepository.save(pari);

			return pariResult;
		});
	}

	public Optional<Pari> editOnePari(Long pariId, Long utilisateurId, PariDto paridto, Cote cote) {
		return pariRepository.findByIdAndUtilisateurId(pariId, utilisateurId).map(pariToUpdate -> {

			pariToUpdate.setMontantJoue(paridto.getMontantJoue());
			pariToUpdate.setDatePari(paridto.getDatePari());
			pariToUpdate.setHeurePari(paridto.getHeurePari());
			pariToUpdate.setMontantResultat(paridto.getMontantResultat());
			pariToUpdate.setResultat(paridto.getResultat());
			pariToUpdate.setCote(cote);
			pariToUpdate = pariRepository.save(pariToUpdate);
			// matchToUpdate.setEquipeMatchs(equipeMatchService.updateEquipesToMatch(matchToUpdate,matchdto.getEquipes()));

			return pariToUpdate;
		});
	}

	public Optional<?> deleteOnePariByUtilisateur(Long pariId, Long utilisateurId) {
		return pariRepository.findByIdAndUtilisateurId(pariId, utilisateurId).map(pariToDelete -> {

			pariRepository.delete(pariToDelete);

			return "DELETED";
		});
	}

}
