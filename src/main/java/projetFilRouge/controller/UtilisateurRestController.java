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
		
		@Autowired
		private ResultatService resultatService;
		
		@Autowired
		private MatchService matchService;
		
		@Autowired
		private EquipeService equipeService;
		

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

			
			return new ResponseEntity<>(utilisateurService.saveOrUpdate(userToSave), HttpStatus.CREATED);
		}	

		@GetMapping(value = "/utilisateur/{id}")
		public ResponseEntity<Utilisateur> getUser(@PathVariable("id") Long id) {		

			Utilisateur user = utilisateurService.getById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));			
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		
		@PutMapping(value = "/utilisateur/{id}")
		public ResponseEntity<Utilisateur> editUser(@PathVariable("id") Long id, @Valid @RequestBody UtilisateurDto utilisateurDto) {
			
			Utilisateur userToUpdate = utilisateurService.getById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));	
			
			userToUpdate.setIdentifiant(utilisateurDto.getIdentifiant());
			userToUpdate.setEmail(utilisateurDto.getEmail());
			userToUpdate.setMotDePasse(utilisateurDto.getMotDePasse());
			userToUpdate.setRole(utilisateurDto.getRole());
			userToUpdate.setProfil(utilisateurDto.getProfil());
			
			utilisateurService.saveOrUpdate(userToUpdate);
			
			return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
		}
		
		@DeleteMapping(value = "/users/{id}")
		public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
			
			Utilisateur userToDelete = utilisateurService.getById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));	
			
			utilisateurService.delete(userToDelete);
			return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
		}
		
	
		// Controlleur lié aux équipes (creation + suppresion)
		@GetMapping("utilisateur/{utilisateurId}/sports/{sportId}/equipes")
		public ResponseEntity<List<Equipe>> getEquipesBysport(@PathVariable("sportId") Long sportId){
			
			sportService.getOne(sportId).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "sport not found with id: " + sportId));
			
			return new ResponseEntity<>(equipeService.getEquipesBySport(sportId), HttpStatus.OK);
		}
		
		@GetMapping("/sports/{sportId}/equipes/{equipeId}")
		public ResponseEntity<Equipe> getOneEquipeBySport(@PathVariable("sportId") Long sportId,
				@PathVariable("equipeId") Long equipeId){
			
			sportService.getOne(sportId).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "sport not found with id: " + sportId));
			
			Equipe equipe = equipeService.getOneEquipeBySport(equipeId, sportId).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "equipe not found with id: " + equipeId));
			
			return new ResponseEntity<>(equipe, HttpStatus.OK);
		}
		
		@PostMapping("/sports/{sportId}/equipes")
		public ResponseEntity<Equipe> save(@PathVariable("sportId") Long sportId, @Valid @RequestBody EquipeDto equipeDto) {
			
			Equipe equipe= new Equipe();
			equipe.setNom(equipeDto.getNom());
			
			equipeService.saveEquipeBySport(sportId, equipe).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "sport not found with id: :" + sportId));
			return new ResponseEntity<>(equipe, HttpStatus.OK);
		}
		
		@PutMapping("/sports/{sportId}/equipes/{equipeId}")
		public ResponseEntity<Equipe> editOneEquipeByport(@PathVariable("sportId") Long sportId,
				@PathVariable("equipeId") Long equipeId, @Valid @RequestBody EquipeDto equipeDto){
			
			Equipe equipe= new Equipe();
			equipe.setNom(equipeDto.getNom());
			
			sportService.getOne(sportId).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "sport not found with id: " + sportId));
			
			equipeService.editOneEquipeBySport(equipeId, sportId, equipe).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "equipe not found with id: " + equipeId));
			
			return new ResponseEntity<>(equipe, HttpStatus.OK);
		}
		
		@DeleteMapping("/sports/{sportId}/equipes/{equipeId}")
		public ResponseEntity<?> deleteOneEquipeBySport(@PathVariable("sportId") Long sportId,
				@PathVariable("equipeId") Long equipeId){
			
			sportService.getOne(sportId).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "sport not found with id: " + sportId));
			
			equipeService.deleteOneEquipeBySport(equipeId, sportId).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "equipe not found with id: " + equipeId));
			
			return new ResponseEntity<>("DELETED successfully !", HttpStatus.OK);
		}
		
		//Controlleur pour les matchs
		
		
		
		//Controlleur pour les résultats de matchs
	}
