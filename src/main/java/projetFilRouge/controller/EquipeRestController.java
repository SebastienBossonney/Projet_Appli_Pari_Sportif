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
import projetFilRouge.model.Equipe;
import projetFilRouge.service.EquipeService;
import projetFilRouge.service.SportService;

@RestController
@CrossOrigin(origins ="*")
public class EquipeRestController {
	
	@Autowired
	private SportService sportService;
	
	@Autowired
	private EquipeService equipeService;
	
	
//	@GetMapping(value = "/equipes")
//	public ResponseEntity<List<Equipe>> getEquipes(){		
//		return new ResponseEntity<>(equipeService.findAll(), HttpStatus.OK);
//	}
//	
//	//partie du controller pour la partie de l'équipe avec la classe joueur
//	
//	@PostMapping(value = "/equipes")
//	public ResponseEntity<Equipe> createEquipe(@Valid @RequestBody EquipeDto equipeDto) {
//		
//		Equipe equipeToSave = new Equipe();
//		
//		equipeToSave.setNom(equipeDto.getNom());
//		
//		return new ResponseEntity<>(equipeService.saveOrUpdate(equipeToSave), HttpStatus.CREATED);
//	}	
//	
//	@GetMapping(value = "/equipes/{id}")
//	public ResponseEntity<Equipe> getEquipe(@PathVariable("id") Long id) {		
//
//		Equipe equipe = equipeService.getOne(id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The team has not been found with id: " + id));			
//		return new ResponseEntity<>(equipe, HttpStatus.OK);
//	}
//	
//	@PutMapping(value = "/equipes/{id}")
//	public ResponseEntity<Equipe> editEquipe(@PathVariable("id") Long id, @Valid @RequestBody EquipeDto equipeDto) {
//		
//		Equipe equipeToUpdate = equipeService.getOne(id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The team has not been found with id: " + id));	
//		
//		equipeToUpdate.setNom(equipeDto.getNom());
//			
//		equipeService.saveOrUpdate(equipeToUpdate);
//		
//		return new ResponseEntity<>(equipeToUpdate, HttpStatus.OK);
//	}
//	
//	@DeleteMapping(value = "/equipes/{id}")
//	public ResponseEntity<?> deleteEquipe(@PathVariable("id") Long id) {
//		
//		Equipe equipeToDelete = equipeService.getOne(id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The team has not been found with id: " + id));	
//		
//		equipeService.delete(id);
//		
//		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
//	}

	
//Partie du controller par le côté Equipe et Sport
	
	@GetMapping("/sports/{sportId}/equipes")
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
	public ResponseEntity<Equipe> save(@PathVariable("sportId") Long sportId, @RequestBody EquipeDto equipeDto) {
		
		Equipe equipe= new Equipe();
		equipe.setNom(equipeDto.getNom());
		
		equipeService.saveEquipeBySport(sportId, equipe).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "sport not found with id: :" + sportId));
		return new ResponseEntity<>(equipe, HttpStatus.OK);
	}
	
	@PutMapping("/sports/{sportId}/equipes/{equipeId}")
	public ResponseEntity<Equipe> editOneEquipeByport(@PathVariable("sportId") Long sportId,
			@PathVariable("equipeId") Long equipeId,  @RequestBody EquipeDto equipeDto){
		
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
}