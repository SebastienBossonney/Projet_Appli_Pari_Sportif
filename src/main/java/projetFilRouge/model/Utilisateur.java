package projetFilRouge.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "users")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100)
	private String identifiant;
	@Version
	private int version;
	@Column(length = 255)
	private String email;
	@Column(name="password",length = 100)
	private String motDePasse;
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private Role role;
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private Profil profil;
	@Column
	private double montantTotalGagne;
	@Column
	private double montantTotalPerdu;
	@Column
	private double salaire;
	@Column
	private double montantDisponible;

	@OneToOne
	@JoinColumn(name = "utilisateur_limite")
	private Limite limite = null;

	@OneToMany(mappedBy = "utilisateur")
	private List<Avertissement> avertissements = new ArrayList<Avertissement>();

	@OneToMany(mappedBy = "utilisateur")
	private List<Pari> Paris = new ArrayList<Pari>();

	public Utilisateur() {
		super();
	}

	public Utilisateur(String identifiant) {
		super();
		this.identifiant = identifiant;
	}
	
	

	public Utilisateur(Long id, String identifiant, int version, String email, String motDePasse, Role role,
			Profil profil, double montantTotalGagne, double montantTotalPerdu, double salaire, double montantDisponible) {
		super();
		this.id = id;
		this.identifiant = identifiant;
		this.version = version;
		this.email = email;
		this.motDePasse = motDePasse;
		this.role = role;
		this.profil = profil;
		this.montantTotalGagne = montantTotalGagne;
		this.montantTotalPerdu = montantTotalPerdu;
		this.salaire = salaire;
		this.montantDisponible = montantDisponible;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public double getMontantTotalGagne() {
		return montantTotalGagne;
	}

	public void setMontantTotalGagne(double montantTotalGagne) {
		this.montantTotalGagne = montantTotalGagne;
	}

	public double getMontantTotalPerdu() {
		return montantTotalPerdu;
	}

	public void setMontantTotalPerdu(double montantTotalPerdu) {
		this.montantTotalPerdu = montantTotalPerdu;
	}

	public Limite getLimite() {
		return limite;
	}

	public void setLimite(Limite limite) {
		this.limite = limite;
	}

	public List<Avertissement> getAvertissements() {
		return avertissements;
	}

	public void setAvertissements(List<Avertissement> avertissements) {
		this.avertissements = avertissements;
	}

	public List<Pari> getParis() {
		return Paris;
	}

	public void setParis(List<Pari> paris) {
		Paris = paris;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public double getMontantDisponible() {
		return montantDisponible;
	}

	public void setMontantDisponible(double montantDisponible) {
		if(montantDisponible < 0 || montantDisponible > 500) {
			throw new IllegalArgumentException("le montant doit etre entre 0 et 500");
		} else {
			this.montantDisponible = montantDisponible;
		}
	}
	

}
