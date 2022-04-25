package projetFilRouge.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "match")
public class Match {
	
	@Id
	@GeneratedValue
	private Long id;
	@Version
	private int version;
	@Temporal(TemporalType.DATE)
	@Column(name = "dateMatch")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateMatch;
	@Temporal(TemporalType.TIME)
	@Column(name = "heureMatch")
	@DateTimeFormat(pattern = "HH:mm")
	private Date heureMatch;
	@Column(name = "lieu")
	private String lieu;
	@Column(name = "ville")
	private String ville;
	@Column(name = "pays")
	private String pays;
	
	@OneToOne(mappedBy = "match")
	private Resultat resultat = null;
	
	@ManyToOne
	@JoinColumn(name = "sport_id")
	private Sport sport;
	
	@OneToMany(mappedBy="match")
	private List<EquipeMatch> equipeMatchs = new ArrayList<EquipeMatch>();
	
	@OneToMany(mappedBy="match")
	private List<Cote> cotes= new ArrayList<Cote>();

	
	
	
	public Match() {
		super();
	}

	public Match(Long id, int version, Date dateMatch, Date heureMatch, String lieu, String ville, String pays) {
		super();
		this.id = id;
		this.version = version;
		this.dateMatch = dateMatch;
		this.heureMatch = heureMatch;
		this.lieu = lieu;
		this.ville = ville;
		this.pays = pays;
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

	public Date getDateMatch() {
		return dateMatch;
	}

	public void setDateMatch(Date dateMatch) {
		this.dateMatch = dateMatch;
	}

	public Date getHeureMatch() {
		return heureMatch;
	}

	public void setHeureMatch(Date heureMatch) {
		this.heureMatch = heureMatch;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Resultat getResultat() {
		return resultat;
	}

	public void setResultat(Resultat resultat) {
		this.resultat = resultat;
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

	public List<Cote> getCotes() {
		return cotes;
	}

	public void setCotes(List<Cote> cotes) {
		this.cotes = cotes;
	}

	
}
