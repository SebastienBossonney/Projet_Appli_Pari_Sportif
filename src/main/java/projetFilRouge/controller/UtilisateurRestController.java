package projetFilRouge.controller;

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
import projetFilRouge.model.Utilisateur;
import projetFilRouge.service.UtilisateurService;

@RestController
@CrossOrigin(origins = "*")
public class UtilisateurRestController {

	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping(value = "/utilisateurs")
	public ResponseEntity<List<Utilisateur>> getUsers() {
		return new ResponseEntity<>(utilisateurService.findAll(), HttpStatus.OK);
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
	public ResponseEntity<Utilisateur> createUser(@Valid @RequestBody UtilisateurDto utilisateurDto) {

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

		return new ResponseEntity<>(userToSave, HttpStatus.CREATED);
	}

	@GetMapping(value = "/utilisateurs/{id}")
	public ResponseEntity<Utilisateur> getUser(@PathVariable("id") Long id) {

		Utilisateur user = utilisateurService.getOne(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/utilisateurs/{id}")
	public ResponseEntity<Utilisateur> editUser(@PathVariable("id") Long id,
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

		return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
	}

	@DeleteMapping(value = "/utilisateurs/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {

		Utilisateur userToDelete = utilisateurService.getOne(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

		utilisateurService.deleteUser(userToDelete);
		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
	}

	@GetMapping(value = "/utilisateurs/{identifiant}/{password}")
	public ResponseEntity<Optional<Utilisateur>> findByIdentifiantAndPassword(
			@PathVariable("identifiant") String identifiant, @PathVariable("password") String password) {
		return new ResponseEntity<Optional<Utilisateur>>(
				utilisateurService.findByIdentifiantAndPassword(identifiant, password), HttpStatus.OK);
	}

	@GetMapping(value = "/utilisateurs/motDePasseOublie/{email}")
	public ResponseEntity<Optional<Utilisateur>> findByEmail(@PathVariable("email") String email) {
		return new ResponseEntity<Optional<Utilisateur>>(utilisateurService.findByEmail(email), HttpStatus.OK);
	}
}
