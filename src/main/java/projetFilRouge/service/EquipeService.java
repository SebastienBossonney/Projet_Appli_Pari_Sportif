package projetFilRouge.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import projetFilRouge.model.Equipe;
import projetFilRouge.repository.IEquipeRepository;
import projetFilRouge.repository.ISportRepository;

@Service(value = "equipeService")
public class EquipeService implements IService<Equipe> {
	
	@Autowired
	private IEquipeRepository equipeRepository;
	
	@Autowired
	private ISportRepository sportRepository;
	
	
	
	@Override
	public List<Equipe> findAll() {
		return equipeRepository.findAll();
	}
	
	public Equipe getEquipeById(Long equipeId) {
		return equipeRepository.findById(equipeId).get();
	}

	public Optional<Equipe> getOneEquipeBySport(Long equipeId, Long sportId) {
		return equipeRepository.findByIdAndSportId(equipeId, sportId);
	}
	
	public List <Equipe> getEquipesBySport(Long sportId) {
		return equipeRepository.findBySportId(sportId);
	}
	
	@Override
	public Equipe saveOrUpdate(Equipe equipe) {
		return equipeRepository.save(equipe);
	}
	
	public Optional<Equipe> saveEquipeBySport(Long sportId, Equipe equipe) {
		return sportRepository.findById(sportId).map(sport -> {
			equipe.setSport(sport);
			return equipeRepository.save(equipe);
		});
	}
	
	@Override
	public Optional<Equipe> getOne(Long id) {
		return equipeRepository.findById(id);
	}
	
	public Optional<Equipe> editOneEquipeBySport(Long equipeId, Long sportId, Equipe equipe) {
		return equipeRepository.findByIdAndSportId(equipeId, sportId).map(equipeToUpdate -> {
			equipeToUpdate.setNom(equipe.getNom());
			return equipeRepository.save(equipeToUpdate);
		});
	}
	
	@Override
	public void delete(Long id) {
		equipeRepository.deleteById(id);
	}

	public Optional<?> deleteOneEquipeBySport(Long equipeId, Long sportId) {
		return equipeRepository.findByIdAndSportId(equipeId, sportId).map(equipeToDelete -> {
			equipeRepository.delete(equipeToDelete);
			return "DELETED";
		});

	
	}



}

