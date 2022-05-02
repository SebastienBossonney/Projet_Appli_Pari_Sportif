package projetFilRouge.controller;

import java.util.List;

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

import projetFilRouge.model.Avertissement;
import projetFilRouge.service.AvertissementService;
import projetFilRouge.service.UtilisateurService;

@RestController
@CrossOrigin(origins ="*")
public class AvertissementRestController {
	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private AvertissementService avertissementService;
	
	@GetMapping("/users/{userId}/avertissements")
	public ResponseEntity<List<Avertissement>> getAvertissementByUser(@PathVariable("userId") Long userId){
		utilisateurService.getById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + userId));
		
		return new ResponseEntity<>(avertissementService.getAvertissementsByUser(userId), HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}/avertissements/{avertissementId}")
	public ResponseEntity<Avertissement> getOneAvertissementByUser(@PathVariable("userId") Long userId,
			@PathVariable("avertissementId") Long avertissementId){
		
		utilisateurService.getById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + userId));
		
		Avertissement avertissement = avertissementService.getOneAvertissementByUser(avertissementId, userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avertissement not found with id: " + avertissementId));
		
		return new ResponseEntity<>(avertissement, HttpStatus.OK);
	}
	
	@PostMapping("/users/{userId}/avertissements")
	public ResponseEntity<Avertissement> save(@PathVariable("userId") Long userId, @RequestBody Avertissement avertissement) {
		avertissementService.saveAvertissementByUser(userId, avertissement).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found :" + userId));
		return new ResponseEntity<>(avertissement, HttpStatus.OK);
	}
	
	@PutMapping("/users/{userId}/avertissements/{avertissementId}")
	public ResponseEntity<Avertissement> editOneCarByEmployee(@PathVariable("userId") Long userId,
			@PathVariable("avertissementId") Long avertissementId, @RequestBody Avertissement avertissement){
		
		utilisateurService.getById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + userId));
		
		avertissementService.editOneAvertissementByUser(avertissementId, userId, avertissement).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avertissement not found with id: " + avertissementId));
		
		return new ResponseEntity<>(avertissement, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{userId}/avertissements/{avertissementId}")
	public ResponseEntity<?> deleteOneAvertissementByUser(@PathVariable("userId") Long userId,
			@PathVariable("avertissementId") Long avertissementId){
		
		utilisateurService.getById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));
		
		avertissementService.deleteOneAvertissementByUser(avertissementId, userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avertissement not found with id: " + avertissementId));
		
		return new ResponseEntity<>("DELETED successfully !", HttpStatus.OK);
	}
}
