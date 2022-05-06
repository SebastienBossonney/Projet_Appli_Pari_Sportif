package projetFilRouge.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import projetFilRouge.dto.LimiteDto;
import projetFilRouge.dto.UtilisateurDto;
import projetFilRouge.model.Limite;
import projetFilRouge.model.Utilisateur;
import projetFilRouge.service.LimiteService;
import projetFilRouge.service.UtilisateurService;

public class LimiteRestController {
	private LimiteService limiteService;
	
	private UtilisateurService utilisateurService;

	
	@PostMapping(value = "/users/{userId}/limite")
	public ResponseEntity<Limite> createLimit(@PathVariable("userId") Long utilisateurId, @Valid @RequestBody LimiteDto limiteDto) {
		
		Utilisateur user = utilisateurService.getOne(utilisateurId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + utilisateurId));
		Limite limitToSave = new Limite();
		
		limitToSave.setValeur(limiteDto.getValeur());
		limitToSave.setDuree(limiteDto.getDuree());

		limitToSave.setUtilisateur(user);
		limiteService.saveLimitByUser(utilisateurId,limitToSave);
		
		return new ResponseEntity<>(limitToSave, HttpStatus.CREATED);
	}	

	
	@GetMapping(value = "/utilisateur/{utilisateurId}/limite")
	public ResponseEntity<Limite> getUser(@PathVariable("utilisateurId") Long utilisateurId) {		

		Limite limite = limiteService.getOneLimitByUser(utilisateurId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + utilisateurId));			
		return new ResponseEntity<>(limite, HttpStatus.OK);
	}
	
	@PutMapping(value = "/utilisateur/{utilisateurId}/limite")
	public ResponseEntity<Limite> editLimit(@PathVariable("id") Long userId, @Valid @RequestBody LimiteDto limiteDto) {
		
		Limite limitToUpdate = limiteService.getOneLimitByUser(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));	
		
		limitToUpdate.setDuree(limiteDto.getDuree());
		limitToUpdate.setValeur(limiteDto.getValeur());

		limiteService.saveLimitByUser(userId,limitToUpdate);
		
		return new ResponseEntity<>(limitToUpdate, HttpStatus.OK);
	}
	

	@DeleteMapping(value = "/utilisateur/{utilisateurId}/limite/{limiteId}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long utilisateurId) {
		
		Limite limitToDelete = limiteService.getOneLimitByUser(utilisateurId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + utilisateurId));	
		
		limiteService.deleteOneLimitByUser(utilisateurId);
		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
	}
}
