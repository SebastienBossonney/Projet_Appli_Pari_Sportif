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

import projetFilRouge.dto.ResultatDto;
import projetFilRouge.model.Equipe;
import projetFilRouge.model.Resultat;
import projetFilRouge.repository.IMatchRepository;
import projetFilRouge.repository.IResultatRepository;
import projetFilRouge.service.ResultatService;

@RestController
@CrossOrigin(origins ="*")
public class ResultatRestController {
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private ResultatService resultatService;
	
	
	
	@GetMapping("/matchs/{matchId}/resultats")
	public ResponseEntity<List<Resultat>> getResultatsByMatch(@PathVariable("matchId") Long matchtId){
		
		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));
		
		return new ResponseEntity<>(resultatService.getResultatsByMatch(matchId), HttpStatus.OK);
	}
	
	@GetMapping("/matchs/{matchId}/resultats/{resultatId}")
	public ResponseEntity<Resultat> getOneResultatByMatch(@PathVariable("matchId") Long matchId,
			@PathVariable("resultatId") Long resultatId){
		
		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));
		
		Resultat resultat = resultatService.getOneResultatByMatch(resultatId, matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scores not found with id: " + resultatId));
		
		return new ResponseEntity<>(resultat, HttpStatus.OK);
	}
	
	@PostMapping("/matchs/{matchId}/resultats")
	public ResponseEntity<Equipe> save(@PathVariable("matchId") Long matchId, @Valid @RequestBody ResultatDto resultatDto) {
		
		Resultat resultat=new Resultat();
		resultat.setEquipe1(resultatDto.getEquipe1());
		resultat.setScoreEquipe1(resultatDto.getScoreEquipe1());
		resultat.setEquipe2(resultatDto.getEquipe2());
		resultat.setScoreEquipe2(resultatDto.getScoreEquipe2());
		resultat.setMatchNul(resultat.isMatchNul());
		
		
		resultatService.saveResultatByMatch(matchId, resultat).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: :" + matchId));
		return new ResponseEntity<>(resultat, HttpStatus.OK);
	}
	
	@PutMapping("/matchs/{matchId}/resultats/{resultatId}")
	public ResponseEntity<Resultat> editOneResultatByMatch(@PathVariable("matchId") Long matchId,
			@PathVariable("resultatId") Long resultatId, @Valid @RequestBody ResultatDto resultatDto){
		
		
		Resultat resultat=new Resultat();
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
	
	@DeleteMapping("/matchs/{matchId}/resultats/{resultatId}")
	public ResponseEntity<?> deleteOneResultatByMatch(@PathVariable("matchId") Long matchId,
			@PathVariable("resultatId") Long resultatId){
		
		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));
		
		resultatService.deleteOneResultatByMatch(resultatId, matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scores not found with id: " + resultatId));
		
		return new ResponseEntity<>("DELETED successfully !", HttpStatus.OK);
	}

}
