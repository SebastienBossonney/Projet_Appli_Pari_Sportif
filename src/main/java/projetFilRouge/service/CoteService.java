package projetFilRouge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetFilRouge.dto.CoteDto;
import projetFilRouge.model.Cote;
import projetFilRouge.model.Match;
import projetFilRouge.repository.ICoteRepository;
import projetFilRouge.repository.IMatchRepository;

@Service(value = "coteService")
public class CoteService {

	@Autowired
	ICoteRepository coteRepository;

	@Autowired
	IMatchRepository matchRepository;

	public List<Cote> getCotesByMatch(Long matchId) {

		return coteRepository.findByMatchId(matchId);
		
	}

	public Optional<Cote> getOneCoteByMatch(Long coteId, Long matchId) {
		return coteRepository.findByIdAndMatchId(coteId, matchId);
		
	}

	public Optional<Cote> saveCoteByMatch(Long matchId, Cote cote) {
		return matchRepository.findById(matchId).map(match -> {
			cote.setMatch(match);
			return coteRepository.save(cote);
		});
	}
	
	public Optional<List<Cote>> saveCoteListByMatch(Long matchId, List<Cote> coteList) {

		List<Cote> listCote = new ArrayList<Cote>();
		
		return matchRepository.findById(matchId).map(match -> {
			
			for (Cote c: coteList) {
				c.setMatch(match);
			    Cote cote = coteRepository.save(c);
			    listCote.add(cote);
				}
			return listCote;
		});
	}


	public Optional<Cote> editOneCoteByMacth(Long coteId, Long matchId, Cote cote) {
		return coteRepository.findByIdAndMatchId(coteId, matchId).map(coteToUpdate -> {
			coteToUpdate.setStatut(cote.getStatut());
			coteToUpdate.setValeur(cote.getValeur());
			return coteRepository.save(coteToUpdate);
		});
	}

	public Optional<?> deleteOneCoteByMatch(Long coteId, Long matchId) {
		return coteRepository.findByIdAndMatchId(coteId, matchId).map(coteToDelete -> {
			coteRepository.delete(coteToDelete);
			return "DELETED";
		});
	}
	
	public Optional<Cote> getCoteById (Long coteId)
	{
		return this.coteRepository.findById(coteId);
	}

	
}
