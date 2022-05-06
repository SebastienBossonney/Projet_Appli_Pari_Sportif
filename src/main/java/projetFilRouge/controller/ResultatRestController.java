package projetFilRouge.controller;

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

import projetFilRouge.dto.ResultatDto;
import projetFilRouge.model.Resultat;
import projetFilRouge.service.MatchService;
import projetFilRouge.service.ResultatService;

@RestController
@CrossOrigin(origins = "*")
public class ResultatRestController {

	@Autowired
	private MatchService matchService;

	@Autowired
	private ResultatService resultatService;

	@GetMapping("/sports/{sportId}/matchs/{matchId}/resultats")
	public ResponseEntity<ResultatDto> getResultatsByMatch(@PathVariable("matchId") Long matchId) {

		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));

		Resultat resultat1 = resultatService.getResultatsByMatch(matchId);
		ResultatDto resultatPourAfficher = new ResultatDto();
		resultatPourAfficher.setEquipe1(resultat1.getEquipe1());
		resultatPourAfficher.setScoreEquipe1(resultat1.getScoreEquipe1());
		resultatPourAfficher.setEquipe2(resultat1.getEquipe2());
		resultatPourAfficher.setScoreEquipe2(resultat1.getScoreEquipe2());
		resultatPourAfficher.setMatchNul(resultat1.isMatchNul());
		resultatPourAfficher.setMatchId(resultat1.getMatch().getId());

		return new ResponseEntity<>(resultatPourAfficher, HttpStatus.OK);

		// return new ResponseEntity<>(resultatService.getResultatsByMatch(matchId),
		// HttpStatus.OK);
	}

	@GetMapping("/sports/{sportId}/matchs/{matchId}/resultats/{resultatId}")
	public ResponseEntity<ResultatDto> getOneResultatByMatch(@PathVariable("matchId") Long matchId,
			@PathVariable("resultatId") Long resultatId) {

		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));

		Resultat resultat = resultatService.getOneResultatByMatch(resultatId, matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scores not found with id: " + resultatId));

		ResultatDto resultatPourAfficher = new ResultatDto();
		resultatPourAfficher.setEquipe1(resultat.getEquipe1());
		resultatPourAfficher.setScoreEquipe1(resultat.getScoreEquipe1());
		resultatPourAfficher.setEquipe2(resultat.getEquipe2());
		resultatPourAfficher.setScoreEquipe2(resultat.getScoreEquipe2());
		resultatPourAfficher.setMatchNul(resultat.isMatchNul());
		resultatPourAfficher.setMatchId(resultat.getMatch().getId());

		return new ResponseEntity<>(resultatPourAfficher, HttpStatus.OK);

		// return new ResponseEntity<>(resultat, HttpStatus.OK);
	}

	@PostMapping("/sports/{sportId}/matchs/{matchId}/resultats")
	public ResponseEntity<ResultatDto> save(@PathVariable("matchId") Long matchId,
			@RequestBody ResultatDto resultatDto) {

		Resultat resultat = new Resultat();
		resultat.setEquipe1(resultatDto.getEquipe1());
		resultat.setScoreEquipe1(resultatDto.getScoreEquipe1());
		resultat.setEquipe2(resultatDto.getEquipe2());
		resultat.setScoreEquipe2(resultatDto.getScoreEquipe2());
		resultat.setMatchNul(resultatDto.isMatchNul());

		resultatDto.setMatchId(matchId);

		resultatService.saveResultatByMatch(matchId, resultat).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: :" + matchId));
		return new ResponseEntity<>(resultatDto, HttpStatus.OK);
	}

	@PutMapping("/sports/{sportId}/matchs/{matchId}/resultats/{resultatId}")
	public ResponseEntity<Resultat> editOneResultatByMatch(@PathVariable("matchId") Long matchId,
			@PathVariable("resultatId") Long resultatId, @RequestBody ResultatDto resultatDto) {

		Resultat resultat = new Resultat();
		resultat.setEquipe1(resultatDto.getEquipe1());
		resultat.setScoreEquipe1(resultatDto.getScoreEquipe1());
		resultat.setEquipe2(resultatDto.getEquipe2());
		resultat.setScoreEquipe2(resultatDto.getScoreEquipe2());
		resultat.setMatchNul(resultat.isMatchNul());

		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));

		resultatService.editOneResultatByMatch(resultatId, matchId, resultat).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scores not found with id: " + resultatId));

		return new ResponseEntity<>(resultat, HttpStatus.OK);
	}

	@DeleteMapping("/sports/{sportId}/matchs/{matchId}/resultats/{resultatId}")
	public ResponseEntity<?> deleteOneResultatByMatch(@PathVariable("matchId") Long matchId,
			@PathVariable("resultatId") Long resultatId) {

		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));

		resultatService.deleteOneResultatByMatch(resultatId, matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scores not found with id: " + resultatId));

		return new ResponseEntity<>("DELETED successfully !", HttpStatus.OK);
	}

}
