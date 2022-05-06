package projetFilRouge.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import projetFilRouge.model.Profil;
import projetFilRouge.model.Role;

public class UtilisateurDto {

	@NotEmpty()
	@Size(min = 2, max = 30, message = "Name should have at least 2 characters")
	private String identifiant;

	@NotEmpty()
	@Size(min = 2, max = 30, message = "Name should have at least 2 characters")
	private String email;

	@NotEmpty()
	@Size(min = 2, max = 30, message = "Name should have at least 2 characters")
	private String motDePasse;

	@NotEmpty()
	private Role role;
	@NotEmpty()
	private Profil profil;

	private double montantTotalGagne;
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