package projetFilRouge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name = "cote")
public class Cote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Column(name = "valeur", length = 10)
	private double valeur;
	@Enumerated(EnumType.STRING)
	@Column(name = "statut", length = 20)
	private ChoixCote statut;

	@OneToOne(mappedBy = "cote")
	private Joueur joueur = null;
	@OneToOne(mappedBy = "cote")
	@JoinColumn(name = "cote_id")
	private Pari pari;
	
	@ManyToOne
	@JoinColumn(name = "match_id")
	private Match match;

	public Cote() {
		super();
	}

	public Cote(Long id, int version, double valeur, ChoixCote statut) {
		super();
		this.id = id;
		this.version = version;
		this.valeur = valeur;
		this.statut = statut;
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

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public ChoixCote getStatut() {
		return statut;
	}

	public void setStatut(ChoixCote statut) {
		this.statut = statut;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Pari getPari() {
		return pari;
	}

	public void setPari(Pari pari) {
		this.pari = pari;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

}
