package projetFilRouge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "resultat")
public class Resultat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Column(name = "equipe1")
	private String equipe1;
	@Column(name = "scoreEquipe1")
	private int scoreEquipe1;
	@Column(name = "equipe2")
	private String equipe2;
	@Column(name = "scoreEquipe2")
	private int scoreEquipe2;
	@Column(name = "matchNul")
	private boolean matchNul = false;

	@OneToOne
	@JoinColumn(name = "match_id")
	private Match match;

	public Resultat() {
		super();
	}

	public Resultat(Long id, int version, String equipe1, int scoreEquipe1, String equipe2, int scoreEquipe2,
			boolean matchNul) {
		super();
		this.id = id;
		this.version = version;
		this.equipe1 = equipe1;
		this.scoreEquipe1 = scoreEquipe1;
		this.equipe2 = equipe2;
		this.scoreEquipe2 = scoreEquipe2;
		this.matchNul = matchNul;
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

	public String getEquipe1() {
		return equipe1;
	}

	public void setEquipe1(String equipe1) {
		this.equipe1 = equipe1;
	}

	public int getScoreEquipe1() {
		return scoreEquipe1;
	}

	public void setScoreEquipe1(int scoreEquipe1) {
		this.scoreEquipe1 = scoreEquipe1;
	}

	public String getEquipe2() {
		return equipe2;
	}

	public void setEquipe2(String equipe2) {
		this.equipe2 = equipe2;
	}

	public int getScoreEquipe2() {
		return scoreEquipe2;
	}

	public void setScoreEquipe2(int scoreEquipe2) {
		this.scoreEquipe2 = scoreEquipe2;
	}

	public boolean isMatchNul() {
		return matchNul;
	}

	public void setMatchNul(boolean matchNul) {
		this.matchNul = matchNul;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

}