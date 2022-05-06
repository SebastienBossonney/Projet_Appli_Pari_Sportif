package projetFilRouge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import projetFilRouge.dto.EquipeDto;
import projetFilRouge.dto.UtilisateurDto;
import projetFilRouge.model.Equipe;
import projetFilRouge.model.Utilisateur;
import projetFilRouge.service.EquipeService;
import projetFilRouge.service.ResultatService;
import projetFilRouge.service.UtilisateurService;

@RestController
@CrossOrigin(origins ="*")
public class UtilisateurRestController {
	
		@Autowired
		private UtilisateurService utilisateurService;
		

		@GetMapping(value = "/users")
		public ResponseEntity<List<Utilisateur>> getUsers(){		
			return new ResponseEntity<>(utilisateurService.findAll(), HttpStatus.OK);
		}


		@PostMapping(value = "/users")
		public ResponseEntity<Utilisateur> createEmployee(@Valid @RequestBody UtilisateurDto utilisateurDto) {
			
			Utilisateur userToSave = new Utilisateur();
			
			userToSave.setIdentifiant(utilisateurDto.getIdentifiant());
			userToSave.setEmail(utilisateurDto.getEmail());
			userToSave.setMotDePasse(utilisateurDto.getMotDePasse());
			userToSave.setRole(utilisateurDto.getRole());
			userToSave.setProfil(utilisateurDto.getProfil());
			userToSave.setMontantTotalGagne(utilisateurDto.getMontantTotalGagne());
			userToSave.setMontantTotalPerdu(utilisateurDto.getMontantTotalPerdu());

			
			return new ResponseEntity<>(utilisateurService.saveOrUpdate(userToSave), HttpStatus.CREATED);
		}	

		@GetMapping(value = "/utilisateur/{id}")
		public ResponseEntity<Utilisateur> getUser(@PathVariable("id") Long id) {		

			Utilisateur user = utilisateurService.getOne(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));			
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		
		@PutMapping(value = "/utilisateur/{id}")
		public ResponseEntity<Utilisateur> editUser(@PathVariable("id") Long id, @Valid @RequestBody UtilisateurDto utilisateurDto) {
			
			Utilisateur userToUpdate = utilisateurService.getOne(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));	
			
			userToUpdate.setIdentifiant(utilisateurDto.getIdentifiant());
			userToUpdate.setEmail(utilisateurDto.getEmail());
			userToUpdate.setMotDePasse(utilisateurDto.getMotDePasse());
			userToUpdate.setRole(utilisateurDto.getRole());
			userToUpdate.setProfil(utilisateurDto.getProfil());
			userToUpdate.setMontantTotalGagne(utilisateurDto.getMontantTotalGagne());
			userToUpdate.setMontantTotalPerdu(utilisateurDto.getMontantTotalPerdu());
			
			utilisateurService.saveOrUpdate(userToUpdate);
			
			return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
		}
		
		@DeleteMapping(value = "/users/{id}")
		public ResponseEntity<?> delete(@PathVariable("id") Long id) {
			
			Utilisateur userToDelete = utilisateurService.getOne(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));	
			
			utilisateurService.deleteUser(userToDelete);
			return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
		}
		
	
		// Controlleur lié aux équipes (creation + suppresion)

		
		//Controlleur pour les matchs
		
		
		
		//Controlleur pour les résultats de matchs
	}
