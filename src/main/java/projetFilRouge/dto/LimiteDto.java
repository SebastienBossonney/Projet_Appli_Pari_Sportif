package projetFilRouge.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class LimiteDto {

	
	private double valeur;
	
	private Date duree;

	public LimiteDto() {
		super();
	}

	public LimiteDto(double valeur, Date duree) {
		super();
		this.valeur = valeur;
		this.duree = duree;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public Date getDuree() {
		return duree;
	}

	public void setDuree(Date duree) {
		this.duree = duree;
	}

}
