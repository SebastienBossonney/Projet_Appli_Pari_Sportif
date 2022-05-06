package projetFilRouge.controller;

import java.util.ArrayList;
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

import projetFilRouge.dto.PariDto;

import projetFilRouge.model.Pari;

import projetFilRouge.service.PariService;
import projetFilRouge.service.UtilisateurService;

@RestController
@CrossOrigin(origins = "*")
public class PariRestController {

	@Autowired
	private PariService pariService;

	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping(value = "Utilisateurs/{utilisateurId}/Paris")
	public ResponseEntity<List<PariDto>> getParis(@PathVariable("utilisateurId") Long utilisateurId) {

		List<Pari> pari = pariService.getPariByUtilisateur(utilisateurId);

		List<PariDto> parisDto = new ArrayList<PariDto>();

		for (Pari p : pari) {
			PariDto pariDto = new PariDto();
			pariDto.setId(p.getId());
			pariDto.setMontantJoue(p.getMontantJoue());
			pariDto.setDatePari(p.getDatePari());
			pariDto.setHeurePari(p.getHeurePari());
			pariDto.setMontantResultat(p.getMontantResultat());
			pariDto.setResultat(p.getResultat());
			pariDto.setUtilisateurId(utilisateurId);

			parisDto.add(pariDto);

		}

		return new ResponseEntity<>(parisDto, HttpStatus.OK);
	}

	// get match by Id
	@GetMapping("/utilisateurs/{utilisateurId}/paris/{pariId}")
	public ResponseEntity<PariDto> getOnePariByUtilisateur(@PathVariable("utilisateurId") Long utilisateurId,
			@PathVariable("pariId") Long pariId) {

		utilisateurService.getOne(utilisateurId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Utilisateur " + "not found with id: " + utilisateurId));

		Pari pari = pariService.getOnePariByUtilisateur(pariId, utilisateurId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pari not found with id: " + pariId));

		PariDto pariDto = new PariDto();
		pariDto.setId(pari.getId());
		pariDto.setMontantJoue(pari.getMontantJoue());
		pariDto.setDatePari(pari.getDatePari());
		pariDto.setHeurePari(pari.getHeurePari());
		pariDto.setMontantResultat(pari.getMontantResultat());
		pariDto.setResultat(pari.getResultat());
		pariDto.setUtilisateurId(utilisateurId);

		return new ResponseEntity<>(pariDto, HttpStatus.OK);
	}

	@PostMapping("/utilisateurs/{utilisateurId}/paris")
	public ResponseEntity<PariDto> save(@PathVariable("utilisateurId") Long utilisateurId,
			@RequestBody PariDto pariDto) {

		Pari pari = new Pari();

		pari.setMontantJoue(pariDto.getMontantJoue());
		pari.setDatePari(pariDto.getDatePari());
		pari.setHeurePari(pariDto.getHeurePari());
		pari.setMontantResultat(pariDto.getMontantResultat());
		pari.setResultat(pariDto.getResultat());

		pari = pariService.savePariByUtilisateur(utilisateurId, pari).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvée :" + utilisateurId));

		pariDto.setId(pari.getId());
		pariDto.setUtilisateurId(utilisateurId);
		return new ResponseEntity<>(pariDto, HttpStatus.OK);
	}

	@PutMapping("/utilisateurs/{utilisateurId}/paris/{pariId}")
	public ResponseEntity<PariDto> editOnePariByUtilisateur(@PathVariable("utilisateurId") Long utilisateurId,
			@PathVariable("pariId") Long pariId, @RequestBody PariDto pariDto) {

		utilisateurService.getOne(utilisateurId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Utilisateur" + " not found with id: " + utilisateurId));

		pariService.editOnePari(pariId, utilisateurId, pariDto).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pari " + "not found with id: " + pariId));

		pariDto.setId(pariId);
		pariDto.setUtilisateurId(utilisateurId);

		return new ResponseEntity<>(pariDto, HttpStatus.OK);
	}

	@DeleteMapping("/utilisateurs/{utilisateurId}/paris/{parisId}")
	public ResponseEntity<?> deleteOnePariByUtilisateur(@PathVariable("utilisateurId") Long utilisateurId,
			@PathVariable("pariId") Long pariId) {

		utilisateurService.getOne(utilisateurId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Utilisateur not found with id: " + utilisateurId));

		pariService.deleteOnePariByUtilisateur(pariId, utilisateurId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pari not found with id: " + pariId));

		return new ResponseEntity<>("DELETED successfully !", HttpStatus.OK);
	}
}
