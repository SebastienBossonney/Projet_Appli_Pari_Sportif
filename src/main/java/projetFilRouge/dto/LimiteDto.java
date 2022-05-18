package projetFilRouge.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LimiteDto {

	private Long id;

	private Double valeur;

	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
	private Date duree;

	private Long utilisateurId;

	public LimiteDto() {
		super();
	}

	public LimiteDto(Double valeur, Date duree, Long UtilisateurId) {
		super();
		this.valeur = valeur;
		this.duree = duree;
		this.utilisateurId = UtilisateurId;
	}

	public Long getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(Long utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public Date getDuree() {
		return duree;
	}

	public void setDuree(Date duree) {
		this.duree = duree;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
