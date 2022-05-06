package projetFilRouge.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetFilRouge.dto.MatchDto;
import projetFilRouge.model.Equipe;
import projetFilRouge.model.EquipeMatch;
import projetFilRouge.model.Match;
import projetFilRouge.repository.IEquipeMatchRepository;
import projetFilRouge.repository.IMatchRepository;
import projetFilRouge.repository.ISportRepository;

@Service(value = "matchService")
   public class MatchService /* implements IService<Match> */  {

	@Autowired
	private ISportRepository sportRepository;
	@Autowired
	private IMatchRepository matchRepository;
	@Autowired
	private IEquipeMatchRepository equipeMatchRepository;
	
	@Autowired
	private EquipeMatchService equipeMatchService;
	
	public List<Match> getMatchsBySport(Long sportId) {

		return matchRepository.findBySportId(sportId);
	}

	public List<Match> getMatchsSuivantesBySport(Long sportId) 
	{

		List<Match> matchs = matchRepository.findBySportId(sportId);

		Date today = new Date();
		
		List<Match> matchsResultat = new ArrayList<Match>();
		
		for (Match m : matchs) 
		{
			Date dateTest = null;
			 
			 SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			  
			  try { dateTest = sdfDate.parse(m.getDateMatch().toString() + " " + m.getHeureMatch().toString()); } catch (ParseException e) {
			  e.printStackTrace(); }
			 
			//check if the current date and time is before the Match Date, then i create a List of Matchs
				if (today.before(dateTest))
			{
				matchsResultat.add(m);
			}
				
				}


		return matchsResultat;

	}

	// Retourne une voiture(id) selon son proprietaire(id)
	public Optional<Match> getOneMatchBySport(Long matchId, Long sportId) {
		return matchRepository.findByIdAndSportId(matchId, sportId);
	}

	// Enregistre une voiture selon son proprietaire(id)
	public Optional<Match> saveMatchBySport(Long sportId, Match match, List<Equipe> equipes) {

		return sportRepository.findById(sportId).map(sport -> {
			match.setSport(sport);

			Match matchResult = matchRepository.save(match);
			
			matchResult.setEquipeMatchs(equipeMatchService.setEquipesToMatch(matchResult, equipes));

			return matchResult;
		});
	}

	public Optional<Match> editOneMatch(Long matchId, Long sportId, MatchDto matchdto) {
		return matchRepository.findByIdAndSportId(matchId, sportId).map(matchToUpdate -> {

			// matchRepository.findByIdAndSportId(matchId, sportId);

			matchToUpdate.setDateMatch(matchdto.getDateMatch());

			matchToUpdate.setHeureMatch(matchdto.getHeureMatch());

			matchToUpdate.setLieu(matchdto.getLieu());
			matchToUpdate.setVille(matchdto.getVille());
			matchToUpdate.setPays(matchdto.getPays());
			
			matchToUpdate =  matchRepository.save(matchToUpdate);
			matchToUpdate.setEquipeMatchs(equipeMatchService.updateEquipesToMatch(matchToUpdate,matchdto.getEquipes()));

			return matchToUpdate;
		});
	}

	public Optional<?> deleteOneMatcBySport(Long matchId, Long sportId) {
		return matchRepository.findByIdAndSportId(matchId, sportId).map(matchToDelete -> {

			matchRepository.delete(matchToDelete);

			return "DELETED";
		});
	}
	
	public Optional<Match> getOne (Long Id)
	{
		return matchRepository.findById(Id);
	}

}
