package projetFilRouge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetFilRouge.model.Avertissement;
import projetFilRouge.repository.IAvertissementRepository;
import projetFilRouge.repository.IUtilisateurRepository;


@Service(value = "avertissementServic")
public class AvertissementService{

	@Autowired
	private IAvertissementRepository avertissementRepository;
	
	@Autowired
	private IUtilisateurRepository utilisateurRepository;
	
	// Retourne une liste de avertissements selon son utilisateur(id)
	public List<Avertissement> getAvertissementsByUser(Long utilisateurId) {
		return avertissementRepository.findByUtilisateurId(utilisateurId);
	}

	// Retourne un avertissement son son id
	public Avertissement getUserById(Long utilisateurId) {
		return avertissementRepository.findById(utilisateurId).get();
	}
	
	// Retourne un avertissement(id) selon utilisateur(id)
	public Optional<Avertissement> getOneAvertissementByUser(Long utilisateurId, Long avertissementId) {
		return avertissementRepository.findByIdAndUtilisateurId(avertissementId,utilisateurId);
	}

	// Enregistre un avertissement selon son utilisateur(id)
	public Optional<Avertissement> saveAvertissementByUser(Long utilisateurId, Avertissement avertissement) {
		return utilisateurRepository.findById(utilisateurId).map(utilisateur -> {
			avertissement.setUtilisateur(utilisateur);
			return avertissementRepository.save(avertissement);
		});
	}

	// met a jour une avertissement selon son utilisateur(id)
	public Optional<Avertissement> editOneAvertissementByUser(Long AvertissementId,Long utilisateurId, Avertissement avertissement) {
		return avertissementRepository.findById(utilisateurId).map(avertissementToUpdate -> {
			avertissementToUpdate.setDescription(avertissement.getDescription());
			avertissementToUpdate.setUtilisateur(avertissement.getUtilisateur());
			return avertissementRepository.save(avertissementToUpdate);
		});
	}

	// supprimer un avertissement selon son utilisateur(id)
	public Optional<?> deleteOneAvertissementByUser(Long utilisateurId, Long avertissementId) {
		return avertissementRepository.findById(utilisateurId).map(limiteToDelete -> {
			avertissementRepository.delete(limiteToDelete);
			return "DELETED";
		});
	}

	public List<Avertissement> findAll(Long utilisateurId) {
		return avertissementRepository.findAllByUtilisateurId(utilisateurId);
		
	}

}
