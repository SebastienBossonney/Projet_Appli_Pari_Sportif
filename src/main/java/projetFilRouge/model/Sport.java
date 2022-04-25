package projetFilRouge.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "sport")
public class Sport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ChoixSport nomSport;
	
	@JsonIgnore
	@OneToMany(mappedBy="sport", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(value=FetchMode.SUBSELECT)
	private List <Equipe> equipes= new ArrayList<Equipe>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy="sport", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(value=FetchMode.SUBSELECT)
	private List <Match> matchs= new ArrayList <Match>();
	
	
	
	
	
	public Sport() {
		super();
	}

	public Sport(Long id, int version, ChoixSport nomSport) {
		super();
		this.id = id;
		this.version = version;
		this.nomSport = nomSport;
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
	public ChoixSport getNomSport() {
		return nomSport;
	}
	public void setNomSport(ChoixSport nomSport) {
		this.nomSport = nomSport;
	}
	public List<Equipe> getEquipes() {
		return equipes;
	}
	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}
	public List<Match> getMatchs() {
		return matchs;
	}
	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}
	
	
	
	
	
	

}
