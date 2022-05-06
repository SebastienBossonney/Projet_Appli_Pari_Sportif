package projetFilRouge.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pari")
public class Pari {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Column(name = "montantJoue")
	private double montantJoue;
	@Temporal(TemporalType.DATE)
	@Column(name = "datePari")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datePari;
	@Temporal(TemporalType.TIME)
	@Column(name = "heurePari")
	@DateTimeFormat(pattern = "HH:mm")
	private Date heurePari;
	@Enumerated(EnumType.STRING)
	@Column(name = "resultat", length = 15)
	private ChoixResultat resultat;
	@Column(name = "montantResultat")
	private double montantResultat;

	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;

	@OneToOne
	@JoinColumn(name = "cote_id")
	private Cote cote;

	public Pari() {
		super();
	}

	public Pari(Long id, int version, double montantJoue, Date datePari, Date heurePari, ChoixResultat resultat,
			double montantResultat) {
		super();
		this.id = id;
		this.version = version;
		this.montantJoue = montantJoue;
		this.datePari = datePari;
		this.heurePari = heurePari;
		this.resultat = resultat;
		this.montantResultat = montantResultat;
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

	public double getMontantJoue() {
		return montantJoue;
	}

	public void setMontantJoue(double montantJoue) {
		this.montantJoue = montantJoue;
	}

	public Date getDatePari() {
		return datePari;
	}

	public void setDatePari(Date datePari) {
		this.datePari = datePari;
	}

	public Date getHeurePari() {
		return heurePari;
	}

	public void setHeurePari(Date heurePari) {
		this.heurePari = heurePari;
	}

	public ChoixResultat getResultat() {
		return resultat;
	}

	public void setResultat(ChoixResultat resultat) {
		this.resultat = resultat;
	}

	public double getMontantResultat() {
		return montantResultat;
	}

	public void setMontantResultat(double montantResultat) {
		this.montantResultat = montantResultat;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Cote getCote() {
		return cote;
	}

	public void setCote(Cote cote) {
		this.cote = cote;
	}

}
