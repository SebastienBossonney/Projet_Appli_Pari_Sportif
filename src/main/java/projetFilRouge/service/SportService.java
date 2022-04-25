package projetFilRouge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import projetFilRouge.model.Sport;
import projetFilRouge.repository.ISportRepository;

@Service(value = "sportService")
public class SportService implements IService<Sport>  {

	@Autowired
	private ISportRepository sportRepository;
	
	
	@Override
	public List<Sport> findAll() {
		return sportRepository.findAll();
	}

	@Override
	public Sport saveOrUpdate(Sport sp) {
		return sportRepository.save(sp);
	}

	@Override
	public Optional<Sport> getOne(Long id) {
		return sportRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		sportRepository.deleteById(id);
		
	}

	



}
