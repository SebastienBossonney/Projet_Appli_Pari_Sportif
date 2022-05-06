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

import projetFilRouge.dto.CoteDto;
import projetFilRouge.model.Cote;
import projetFilRouge.service.CoteService;
import projetFilRouge.service.MatchService;


@RestController
@CrossOrigin(origins = "*")
public class CoteRestController {

	@Autowired
	private CoteService coteService;
	
	@Autowired
	private MatchService matchService;
	
	@GetMapping("matchs/{matchId}/cotes")
	public ResponseEntity<List<CoteDto>> getCotesByMatch(@PathVariable("matchId") Long matchId){
		// Si l'utilisateur se trompe d'id, l'exception ResponseStatusException sera declenche
		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));
		
        List <Cote> cotes = coteService.getCotesByMatch(matchId);
		List<CoteDto> cotesDto = new ArrayList<CoteDto>();
		
		for(Cote c : cotes)
		{
			CoteDto cotedto = new CoteDto();
			cotedto.setId(c.getId());
			cotedto.setStatut(c.getStatut());
			cotedto.setValeur(c.getValeur());
			cotedto.setMatchId(matchId);
			
			cotesDto.add(cotedto);
		}
		
		return new ResponseEntity<>(cotesDto, HttpStatus.OK);
	}
	
	@GetMapping("/matchs/{matchId}/cotes/{coteId}")
	public ResponseEntity<CoteDto> getOneCoteByMatch(@PathVariable("matchId") Long matchId,
			@PathVariable("coteId") Long coteId){
		
		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));
		
		Cote cote = coteService.getOneCoteByMatch(coteId, matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cote not found with id: " + coteId));
		
		CoteDto coteDto = new CoteDto();
		coteDto.setId(coteId);
		coteDto.setStatut(cote.getStatut());
		coteDto.setValeur(cote.getValeur());
		
		coteDto.setMatchId(matchId);
		
		return new ResponseEntity<>(coteDto, HttpStatus.OK);
	}
	
	@PostMapping("/matchs/{matchId}/cotes")
	public ResponseEntity<CoteDto> save(@PathVariable("matchId") Long matchId, @RequestBody Cote cote) {
	 coteService.saveCoteByMatch(matchId, cote).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match non trouvée :" + matchId));
	 
	    CoteDto coteDto = new CoteDto();
		coteDto.setId(cote.getId());
		coteDto.setStatut(cote.getStatut());
		coteDto.setValeur(cote.getValeur());
		
		coteDto.setMatchId(matchId);
		
		return new ResponseEntity<>(coteDto, HttpStatus.OK);
	}
	
	@PutMapping("/matchs/{matchId}/cotes/{coteId}")
	public ResponseEntity<CoteDto> editOneCoteByMatch(@PathVariable("matchId") Long matchId,
			@PathVariable("coteId") Long coteId, @RequestBody Cote cote){
		
		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));
		
		coteService.editOneCoteByMacth(coteId, matchId, cote).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cote not found with id: " + coteId));
	
		CoteDto coteDto = new CoteDto();
		coteDto.setId(coteId);
		coteDto.setStatut(cote.getStatut());
		coteDto.setValeur(cote.getValeur());
		
		coteDto.setMatchId(matchId);
		
		return new ResponseEntity<>(coteDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/matchs/{matchId}/cotes/{coteId}")
	public ResponseEntity<?> deleteOneCoteByMatch(@PathVariable("matchId") Long matchId,
			@PathVariable("coteId") Long coteId){
		
		matchService.getOne(matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found with id: " + matchId));
		
		coteService.deleteOneCoteByMatch(coteId, matchId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cote not found with id: " + coteId));
		
		return new ResponseEntity<>("DELETED successfully !", HttpStatus.OK);
	}
}
