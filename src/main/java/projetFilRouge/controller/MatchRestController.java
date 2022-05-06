package projetFilRouge.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;

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

import projetFilRouge.model.Equipe;
import projetFilRouge.model.Match;
import projetFilRouge.service.EquipeMatchService;
import projetFilRouge.service.EquipeService;

import projetFilRouge.service.MatchService;
import projetFilRouge.service.SportService;

@RestController
@CrossOrigin(origins = "*")
public class MatchRestController {

	@Autowired
	private MatchService matchService;

	@Autowired
	private EquipeService equipeService;

	@Autowired
	private EquipeMatchService equipeMatchService;

	@Autowired
	private SportService sportService;

	// method pour get all matchs
	@GetMapping("/sports/{sportId}/matchs-all")
	public ResponseEntity<List<MatchDto>> getMatchsBySport(@PathVariable("sportId") Long sportId) {
		sportService.getOne(sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found with id: " + sportId));

		List<MatchDto> listMatchResultsDto = new ArrayList<MatchDto>();

		List<Match> matchs = matchService.getMatchsBySport(sportId);

		for (int i = 0; i < matchs.size(); i++) {
			MatchDto matchResultsDto = new MatchDto();
			matchResultsDto.setId(matchs.get(i).getId());
			matchResultsDto.setDateMatch(matchs.get(i).getDateMatch());
			matchResultsDto.setHeureMatch(matchs.get(i).getHeureMatch());
			matchResultsDto.setLieu(matchs.get(i).getLieu());
			matchResultsDto.setVille(matchs.get(i).getVille());
			matchResultsDto.setPays(matchs.get(i).getPays());

			List<Equipe> equipes = equipeMatchService.getAllEquipesByMatchId(matchs.get(i).getId());

			matchResultsDto.setEquipes(equipes);
			matchResultsDto.setSportId(sportId);

			listMatchResultsDto.add(i, matchResultsDto);
			System.out.println(listMatchResultsDto.get(i).getId());
		}
		return new ResponseEntity<>(listMatchResultsDto, HttpStatus.OK);
	}
	

	// method pour get the prochains match selon la date
	@GetMapping("/sports/{sportId}/matchs")

	public ResponseEntity<List<MatchDto>> getMatchsSuivantesBySport(@PathVariable("sportId") Long sportId) {
		// Si l'utilisateur se trompe d'id, l'exception ResponseStatusException sera
		// declenche
		sportService.getOne(sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found with id: " + sportId));

		// List<Match> matchs = matchService.getMatchsSuivantesBySport(sportId);

		List<Match> matchs = matchService.getMatchsSuivantesBySport(sportId);

		List<MatchDto> matchDtoResult = new ArrayList<MatchDto>();

		for (Match m : matchs) {
			MatchDto matchDto = new MatchDto();
			matchDto.setId(m.getId());
			matchDto.setDateMatch(m.getDateMatch());
			matchDto.setHeureMatch(m.getHeureMatch());
			matchDto.setLieu(m.getLieu());
			matchDto.setVille(m.getVille());
			matchDto.setPays(m.getPays());
			matchDto.setSportId(sportId);

			List<Equipe> equipes = equipeMatchService.getAllEquipesByMatchId(m.getId());

			matchDto.setEquipes(equipes);

			matchDtoResult.add(matchDto);
		}

		return new ResponseEntity<>(matchDtoResult, HttpStatus.OK);
	}

	// get match by Id
	@GetMapping("/sports/{sportId}/matchs/{matchId}")
	public ResponseEntity<MatchDto> getOneMatchBySport(@PathVariable("sportId") Long sportId,
			@PathVariable("matchId") Long matchId) {

		sportService.getOne(sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found with id: " + sportId));

		Match match = matchService.getOneMatchBySport(matchId, sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));

		MatchDto matchDto = new MatchDto();
		matchDto.setId(match.getId());
		matchDto.setDateMatch(match.getDateMatch());
		matchDto.setHeureMatch(match.getHeureMatch());
		matchDto.setLieu(match.getLieu());
		matchDto.setVille(match.getVille());
		matchDto.setPays(match.getPays());
		matchDto.setSportId(sportId);

		List<Equipe> equipes = equipeMatchService.getAllEquipesByMatchId(matchId);

		matchDto.setEquipes(equipes);

		return new ResponseEntity<>(matchDto, HttpStatus.OK);
	}

	@PostMapping("/sports/{sportId}/matchs")
	public ResponseEntity<MatchDto> save(@PathVariable("sportId") Long sportId, 
			@RequestBody MatchDto matchDto) 
	{

		Match match = new Match();

		match.setDateMatch(matchDto.getDateMatch());

		match.setHeureMatch(matchDto.getHeureMatch());

		match.setLieu(matchDto.getLieu());
		match.setVille(matchDto.getVille());
		match.setPays(matchDto.getPays());

		List<Equipe> equipes = matchDto.getEquipes();

		match = matchService.saveMatchBySport(sportId, match, equipes)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport non trouvée :" + sportId));

		matchDto.setId(match.getId());
		matchDto.setSportId(sportId);
		return new ResponseEntity<>(matchDto, HttpStatus.OK);
	}

	

	@PutMapping("/sports/{sportId}/matchs/{matchId}")
	public ResponseEntity<MatchDto> editOneMatchBySport(@PathVariable("sportId") Long sportId,
			@PathVariable("matchId") Long matchId, @RequestBody MatchDto matchDto) {

		sportService.getOne(sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport" + " not found with id: " + sportId));

		Match match = matchService.editOneMatch(matchId, sportId, matchDto).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match " + "not found with id: " + matchId));

		matchDto.setId(matchId);
		matchDto.setSportId(sportId);
		
		return new ResponseEntity<>(matchDto, HttpStatus.OK);
	}

	@DeleteMapping("/sports/{sportId}/matchs/{matchId}")
	public ResponseEntity<?> deleteOneMatchBySport(@PathVariable("sportId") Long sportId,
			@PathVariable("matchId") Long matchId) {

		sportService.getOne(sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found with id: " + sportId));

		matchService.deleteOneMatcBySport(matchId, sportId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));

		return new ResponseEntity<>("DELETED successfully !", HttpStatus.OK);
	}

}

