package projetFilRouge.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import projetFilRouge.model.ChoixResultat;

public class PariDto {

	private Long id;

	@NotEmpty
	private double montantJoue;

	@NotEmpty
	@JsonFormat(/* shape = JsonFormat.Shape.STRING, */ pattern = "dd/MM/yyyy")
	private Date datePari;

	@NotEmpty
	@JsonFormat(pattern = "HH:mm", timezone = "Europe/Paris")
	private Date heurePari;

	@NotNull()
	private ChoixResultat resultat;

	private double montantResultat;

	private Long utilisateurId;
	
	private Long coteId;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Long getCoteId() {
		return coteId;
	}

	public void setCoteId(Long coteId) {
		this.coteId = coteId;
	}

	public Long getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(Long utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

}
