package projetFilRouge.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

import com.fasterxml.jackson.core.JsonParser;

import projetFilRouge.dto.MatchDto;
import projetFilRouge.model.Match;
import projetFilRouge.service.MatchService;
import projetFilRouge.service.SportService;

@RestController
@CrossOrigin (origins = "*")
public class MatchRestController {
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private SportService sportService;
	
	  @GetMapping("/sports/{sportId}/matchs/all") public ResponseEntity<List<Match>>
	  getMatchsBySport(@PathVariable("sportId") Long sportId){ 
	  sportService.getOne(sportId).orElseThrow( () -> new
	  ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found with id: " +
	  sportId));
	  
	  return new ResponseEntity<>(matchService.getMatchsBySport(sportId),
	  HttpStatus.OK); }
	 
	
	@GetMapping("/sports/{sportId}/matchs")
	public ResponseEntity<List<Match>> getMatchsSuivantesBySport(@PathVariable("sportId") Long sportId){
		// Si l'utilisateur se trompe d'id, l'exception ResponseStatusException sera declenche
		sportService.getOne(sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found with id: " + sportId));
		
		return new ResponseEntity<>(matchService.getMatchsSuivantesBySport(sportId), HttpStatus.OK);
	}
	
	@GetMapping("/sports/{sportId}/matchs/{matchId}")
	public ResponseEntity<Match> getOneMatchBySport(@PathVariable("sportId") Long sportId,
			@PathVariable("matchId") Long matchId){
		
		sportService.getOne(sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found with id: " + sportId));
		
		Match match = matchService.getOneMatchBySport(matchId, sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));
		
		return new ResponseEntity<>(match, HttpStatus.OK);
	}
	
	@PostMapping("/sports/{sportId}/match")
	public ResponseEntity<Match> save(@PathVariable("sportId") Long sportId, @RequestBody MatchDto matchDto) {
		
		Match match = new Match();
		
		match.setDateMatch(matchDto.getDateMatch());
		
		
		match.setHeureMatch(matchDto.getHeureMatch());
		
		
		match.setLieu(matchDto.getLieu());
		match.setVille(matchDto.getVille());
		match.setPays(matchDto.getPays());
		
		matchService.saveMatchBySport(sportId, match).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport non trouvée :" + sportId));
		return new ResponseEntity<>(match, HttpStatus.OK);
	}
	
	@PutMapping("/sports/{sportId}/matchs/{matchId}")
	public ResponseEntity<Match> editOneMatchBySport(@PathVariable("sportId") Long sportId,
			@PathVariable("matchId") Long matchId, @RequestBody MatchDto matchDto){
		
		sportService.getOne(sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport"
						+ " not found with id: " + sportId));
		
		
		Match match = matchService.editOneMatch(matchId,sportId,matchDto).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match "
						+ "not found with id: " + matchId));
		
	
		return new ResponseEntity<>(match, HttpStatus.OK);
	}
	
	
	  @DeleteMapping("/sports/{sportId}/matchs/{matchId}") public ResponseEntity<?>
	  deleteOneMatchBySport(@PathVariable("sportId") Long sportId,  
	  @PathVariable("matchId") Long matchId){
	  
	  sportService.getOne(sportId).orElseThrow( () -> new
	  ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found with id: " +
	  sportId));
	  
	  matchService.deleteOneMatcBySport(matchId, sportId).orElseThrow( () -> new
	  ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " +
	  matchId));
	  
	  return new ResponseEntity<>("DELETED successfully !", HttpStatus.OK); }
	 

}

