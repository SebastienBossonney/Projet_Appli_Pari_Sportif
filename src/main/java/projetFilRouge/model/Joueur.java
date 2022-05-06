package projetFilRouge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "joueur")
public class Joueur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Column(name = "nom", length = 20)
	private String nom;
	@Column(name = "nbBut")
	private int nbBut;

	@OneToOne
	@JoinColumn(name = "cote_id")
	private Cote cote;
	@ManyToOne
	@JoinColumn(name = "equipe_id")
	private Equipe equipe;

	public Joueur() {
		super();
	}

	public Joueur(Long id, int version, String nom, int nbBut) {
		super();
		this.id = id;
		this.version = version;
		this.nom = nom;
		this.nbBut = nbBut;
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

	public int getNbBut() {
		return nbBut;
	}

	public void setNbBut(int nbBut) {
		this.nbBut = nbBut;
	}

	public Cote getCote() {
		return cote;
	}

	public void setCote(Cote cote) {
		this.cote = cote;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

}
