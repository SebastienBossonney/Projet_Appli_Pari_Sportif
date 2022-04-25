package projetFilRouge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import projetFilRouge.model.Avertissement;
import projetFilRouge.model.ChoixCote;
import projetFilRouge.model.ChoixResultat;
import projetFilRouge.model.ChoixSport;
import projetFilRouge.model.Cote;
import projetFilRouge.model.Equipe;
import projetFilRouge.model.EquipeMatch;
import projetFilRouge.model.Joueur;
import projetFilRouge.model.Limite;
import projetFilRouge.model.Match;
import projetFilRouge.model.Pari;
import projetFilRouge.model.Profil;
import projetFilRouge.model.Resultat;
import projetFilRouge.model.Role;
import projetFilRouge.model.Sport;
import projetFilRouge.model.Utilisateur;
import projetFilRouge.repository.IAvertissementRepository;
import projetFilRouge.repository.ICoteRepository;
import projetFilRouge.repository.IEquipeMatchRepository;
import projetFilRouge.repository.IEquipeRepository;
import projetFilRouge.repository.IJoueurRepository;
import projetFilRouge.repository.ILimiteRepository;
import projetFilRouge.repository.IMatchRepository;
import projetFilRouge.repository.IPariRepository;
import projetFilRouge.repository.IResultatRepository;
import projetFilRouge.repository.ISportRepository;
import projetFilRouge.repository.IUtilisateurRepository;

@SpringBootTest
class ProjetFilRougeApplicationTests {
	@Autowired
	private IUtilisateurRepository utilisateurRepo;
	@Autowired
	private IAvertissementRepository avertissementRepo;
	@Autowired
	private ILimiteRepository limiteRepo;
	@Autowired
	private IPariRepository pariRepo;
	@Autowired
	private IResultatRepository resultatRepo;
	@Autowired
	private IMatchRepository matchRepo;
	@Autowired
	private ICoteRepository coteRepo;
	@Autowired
	private ISportRepository sportRepo;
	@Autowired
	private IEquipeRepository equipeRepo;
	@Autowired
	private IJoueurRepository joueurRepo;
	@Autowired
	private IEquipeMatchRepository equipeMatchRepo;

	@Test
	void testUtilisateur() {
		int start = utilisateurRepo.findAll().size();

		Utilisateur parieur = new Utilisateur("parieur");
		parieur.setEmail("parieur@orange.fr");
		parieur.setMontantTotalGagne(350);
		parieur.setMontantTotalPerdu(250);
		parieur.setMotDePasse("motdepasse");
		parieur.setRole(Role.PARIEUR);
		parieur.setProfil(Profil.RISQUE);

		parieur = utilisateurRepo.save(parieur);
		parieur = utilisateurRepo.findById(parieur.getId()).get();

		assertEquals("parieur", parieur.getIdentifiant());
		assertEquals("parieur@orange.fr", parieur.getEmail());
		assertEquals(350.0, parieur.getMontantTotalGagne());
		assertEquals(250.0, parieur.getMontantTotalPerdu());
		assertEquals("motdepasse", parieur.getMotDePasse());
		assertEquals(Role.PARIEUR, parieur.getRole());
		assertEquals(Profil.RISQUE, parieur.getProfil());

		parieur.setIdentifiant("John");
		parieur.setEmail("john@orange.fr");
		parieur.setMontantTotalGagne(555);
		parieur.setMontantTotalPerdu(666);
		parieur.setMotDePasse("password");
		parieur.setRole(Role.ADMIN);
		parieur.setProfil(Profil.SANSRISQUE);

		parieur = utilisateurRepo.save(parieur);
		parieur = utilisateurRepo.findById(parieur.getId()).get();


		assertEquals("John", parieur.getIdentifiant());
		assertEquals("john@orange.fr", parieur.getEmail());
		assertEquals(555.0, parieur.getMontantTotalGagne());
		assertEquals(666.0, parieur.getMontantTotalPerdu());
		assertEquals("password", parieur.getMotDePasse());
		assertEquals(Role.ADMIN, parieur.getRole());
		assertEquals(Profil.SANSRISQUE, parieur.getProfil());

		int end = utilisateurRepo.findAll().size();

		assertEquals(1, end - start);

		utilisateurRepo.delete(parieur);

	}

	@Test
	void testLimite() {
		int start = limiteRepo.findAll().size();

		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCreneau = null;
		try {
			dateCreneau = sdfDate.parse("04/04/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Limite limite = new Limite(500.0);

		limite.setDuree(dateCreneau);

		limite = limiteRepo.save(limite);
		limite = limiteRepo.findById(limite.getId()).get();

		assertEquals(500.0, limite.getValeur());
		assertEquals(dateCreneau, limite.getDuree());

		limite.setValeur(150);
		try {
			dateCreneau = sdfDate.parse("04/04/2021");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limite.setDuree(dateCreneau);
		limite = limiteRepo.save(limite);
		limite = limiteRepo.findById(limite.getId()).get();
		

		assertEquals(150.0, limite.getValeur());
		assertEquals(dateCreneau, limite.getDuree());

		int end = limiteRepo.findAll().size();

		assertEquals(1, end - start);

		limiteRepo.delete(limite);
	}

	@Test
	void testAvertissement() {
		int start = avertissementRepo.findAll().size();

		Avertissement avertissement = new Avertissement();

		avertissement.setDescription("Attention, de nombreux paris en un cours laps de temps");

		avertissement = avertissementRepo.save(avertissement);
		avertissement = avertissementRepo.findById(avertissement.getId()).get();

		assertEquals("Attention, de nombreux paris en un cours laps de temps", avertissement.getDescription());

		avertissement.setDescription("Pari trop élevé");

		avertissement = avertissementRepo.save(avertissement);

		assertEquals("Pari trop élevé", avertissement.getDescription());

		int end = avertissementRepo.findAll().size();

		assertEquals(1, end - start);

		avertissementRepo.delete(avertissement);

	}

	@Test
	void testUtilisateurAvertissement() {

		Utilisateur parieur = new Utilisateur("parieur");

		parieur.setEmail("parieur@orange.fr");
		parieur.setMontantTotalGagne(350);
		parieur.setMontantTotalPerdu(250);
		parieur.setMotDePasse("motdepasse");
		parieur.setRole(Role.PARIEUR);
		parieur.setProfil(Profil.RISQUE);
		parieur = utilisateurRepo.save(parieur);
		parieur = utilisateurRepo.findById(parieur.getId()).get();
		
		Avertissement avertissement = new Avertissement();
		avertissement.setDescription("Attention, de nombreux paris en un cours laps de temps");
		avertissement.setUtilisateur(parieur);
		avertissement = avertissementRepo.save(avertissement);
		avertissement = avertissementRepo.findById(avertissement.getId()).get();

		assertNotNull(avertissement.getUtilisateur().getIdentifiant());
		assertEquals("parieur", avertissement.getUtilisateur().getIdentifiant());
		
//		int size = utilisateurRepo.findById(avertissement.getId()).size();
//		assertEquals(1, size);
		
		
		avertissementRepo.delete(avertissement);
		utilisateurRepo.delete(parieur);
		int start = avertissementRepo.findAll().size();
		assertEquals(0,  start);

	}

	@Test
	void testUtilisateurLimite() {
		Utilisateur parieur= new Utilisateur("testUtilisateurLimite");

		parieur.setEmail("parieur@orange.fr");
		parieur.setMontantTotalGagne(350);
		parieur.setMontantTotalPerdu(250);
		parieur.setMotDePasse("motdepasse");
		parieur.setRole(Role.PARIEUR);
		parieur.setProfil(Profil.RISQUE);

		parieur = utilisateurRepo.save(parieur);
		parieur = utilisateurRepo.findById(parieur.getId()).get();

		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCreneau = null;
		try {
			dateCreneau = sdfDate.parse("04/04/2022");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Limite limite = new Limite(500.0);

		limite.setDuree(dateCreneau);

		limite.setUtilisateur(parieur);

		
		limite = limiteRepo.save(limite);
		
		parieur.setLimite(limite);
		parieur = utilisateurRepo.save(parieur);
		
		assertNotNull(limite.getUtilisateur().getIdentifiant());
		assertEquals("testUtilisateurLimite", limite.getUtilisateur().getIdentifiant());
		
		utilisateurRepo.delete(parieur);
		limiteRepo.delete(limite);
		
		int start = avertissementRepo.findAll().size();
		assertEquals(0,  start);
	}

	@Test
	void testJoueur() {
		int start = joueurRepo.findAll().size();

		Joueur mbappe = new Joueur();
		mbappe.setNom("Kylian Mbappé");
		mbappe.setNbBut(3);

		mbappe = joueurRepo.save(mbappe);

		mbappe = joueurRepo.findById(mbappe.getId()).get();

		assertEquals("Kylian Mbappé", mbappe.getNom());
		assertEquals(3, mbappe.getNbBut());

		mbappe.setNbBut(5);
		mbappe = joueurRepo.save(mbappe);

		mbappe = joueurRepo.findById(mbappe.getId()).get();

		assertEquals("Kylian Mbappé", mbappe.getNom());
		assertEquals(5, mbappe.getNbBut());

		int end = joueurRepo.findAll().size();

		assertEquals(1, end - start);

		Equipe psg = new Equipe();
		psg.setNom("Paris Saint-Germain");

		psg = equipeRepo.save(psg);

		psg = equipeRepo.findById(psg.getId()).get();

		assertEquals("Paris Saint-Germain", psg.getNom());

		mbappe.setEquipe(psg);

		mbappe = joueurRepo.save(mbappe);

		assertEquals(psg.getId(), mbappe.getEquipe().getId());

	}

	@Test
	void testSport() {

		int start = sportRepo.findAll().size();

		Sport football = new Sport();
		football.setNomSport(ChoixSport.FOOTBALL);

		football = sportRepo.save(football);

		football = sportRepo.findById(football.getId()).get();

		assertEquals(ChoixSport.FOOTBALL, football.getNomSport());

		int end = sportRepo.findAll().size();

		assertEquals(1, end - start);

		sportRepo.delete(football);

	}

	@Test
	void testSportEquipe() {

		Sport football = new Sport();
		football.setNomSport(ChoixSport.FOOTBALL);
		football = sportRepo.save(football);

		Equipe equipe = new Equipe();

		equipe.setNom("Marseille");

		equipe = equipeRepo.save(equipe);

		equipe.setSport(football);

		equipe = equipeRepo.save(equipe);

		equipe = equipeRepo.findById(equipe.getId()).get();

		assertEquals(football.getId(), equipe.getSport().getId());

	}

	@Test
	void testCote() {
		int start = coteRepo.findAll().size();

		Cote cote = new Cote();
		cote.setValeur(1.4);
		cote.setStatut(ChoixCote.GAGNANT);

		cote = coteRepo.save(cote);

		cote = coteRepo.findById(cote.getId()).get();

		assertEquals(1.4, cote.getValeur());
		assertEquals(ChoixCote.GAGNANT, cote.getStatut());

		int end = coteRepo.findAll().size();

		assertEquals(1, end - start);

		coteRepo.delete(cote);

	}

	@Test
	void testCoteJoueur() {

		Cote cote = new Cote();
		cote.setValeur(1.4);
		cote.setStatut(ChoixCote.GAGNANT);

		cote = coteRepo.save(cote);

		Joueur joueur = new Joueur();
		joueur.setNom("Dimitri Payet");
		joueur.setNbBut(10);

		joueur = joueurRepo.save(joueur);

		joueur.setCote(cote);

		joueur = joueurRepo.save(joueur);

		joueur = joueurRepo.findById(joueur.getId()).get();

		assertEquals(cote.getId(), joueur.getCote().getId());

	}

	@Test
	void testSportMatch() {
		Sport football = new Sport();
		football.setNomSport(ChoixSport.FOOTBALL);

		football = sportRepo.save(football);

		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCreneau = null;
		try {
			dateCreneau = sdfDate.parse("04/04/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat sdfHeure = new SimpleDateFormat("HH:mm");
		Date heureMatch = null;

		try {
			heureMatch = sdfHeure.parse("20:45");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Match match = new Match();
		match.setDateMatch(dateCreneau);
		match.setHeureMatch(heureMatch);
		match.setLieu("Parc des Princes");
		match.setVille("Paris");
		match.setPays("France");

		match = matchRepo.save(match);

		match = matchRepo.findById(match.getId()).get();

		assertEquals(dateCreneau, match.getDateMatch());
		assertEquals(heureMatch, match.getHeureMatch());
		assertEquals("Parc des Princes", match.getLieu());
		assertEquals("Paris", match.getVille());
		assertEquals("France", match.getPays());

		match.setSport(football);

		match = matchRepo.save(match);

		match = matchRepo.findById(match.getId()).get();

		assertEquals(football.getId(), match.getSport().getId());

	}

	@Test
	void testEquipeMatch() {

		Equipe equipe = new Equipe();
		equipe.setNom("Paris Saint-Germain");

		equipe = equipeRepo.save(equipe);

		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCreneau = null;
		try {
			dateCreneau = sdfDate.parse("04/04/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat sdfHeure = new SimpleDateFormat("HH:mm");
		Date heureMatch = null;

		try {
			heureMatch = sdfHeure.parse("20:45");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Match match = new Match();
		match.setDateMatch(dateCreneau);
		match.setHeureMatch(heureMatch);
		match.setLieu("Parc des Princes");
		match.setVille("Paris");
		match.setPays("France");

		match = matchRepo.save(match);

		EquipeMatch equipeMatch = new EquipeMatch(equipe, match);
		equipeMatch = equipeMatchRepo.save(equipeMatch);

//		int size = equipeMatchRepo.findAllByEquipe(equipe.getId()).size();
//
//		assertEquals(1, size);
	}

	@Test

	void Pari() {

		Date datePari = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm");
		Date heurePari = new Date();

		try {
			datePari = dateFormat.parse("09/09/1980");
			heurePari = heureFormat.parse("13:09");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Pari pari = new Pari();
		pari.setMontantJoue(18.50);
		pari.setDatePari(datePari);
		pari.setHeurePari(heurePari);
		pari.setResultat(ChoixResultat.GAGNANT);
		pari.setMontantResultat(23);

		pari = pariRepo.save(pari);

		pari = pariRepo.findById(pari.getId()).get();

		assertEquals(18.5, pari.getMontantJoue());
		assertEquals(datePari, pari.getDatePari());
		assertEquals(heurePari, pari.getHeurePari());
		assertEquals(ChoixResultat.GAGNANT, pari.getResultat());
		assertEquals(23, pari.getMontantResultat());

		pariRepo.delete(pari);

		Optional<Pari> optPari = pariRepo.findById(pari.getId());

		if (optPari.isPresent()) {
			fail("Suppression lieu en erreur");
		}

	}

	@Test

	void Equipe() {

		Equipe equipe1 = new Equipe();
		Equipe equipe2 = new Equipe();
		Equipe equipe3 = new Equipe();

		equipe1.setNom("Barcelona");
		equipe2.setNom("Madrid");
		equipe3.setNom("Paris St Germain");

		equipe1 = equipeRepo.save(equipe1);
		equipe2 = equipeRepo.save(equipe2);
		equipe3 = equipeRepo.save(equipe3);

		int size = equipeRepo.findAll().size();
		//assertEquals(size, size);
	}

	@Test
	void Match() {

		Match match = new Match();

		Date dateMatch = new Date();
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat heureFormat3 = new SimpleDateFormat("HH:mm");
		Date heureMatch = new Date();

		try {
			dateMatch = dateFormat3.parse("09/12/1990");
			heureMatch = heureFormat3.parse("12:09");
		} catch (Exception e) {
			e.printStackTrace();
		}

		match.setDateMatch(dateMatch);
		match.setHeureMatch(heureMatch);
		match.setLieu("Cap Nou");
		match.setVille("Barcelona");
		match.setPays("Espagne");

		match = matchRepo.save(match);

		int size = matchRepo.findAll().size();
		//assertEquals(1, size);
	}

	@Test
	void MatchEquipes() {

		EquipeMatch equipeMatch = new EquipeMatch();

		Equipe equipe = new Equipe();
		equipe.setNom("Barcelona");
		equipe = equipeRepo.save(equipe);
		

		Match match = new Match();

		Date dateMatch = new Date();
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat heureFormat3 = new SimpleDateFormat("HH:mm");
		Date heureMatch = new Date();

		try {
			dateMatch = dateFormat3.parse("09/12/1990");
			heureMatch = heureFormat3.parse("12:09");
		} catch (Exception e) {
			e.printStackTrace();
		}

		match.setDateMatch(dateMatch);
		match.setHeureMatch(heureMatch);
		match.setLieu("Cap Nou");
		match.setVille("Barcelona");
		match.setPays("Espagne");
		
		match = matchRepo.save(match);
		
		equipeMatch.setMatch(match);
		equipeMatch.setEquipe(equipe);

		equipeMatch = equipeMatchRepo.save(equipeMatch);

		//int size = matchRepo.findAll().size(); 
		
		//assertEquals(1, size);
		assertEquals("Barcelona", equipeMatch.getEquipe().getNom());
		assertEquals("Barcelona", equipeMatch.getMatch().getVille());

		EquipeMatch equipeMatch1 = new EquipeMatch();

		equipe.setNom("Paris St Germain");
		equipeMatch1.setEquipe(equipe);

		equipeMatch1.setMatch(match);

		equipeMatch1 = equipeMatchRepo.save(equipeMatch1);

		assertEquals("Paris St Germain", equipeMatch1.getEquipe().getNom());
		assertEquals("Barcelona", equipeMatch1.getMatch().getVille());

	}

	@Test void Resultat()
	  
	  {
	  
	  Resultat resultat = new Resultat();
	  
	  /////////////////Match/////////////// 
	  Match match1 = new Match();
	  
	  Date dateMatch = new Date(); SimpleDateFormat dateFormat3 = new
	  SimpleDateFormat("dd/MM/yyyy"); SimpleDateFormat heureFormat3 = new
	  SimpleDateFormat("HH:mm"); Date heureMatch = new Date();
	  
	  try { dateMatch = dateFormat3.parse("09/12/1950"); heureMatch =
	  heureFormat3.parse("12:09"); } catch(Exception e) {e.printStackTrace();}
	  
	  
	  match1.setDateMatch(dateMatch); 
	  match1.setHeureMatch(heureMatch);
	  match1.setLieu("Tatata"); 
	  match1.setVille("Paris"); 
	  match1.setPays("France");
	  match1 = matchRepo.save(match1);
	  
	  //long matchId = match1.getId();
	  
	  ///////////////EquipesMatch/////////////////////////
	  
	  //List<EquipeMatch> equipesMatchs = equipeMatchRepo.findAllByMatch(matchId);
	  
	  Equipe equipeGagnante = new Equipe(); 
	  equipeGagnante.setNom("Toulouse"); 
	  equipeGagnante =  equipeRepo.save(equipeGagnante); 
	 
	  Equipe equipePerdante = new Equipe(); 
	  equipePerdante.setNom("Roma"); 
	  equipePerdante =  equipeRepo.save(equipePerdante); 
	  
	  resultat.setMatch(match1);
	  resultat.setEquipe1("Toulouse");
	  resultat.setScoreEquipe1(3);
	  resultat.setEquipe2("Roma");
	  resultat.setScoreEquipe2(2);
	  resultat.setMatchNul(false);
	  
	  resultat= resultatRepo.save(resultat);
	  
	  
	  
	  // assertEquals(1, sizeM); 
	  }
	 
	@Test
	void PariCote() {

		Cote cote = new Cote();

		cote.setValeur(2.4);
		cote.setStatut(ChoixCote.GAGNANT);

		cote = coteRepo.save(cote);

		Date datePari = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm");
		Date heurePari = new Date();

		try {
			datePari = dateFormat.parse("09/09/1980");
			heurePari = heureFormat.parse("13:09");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Pari pari = new Pari();
		pari.setMontantJoue(18.50);
		pari.setDatePari(datePari);
		pari.setHeurePari(heurePari);
		pari.setResultat(ChoixResultat.GAGNANT);
		pari.setMontantResultat(23);

		pari = pariRepo.save(pari);
		
		pari.setCote(cote);

		pariRepo.save(pari);

		// assertEquals(1, sizeM);
	}

	@Test
	void coteMatch() {
		Cote cote = new Cote();
		cote.setValeur(1.4);
		cote.setStatut(ChoixCote.GAGNANT);

		cote = coteRepo.save(cote);

		Match match = new Match();

		Date dateMatch = new Date();
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat heureFormat3 = new SimpleDateFormat("HH:mm");
		Date heureMatch = new Date();

		try {
			dateMatch = dateFormat3.parse("09/12/1990");
			heureMatch = heureFormat3.parse("12:09");
		} catch (Exception e) {
			e.printStackTrace();
		}

		match.setDateMatch(dateMatch);
		match.setHeureMatch(heureMatch);
		match.setLieu("Cap Nou");
		match.setVille("Barcelona");
		match.setPays("Espagne");

		match = matchRepo.save(match);

		cote.setMatch(match);

		coteRepo.save(cote);

	}
}
