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

import projetFilRouge.model.Sport;
import projetFilRouge.service.SportService;

@RestController
@CrossOrigin(origins = "*")
public class SportRestController {

	@Autowired
	private SportService sportService;

	@GetMapping(value = "/sports")
	public ResponseEntity<List<Sport>> getSports() {
		return new ResponseEntity<>(sportService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/sports")
	public ResponseEntity<Sport> createSport(@RequestBody Sport sport) {

		Sport sportToSave = new Sport();

		sportToSave.setNomSport(sport.getNomSport());

		return new ResponseEntity<>(sportService.saveOrUpdate(sportToSave), HttpStatus.CREATED);
	}

	@GetMapping(value = "/sports/{id}")
	public ResponseEntity<Sport> getSport(@PathVariable("id") Long id) {

		Sport sport = sportService.getOne(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport has not been found with id: " + id));
		return new ResponseEntity<>(sport, HttpStatus.OK);
	}

	@PutMapping(value = "/sports/{id}")
	public ResponseEntity<Sport> editSport(@PathVariable("id") Long id, @RequestBody Sport sport) {

		Sport sportToUpdate = sportService.getOne(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport has not been found with id: " + id));

		sportToUpdate.setNomSport(sport.getNomSport());

		sportService.saveOrUpdate(sportToUpdate);

		return new ResponseEntity<>(sportToUpdate, HttpStatus.OK);
	}

	@DeleteMapping(value = "/sports/{id}")
	public ResponseEntity<?> deleteSport(@PathVariable("id") Long id) {

		sportService.getOne(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport has not been found with id: " + id));

		sportService.delete(id);

		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
	}
}
