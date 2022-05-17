package projetFilRouge.controller;

import java.util.ArrayList;
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

import projetFilRouge.dto.LimiteDto;
import projetFilRouge.model.Limite;
import projetFilRouge.model.Utilisateur;
import projetFilRouge.service.LimiteService;
import projetFilRouge.service.UtilisateurService;

@RestController
@CrossOrigin(origins = "*")
public class LimiteRestController {
	
	@Autowired
	private LimiteService limiteService;
	
	@Autowired
	private UtilisateurService utilisateurService;

	@PostMapping(value = "/utilisateurs/{userId}/limite")
	public ResponseEntity<LimiteDto> createLimit(@PathVariable("userId") Long userId,
			@Valid @RequestBody LimiteDto limiteDto) {
		System.out.println(userId);
		Utilisateur user1 = new Utilisateur();
		user1 = utilisateurService.getOne(userId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvée avec l'id :" + userId));
	
		Limite limitToSave = new Limite();

		limitToSave.setValeur(limiteDto.getValeur());
		limitToSave.setDuree(limiteDto.getDuree());
		limitToSave.setUtilisateur(user1);
		limitToSave = limiteService.saveLimitByUser(userId, limitToSave)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Employee not found with id: " + userId));
		
		limiteDto.setId(limitToSave.getId());
		limiteDto.setUtilisateurId(userId);
				
		return new ResponseEntity<>(limiteDto, HttpStatus.CREATED);
	}
 
	@GetMapping(value = "/utilisateurs/{utilisateurId}/limite")
	public ResponseEntity<LimiteDto> getLimitByUtilisateurId(@PathVariable("utilisateurId") Long utilisateurId) {
		
		Utilisateur user = utilisateurService.getOne(utilisateurId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Utilisateur not found with id: " + utilisateurId));
		
			
		Limite limite = limiteService.getOneLimitByUser(utilisateurId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Limite not found pour l'utilisatuer: " + utilisateurId));;
		
		LimiteDto limiteDto = new LimiteDto (limite.getValeur(), limite.getDuree(), utilisateurId);
		limiteDto.setId(limite.getId());
		
     return new ResponseEntity<>(limiteDto, HttpStatus.OK);
    }

	@GetMapping(value = "/limite/{limiteId}")
	public ResponseEntity<LimiteDto> getLimitById(@PathVariable("limiteId") Long limiteId) {
		
		Limite limite = limiteService.getLimiteById(limiteId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Limite not found with id: " + limiteId));
		
		LimiteDto limiteDto = new LimiteDto(limite.getValeur(), limite.getDuree(), limite.getUtilisateur().getId());
		limiteDto.setId(limiteId);
		return new ResponseEntity<>(limiteDto, HttpStatus.OK);
	}


	@PutMapping(value = "/utilisateurs/{utilisateurId}/limite")
	public ResponseEntity<LimiteDto> editLimit(@PathVariable("utilisateurId") Long utilisateurId, @Valid @RequestBody LimiteDto limiteDto) {

		Limite limitToUpdate = limiteService.getOneLimitByUser(utilisateurId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + utilisateurId));

		limitToUpdate.setDuree(limiteDto.getDuree());
		limitToUpdate.setValeur(limiteDto.getValeur());
        
		limiteService.saveLimitByUser(utilisateurId, limitToUpdate);
		
		limiteDto.setId(limitToUpdate.getId());
		limiteDto.setUtilisateurId(utilisateurId);

		return new ResponseEntity<>(limiteDto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/utilisateurs/{utilisateurId}/limite/{limiteId}")
	public ResponseEntity<?> deleteLimite(@PathVariable("utilisateurId") Long utilisateurId, @PathVariable("limiteId") Long limiteId) {

		limiteService.getOneLimitByUser(utilisateurId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Limite not found with id: " + limiteId));

		limiteService.deleteOneLimitByUser(utilisateurId);
		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
	}
}
