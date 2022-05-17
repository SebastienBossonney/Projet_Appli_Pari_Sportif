package projetFilRouge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import projetFilRouge.dto.AvertissementDto;
import projetFilRouge.dto.LimiteDto;
import projetFilRouge.dto.UtilisateurDto;
import projetFilRouge.model.Avertissement;
import projetFilRouge.model.Limite;
import projetFilRouge.model.Profil;
import projetFilRouge.model.Role;
import projetFilRouge.model.Utilisateur;
import projetFilRouge.service.UtilisateurService;

@RestController
@CrossOrigin(origins = "*")
public class UtilisateurRestController {

	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping(value = "/utilisateurs")
	public ResponseEntity<List<UtilisateurDto>> getUsers() {
		
		List<Utilisateur> listUtilisateurs = utilisateurService.findAll();
		List<UtilisateurDto> listDtoU = new ArrayList<UtilisateurDto>();
		//List<AvertissementDto> listAvertissementDtoU = new ArrayList<AvertissementDto>();
		
		for(Utilisateur u : listUtilisateurs)
		{
			UtilisateurDto  uDto = new UtilisateurDto();
			uDto.setIdentifiant(u.getIdentifiant());
			uDto.setEmail(u.getEmail());
			uDto.setMotDePasse(u.getMotDePasse()); 
			uDto.setRole(u.getRole());  
			uDto.setProfil(u.getProfil());
			uDto.setMontantTotalGagne(u.getMontantTotalGagne());
			uDto.setMontantTotalPerdu(u.getMontantTotalPerdu());
			uDto.setSalaire(u.getSalaire());
			uDto.setMontantDisponible(u.getMontantDisponible());
			uDto.setId(u.getId());
			
			if (u.getLimite()!=null) {
            LimiteDto limiteDto = new LimiteDto(u.getLimite().getValeur(), u.getLimite().getDuree(),u.getId());
		     limiteDto.setId(u.getLimite().getId());                                        
			uDto.setLimite(limiteDto);                                          
			}
			listDtoU.add(uDto);
				
		}
		
		return new ResponseEntity<>(listDtoU, HttpStatus.OK);
	}

//	@PutMapping(value = "/utilisateurs/{id}")
//	public ResponseEntity<Utilisateur> updateMontant(@PathVariable("id") Long id,
//			@RequestBody Utilisateur utilisateur) {
//
//		Utilisateur userToUpdate = utilisateurService.getOne(id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
//
//		double newMontant = userToUpdate.getMontantDisponible() + utilisateur.getMontantDisponible();
//		userToUpdate.setMontantDisponible(newMontant);
//
//		return new ResponseEntity<>(utilisateurService.saveOrUpdate(userToUpdate), HttpStatus.OK);
//	}

	@PostMapping(value = "/utilisateurs")
	public ResponseEntity<UtilisateurDto> createUser(@Valid @RequestBody UtilisateurDto utilisateurDto) {

		Utilisateur userToSave = new Utilisateur();

		userToSave.setIdentifiant(utilisateurDto.getIdentifiant());
		userToSave.setEmail(utilisateurDto.getEmail());
		userToSave.setMotDePasse(utilisateurDto.getMotDePasse());
		userToSave.setRole(utilisateurDto.getRole());
		userToSave.setProfil(utilisateurDto.getProfil());
		userToSave.setSalaire(utilisateurDto.getSalaire());
		userToSave.setMontantTotalGagne(utilisateurDto.getMontantTotalGagne());
		userToSave.setMontantTotalPerdu(utilisateurDto.getMontantTotalPerdu());
		userToSave.setMontantDisponible(utilisateurDto.getMontantDisponible());

		userToSave = utilisateurService.saveOrUpdate(userToSave);
		
		utilisateurDto.setId(userToSave.getId());
		
		return new ResponseEntity<>(utilisateurDto, HttpStatus.CREATED);
	}

	@GetMapping(value = "/utilisateurs/{id}")
	public ResponseEntity<UtilisateurDto> getUser(@PathVariable("id") Long id) {

		Utilisateur user = utilisateurService.getOne(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
		
		//List<AvertissementDto> listAvertissementDtoU = new ArrayList<AvertissementDto>();
		
		UtilisateurDto  uDto = new UtilisateurDto();
		uDto.setIdentifiant(user.getIdentifiant());
		uDto.setEmail(user.getEmail());
		uDto.setMotDePasse(user.getMotDePasse()); 
		uDto.setRole(user.getRole());  
		uDto.setProfil(user.getProfil());
		uDto.setMontantTotalGagne(user.getMontantTotalGagne());
		uDto.setMontantTotalPerdu(user.getMontantTotalPerdu());
		uDto.setSalaire(user.getSalaire());
		uDto.setMontantDisponible(user.getMontantDisponible());
		uDto.setId(user.getId());
		
		if (user.getLimite()!=null) {
        LimiteDto limiteDto = new LimiteDto(user.getLimite().getValeur(), user.getLimite().getDuree(),user.getId());
	     limiteDto.setId(user.getLimite().getId());                                        
		uDto.setLimite(limiteDto);  
		}
		uDto.setId(user.getId());
		
		return new ResponseEntity<>(uDto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/utilisateurs/{id}")
	public ResponseEntity<UtilisateurDto> editUser(@PathVariable("id") Long id,
			@Valid @RequestBody UtilisateurDto utilisateurDto) {

		Utilisateur userToUpdate = utilisateurService.getOne(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

		userToUpdate.setIdentifiant(utilisateurDto.getIdentifiant());
	 	userToUpdate.setEmail(utilisateurDto.getEmail());
		userToUpdate.setMotDePasse(utilisateurDto.getMotDePasse());
		userToUpdate.setRole(utilisateurDto.getRole());
		userToUpdate.setProfil(utilisateurDto.getProfil());
		userToUpdate.setSalaire(utilisateurDto.getSalaire());
		userToUpdate.setMontantTotalGagne(utilisateurDto.getMontantTotalGagne());
		userToUpdate.setMontantTotalPerdu(utilisateurDto.getMontantTotalPerdu());
		userToUpdate.setMontantDisponible(utilisateurDto.getMontantDisponible());
		
		utilisateurDto.setId(id);
		
		return new ResponseEntity<>(utilisateurDto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/utilisateurs/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {

		Utilisateur userToDelete = utilisateurService.getOne(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

		utilisateurService.deleteUser(userToDelete);
		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
	}

	@GetMapping(value = "/utilisateurs/{identifiant}/{password}")
	public ResponseEntity<UtilisateurDto> findByIdentifiantAndPassword(
			@PathVariable("identifiant") String identifiant, @PathVariable("password") String password) 
	{
		Utilisateur user = utilisateurService.findByIdentifiantAndPassword(identifiant, password)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with identifiant" + identifiant + "and password " + password));
		
		UtilisateurDto  uDto = new UtilisateurDto();
		uDto.setIdentifiant(user.getIdentifiant());
		uDto.setEmail(user.getEmail());
		uDto.setMotDePasse(user.getMotDePasse()); 
		uDto.setRole(user.getRole());  
		uDto.setProfil(user.getProfil());
		uDto.setMontantTotalGagne(user.getMontantTotalGagne());
		uDto.setMontantTotalPerdu(user.getMontantTotalPerdu());
		uDto.setSalaire(user.getSalaire());
		uDto.setMontantDisponible(user.getMontantDisponible());
		uDto.setId(user.getId());
		
		if (user.getLimite()!=null) {
	        LimiteDto limiteDto = new LimiteDto(user.getLimite().getValeur(), user.getLimite().getDuree(),user.getId());
		     limiteDto.setId(user.getLimite().getId());                                        
			uDto.setLimite(limiteDto);  
			}
        
		
		uDto.setId(user.getId());
		
		return new ResponseEntity<UtilisateurDto>(uDto, HttpStatus.OK);
	}

	@GetMapping(value = "/utilisateurs/motDePasseOublie/{email}")
	public ResponseEntity<UtilisateurDto> findByEmail(@PathVariable("email") String email) {
		
		Utilisateur user = utilisateurService.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email" + email));
		
		UtilisateurDto  uDto = new UtilisateurDto();
		uDto.setIdentifiant(user.getIdentifiant());
		uDto.setEmail(user.getEmail());
		uDto.setMotDePasse(user.getMotDePasse()); 
		uDto.setRole(user.getRole());  
		uDto.setProfil(user.getProfil());
		uDto.setMontantTotalGagne(user.getMontantTotalGagne());
		uDto.setMontantTotalPerdu(user.getMontantTotalPerdu());
		uDto.setSalaire(user.getSalaire());
		uDto.setMontantDisponible(user.getMontantDisponible());
		uDto.setId(user.getId());
		
		if (user.getLimite()!=null) {
	        LimiteDto limiteDto = new LimiteDto(user.getLimite().getValeur(), user.getLimite().getDuree(),user.getId());
		     limiteDto.setId(user.getLimite().getId());                                        
			uDto.setLimite(limiteDto);  
			}
      
		uDto.setId(user.getId());
        
		return new ResponseEntity<UtilisateurDto>(uDto, HttpStatus.OK);
	}
}
