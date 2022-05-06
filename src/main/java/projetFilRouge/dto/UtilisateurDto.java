package projetFilRouge.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import projetFilRouge.model.Profil;
import projetFilRouge.model.Role;

public class UtilisateurDto {

	@Column(nullable = false)
	@Size(min = 2, max = 16, message = "Identifiant should have at least 2 characters and maximum 16")
	private String identifiant;

	@Column(nullable = false)
	@Size(min = 2, max = 16, message = "Email should have at least 2 characters and maximum 16")
	private String email;

	@Column(name = "password", nullable = false)
	@Size(min = 2, max = 16, message = "Password should have at least 2 characters and maximum 16")
	private String motDePasse;

	@Column(length = 15)
	private Role role;
	@Column(nullable = false)
	private Profil profil;

	@Column
	private double montantTotalGagne;
	@Column
	private double montantTotalPerdu;

	
	public UtilisateurDto() {
		super();
	}

	public UtilisateurDto(String identifiant, String email, String motDePasse, Role role, Profil profil,
			double montantTotalGagne, double montantTotalPerdu) {
		super();
		this.identifiant = identifiant;
		this.email = email;
		this.motDePasse = motDePasse;
		this.role = role;
		this.profil = profil;
		this.montantTotalGagne = montantTotalGagne;
		this.montantTotalPerdu = montantTotalPerdu;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
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

}
