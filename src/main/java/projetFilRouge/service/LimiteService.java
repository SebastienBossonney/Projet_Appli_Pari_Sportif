package projetFilRouge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetFilRouge.model.Limite;
import projetFilRouge.repository.ILimiteRepository;
import projetFilRouge.repository.IUtilisateurRepository;

@Service(value = "limiteService")
public class LimiteService {

	@Autowired
	private ILimiteRepository limiteRepository;

	@Autowired
	private IUtilisateurRepository utilisateurRepository;

	// Retourne une limite(id) selon utilisateur(id)
	public Optional<Limite> getOneLimitByUser(Long utilisateurId) {
		return limiteRepository.findLimitByUtilisateurId(utilisateurId);
	}

	// Enregistre une limite selon son utilisateur(id)
	public Optional<Limite> saveLimitByUser(Long utilisateurId, Limite limite) {
		return utilisateurRepository.findById(utilisateurId).map(utilisateur -> {
			limite.setUtilisateur(utilisateur);
			return limiteRepository.save(limite);

		});
	}

	// met a jour une limite selon son utilisateur(id)
	public Optional<Limite> editOneLimitByUser(Long utilisateurId, Limite limite) {
		return limiteRepository.findById(utilisateurId).map(limiteToUpdate -> {
			limiteToUpdate.setValeur(limite.getValeur());
			limiteToUpdate.setDuree(limite.getDuree());
			return limiteRepository.save(limiteToUpdate);
		});
	}

	// supprimer une limite selon son utilisateur(id)
	public Optional<?> deleteOneLimitByUser(Long utilisateurId) {
		return limiteRepository.findById(utilisateurId).map(limiteToDelete -> {
			limiteRepository.delete(limiteToDelete);
			return "DELETED";
		});
	}

	public List<Limite> getAllLimitByUserId(Long utilisateurId) {
		return limiteRepository.findAllByUtilisateurId(utilisateurId);
	}

	public Optional<Limite> getLimiteById(Long limiteId) {

		return limiteRepository.findById(limiteId);

	}
}
