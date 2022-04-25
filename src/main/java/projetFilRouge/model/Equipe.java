package projetFilRouge.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "equipe")
public class Equipe {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Column(name ="nom", length = 20)
	private String nom;
	
	@OneToMany(mappedBy="equipe")
	private List<Joueur> joueurs = new ArrayList<Joueur>(); 
	@ManyToOne
	@JoinColumn(name="sport_id")
	private Sport sport;
	@JsonIgnore
	@OneToMany(mappedBy="equipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(value=FetchMode.SUBSELECT)
	private List<EquipeMatch> equipeMatchs = new ArrayList<EquipeMatch>();
	
		
	
	public Equipe() {
		super();
	}
	
	public Equipe(Long id, int version, String nom) {
		super();
		this.id = id;
		this.version = version;
		this.nom = nom;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Joueur> getJoueurs() {
		return joueurs;
	}
	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	public List<EquipeMatch> getEquipeMatchs() {
		return equipeMatchs;
	}
	public void setEquipeMatchs(List<EquipeMatch> equipeMatchs) {
		this.equipeMatchs = equipeMatchs;
	}
	
	
	
	
	
	
	
}
