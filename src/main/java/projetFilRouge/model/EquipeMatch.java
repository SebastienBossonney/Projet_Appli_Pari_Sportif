package projetFilRouge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;



@Entity
@Table(name="equipeMatch")
public class EquipeMatch {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	
	@ManyToOne
	@JoinColumn(name = "equipe_id")
	private Equipe equipe;
	@ManyToOne
	@JoinColumn(name = "match_id")
	private Match match;
	
	
	
	
	public EquipeMatch() {
		super();
	}
	
	public EquipeMatch(Equipe equipe, Match match) {
		super();
		this.equipe = equipe;
		this.match = match;
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
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	
	
	
	
	

	
	
	

}
