package projetFilRouge.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import projetFilRouge.model.Profil;
import projetFilRouge.model.Role;

public class UtilisateurDto {

	private Long id;

	@NotEmpty()
	@Size(min = 2, max = 30, message = "Name should have at least 2 characters")
	private String identifiant;

	@NotEmpty()
	@Size(min = 2, max = 30, message = "Name should have at least 2 characters")
	private String email;

	@NotEmpty()
	@Size(min = 2, max = 30, message = "Name should have at least 2 characters")
	private String motDePasse;

	@NotNull()
	private Role role = Role.PARIEUR;
	@NotNull()
	private Profil profil;

	private double salaire;
	private double montantDisponible;
	private double montantTotalGagne;
	private double montantTotalPerdu;

	private LimiteDto limite;

	public LimiteDto getLimite() {
		return limite;
	}

	public void setLimite(LimiteDto limite) {
		this.limite = limite;
	}

	private List<AvertissementDto> avertissement;

	public UtilisateurDto() {
		super();
	}

//	public UtilisateurDto(String identifiant, String email, String motDePasse, Role role, Profil profil,
//			double montantTotalGagne, double montantTotalPerdu, double salaire, double montantDisponible, 
//			LimiteDto limite, List<AvertissementDto> listeAvertissement) {
//		super();
//		this.identifiant = identifiant;
//		this.email = email;
//		this.motDePasse = motDePasse;
//		this.role = role;
//		this.profil = profil;
//		this.montantTotalGagne = montantTotalGagne;
//		this.montantTotalPerdu = montantTotalPerdu;
//		this.montantDisponible = montantDisponible;
//		this.limite = limite;
//		this.avertissement = listeAvertissement;
//	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMontantDisponible(double montantDisponible) {
		this.montantDisponible = montantDisponible;
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

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public double getMontantDisponible() {
		return montantDisponible;
	}

	public List<AvertissementDto> getAvertissement() {
		return avertissement;
	}

	public void setAvertissement(List<AvertissementDto> avertissement) {
		this.avertissement = avertissement;
	}

}
