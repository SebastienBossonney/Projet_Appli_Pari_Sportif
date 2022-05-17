package projetFilRouge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import projetFilRouge.dto.UtilisateurDto;
import projetFilRouge.model.Avertissement;
import projetFilRouge.model.Limite;
import projetFilRouge.model.Profil;
import projetFilRouge.model.Role;
import projetFilRouge.model.Utilisateur;
import projetFilRouge.service.UtilisateurService;

@RestController
@CrossOrigin(origins = "*")
public class UtilisateurRestController {

	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping(value = "/utilisateurs")
	public ResponseEntity<List<UtilisateurDto>> getUsers() {
		
		List<Utilisateur> listUtilisateurs = utilisateurService.findAll();
		List<UtilisateurDto> listDtoU = new ArrayList<UtilisateurDto>();
		
		for(Utilisateur u : listUtilisateurs)
		{
			UtilisateurDto  uDto = new UtilisateurDto(u.getIdentifiant(), u.getEmail(), u.getMotDePasse(), u.getRole(),
			                                          u.getProfil(), u.getMontantTotalGagne() , u.getMontantTotalPerdu(),
			                                          u.getSalaire(), u.getMontantDisponible(), u.getLimite(), u.getAvertissements());
			                uDto.setId(u.getId());
			listDtoU.add(uDto);
				
		}
		
		return new ResponseEntity<>(listDtoU, HttpStatus.OK);
	}

//	@PutMapping(value = "/utilisateurs/{id}")
//	public ResponseEntity<Utilisateur> updateMontant(@PathVariable("id") Long id,
//			@RequestBody Utilisateur utilisateur) {
//
//		Utilisateur userToUpdate = utilisateurService.getOne(id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
//
//		double newMontant = userToUpdate.getMontantDisponible() + utilisateur.getMontantDisponible();
//		userToUpdate.setMontantDisponible(newMontant);
//
//		return new ResponseEntity<>(utilisateurService.saveOrUpdate(userToUpdate), HttpStatus.OK);
//	}

	@PostMapping(value = "/utilisateurs")
	public ResponseEntity<UtilisateurDto> createUser(@Valid @RequestBody UtilisateurDto utilisateurDto) {

		Utilisateur userToSave = new Utilisateur();

		userToSave.setIdentifiant(utilisateurDto.getIdentifiant());
		userToSave.setEmail(utilisateurDto.getEmail());
		userToSave.setMotDePasse(utilisateurDto.getMotDePasse());
		userToSave.setRole(utilisateurDto.getRole());
		userToSave.setProfil(utilisateurDto.getProfil());
		userToSave.setSalaire(utilisateurDto.getSalaire());
		userToSave.setMontantTotalGagne(utilisateurDto.getMontantTotalGagne());
		userToSave.setMontantTotalPerdu(utilisateurDto.getMontantTotalPerdu());
		userToSave.setMontantDisponible(utilisateurDto.getMontantDisponible());

		userToSave = utilisateurService.saveOrUpdate(userToSave);
		
		utilisateurDto.setId(userToSave.getId());
		
		return new ResponseEntity<>(utilisateurDto, HttpStatus.CREATED);
	}

	@GetMapping(value = "/utilisateurs/{id}")
	public ResponseEntity<UtilisateurDto> getUser(@PathVariable("id") Long id) {

		Utilisateur user = utilisateurService.getOne(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
		
		UtilisateurDto utilisateurDto = new UtilisateurDto(user.getIdentifiant(), user.getEmail(), user.getMotDePasse(), user.getRole(),
				user.getProfil(), user.getMontantTotalGagne() , user.getMontantTotalPerdu(),
				user.getSalaire(), user.getMontantDisponible(), user.getLimite(), user.getAvertissements());
		
		utilisateurDto.setId(user.getId());
		
		return new ResponseEntity<>(utilisateurDto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/utilisateurs/{id}")
	public ResponseEntity<UtilisateurDto> editUser(@PathVariable("id") Long id,
			@Valid @RequestBody UtilisateurDto utilisateurDto) {

		Utilisateur userToUpdate = utilisateurService.getOne(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

		userToUpdate.setIdentifiant(utilisateurDto.getIdentifiant());
	 	userToUpdate.setEmail(utilisateurDto.getEmail());
		userToUpdate.setMotDePasse(utilisateurDto.getMotDePasse());
		userToUpdate.setRole(utilisateurDto.getRole());
		userToUpdate.setProfil(utilisateurDto.getProfil());
		userToUpdate.setSalaire(utilisateurDto.getSalaire());
		userToUpdate.setMontantTotalGagne(utilisateurDto.getMontantTotalGagne());
		userToUpdate.setMontantTotalPerdu(utilisateurDto.getMontantTotalPerdu());
		userToUpdate.setMontantDisponible(utilisateurDto.getMontantDisponible());
		
		utilisateurDto.setId(id);
		
		return new ResponseEntity<>(utilisateurDto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/utilisateurs/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {

		Utilisateur userToDelete = utilisateurService.getOne(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

		utilisateurService.deleteUser(userToDelete);
		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
	}

	@GetMapping(value = "/utilisateurs/{identifiant}/{password}")
	public ResponseEntity<UtilisateurDto> findByIdentifiantAndPassword(
			@PathVariable("identifiant") String identifiant, @PathVariable("password") String password) 
	{
		Utilisateur user = utilisateurService.findByIdentifiantAndPassword(identifiant, password)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with identifiant" + identifiant + "and password " + password));
		
        UtilisateurDto userDto = new UtilisateurDto(user.getIdentifiant(), user.getEmail(), user.getMotDePasse(), user.getRole(),
				user.getProfil(), user.getMontantTotalGagne() , user.getMontantTotalPerdu(),
				user.getSalaire(), user.getMontantDisponible(), user.getLimite(), user.getAvertissements());
        userDto.setId(user.getId());
		
		return new ResponseEntity<UtilisateurDto>(userDto, HttpStatus.OK);
	}

	@GetMapping(value = "/utilisateurs/motDePasseOublie/{email}")
	public ResponseEntity<UtilisateurDto> findByEmail(@PathVariable("email") String email) {
		
		Utilisateur user = utilisateurService.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email" + email));
		
		UtilisateurDto userDto = new UtilisateurDto(user.getIdentifiant(), user.getEmail(), user.getMotDePasse(), user.getRole(),
				user.getProfil(), user.getMontantTotalGagne() , user.getMontantTotalPerdu(),
				user.getSalaire(), user.getMontantDisponible(), user.getLimite(), user.getAvertissements());
        userDto.setId(user.getId());
        
		return new ResponseEntity<UtilisateurDto>(userDto, HttpStatus.OK);
	}
}
