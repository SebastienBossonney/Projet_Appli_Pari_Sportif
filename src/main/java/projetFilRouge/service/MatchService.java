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
import projetFilRouge.model.Match;
import projetFilRouge.model.Utilisateur;
import projetFilRouge.repository.IMatchRepository;
import projetFilRouge.repository.ISportRepository;

@Service(value = "matchService")
public class MatchService /* implements IService<Match> */ {

	@Autowired
	private ISportRepository sportRepository;
	@Autowired
	private IMatchRepository matchRepository;

	public Optional<Match> getOne(Long id) {
		return matchRepository.findById(id);
	}

	
	public List<Match> getMatchsBySport(Long sportId) {

		return matchRepository.findBySportId(sportId);
	}

	public List<Match> getMatchsSuivantesBySport(Long sportId) {

		List<Match> matchs = matchRepository.findBySportId(sportId);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String now1 = LocalDateTime.now().format(dtf).toString();
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		Date today = null;

		try {
			today = sdfDate.parse(now1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DateTimeFormatter dtfh = DateTimeFormatter.ofPattern("HH:mm");
		String nowHeure = LocalDateTime.now().format(dtfh).toString();
		SimpleDateFormat sdfHeure = new SimpleDateFormat("HH:mm");
		Date todayHeure = null;

		try {
			todayHeure = sdfHeure.parse(nowHeure);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// meter hora y fecha en la misma propiedad de la clase
		List<Match> matchsResultat = new ArrayList<Match>();
		for (Match m : matchs) {
			if ((m.getDateMatch().compareTo(today) >= 0) && (m.getHeureMatch().compareTo(todayHeure) >= 0))

			// System.out.println(m.getHeureMatch().toString());
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
	public Optional<Match> saveMatchBySport(Long sportId, Match match) {
		return sportRepository.findById(sportId).map(sport -> {
			match.setSport(sport);
			return matchRepository.save(match);
		});
	}

	
	  public Optional<Match> editOneMatch(Long matchId, Long sportId, MatchDto matchdto)
	  { 
		  return matchRepository.findByIdAndSportId(matchId, sportId).map(matchToUpdate -> {
	  
	 //matchRepository.findByIdAndSportId(matchId, sportId); 
	  
	  matchToUpdate.setDateMatch(matchdto.getDateMatch());
	  
	  matchToUpdate.setHeureMatch(matchdto.getHeureMatch());
	  
	  matchToUpdate.setLieu(matchdto.getLieu());
	  matchToUpdate.setVille(matchdto.getVille());
	  matchToUpdate.setPays(matchdto.getPays()); 
	  
	  return matchRepository.save(matchToUpdate);
	  }); 
	  }
	  
	  
	  public Optional<?> deleteOneMatcBySport(Long matchId, Long sportId) 
	  {
		return matchRepository.findByIdAndSportId(matchId, sportId).map(matchToDelete -> {
				
				matchRepository.delete(matchToDelete);
				
				return "DELETED";
			});
		}
	 
	

}
