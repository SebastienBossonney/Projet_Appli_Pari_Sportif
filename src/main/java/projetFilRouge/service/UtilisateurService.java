package projetFilRouge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import projetFilRouge.model.Utilisateur;
import projetFilRouge.repository.IAvertissementRepository;
import projetFilRouge.repository.ILimiteRepository;
import projetFilRouge.repository.IPariRepository;
import projetFilRouge.repository.IUtilisateurRepository;

public class UtilisateurService implements IService<Utilisateur>{

	@Autowired
	private ILimiteRepository limiteRepository;

	@Autowired
	private IAvertissementRepository avertissementRepository;

	@Autowired
	private IPariRepository pariRepository;

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




}
