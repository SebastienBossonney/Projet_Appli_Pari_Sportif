package projetFilRouge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetFilRouge.model.Utilisateur;
import projetFilRouge.repository.IUtilisateurRepository;

@Service(value = "utilisateurService")
public class UtilisateurService implements IService<Utilisateur> {

	@Autowired
	private IUtilisateurRepository utilisateurRepository;

	public List<Utilisateur> findAll() {
		return utilisateurRepository.findAll();
	}

	public Utilisateur saveOrUpdate(Utilisateur user) {
		return utilisateurRepository.save(user);
	}

	@Override
	public Optional<Utilisateur> getOne(Long id) {
		return utilisateurRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		utilisateurRepository.deleteById(id);

	}

	public void deleteUser(Utilisateur userToDelete) {
		utilisateurRepository.delete(userToDelete);

	}

	public Optional<Utilisateur> findByIdentifiantAndPassword(String identifiant, String password) {
		return utilisateurRepository.findByIdentifiantAndMotDePasse(identifiant, password);

	}

	public Utilisateur updateMontant(Utilisateur user) {
		return utilisateurRepository.save(user);
	}



}
