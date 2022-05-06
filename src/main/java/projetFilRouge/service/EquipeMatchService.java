package projetFilRouge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetFilRouge.model.Equipe;
import projetFilRouge.model.EquipeMatch;
import projetFilRouge.model.Match;
import projetFilRouge.repository.IEquipeMatchRepository;

@Service(value = "equipeMatchService")
public class EquipeMatchService {
	
	@Autowired
	private IEquipeMatchRepository equipeMatchRepository;
	
	public List<Equipe> getAllEquipesByMatchId (Long matchId)
	{
		List<EquipeMatch> em = equipeMatchRepository.findAllByMatchId(matchId);
		
		Equipe e1 = em.get(0).getEquipe();
		Equipe e2 = em.get(1).getEquipe();
		
		List<Equipe> equipes = new ArrayList<Equipe>();
		equipes.add(e1);
		equipes.add(e2);
		
		return equipes;
	}
	
	public List<EquipeMatch> setEquipesToMatch (Match match, List<Equipe> equipes)
	{
		EquipeMatch equipeMatch1 = new EquipeMatch();
		equipeMatch1.setEquipe(equipes.get(0));
		equipeMatch1.setMatch(match);
		
		EquipeMatch equipeMatch2 = new EquipeMatch();
		equipeMatch2.setEquipe(equipes.get(1));
		equipeMatch2.setMatch(match);

		equipeMatch1 = equipeMatchRepository.save(equipeMatch1);
		equipeMatch2 = equipeMatchRepository.save(equipeMatch2);

		List<EquipeMatch> equipeMatchs = new ArrayList();

		equipeMatchs.add(equipeMatch1);
		equipeMatchs.add(equipeMatch2);
		
		return equipeMatchs;
	}
	
	public List<EquipeMatch> updateEquipesToMatch (Match match, List<Equipe> equipes)
	{
		//get all equipeMatch by match
		List<EquipeMatch> em = equipeMatchRepository.findAllByMatchId(match.getId());
		//erase all equipeMatch by Match
		em.get(0).setEquipe(equipes.get(0));
		System.out.println(em.get(0).getId());
		System.out.println((equipes.get(0).getId()));
		em.get(1).setEquipe(equipes.get(1));
		equipeMatchRepository.saveAll(em);
		
		return em;
		
	}

}
