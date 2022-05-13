package projetFilRouge.dto;

import java.util.Date;

public class LimiteDto {

	private Long id;
	
	private Double valeur;

	private Date duree;
			
	public LimiteDto() {
		super();
	}

	public LimiteDto(Double valeur, Date duree) {
		super();
		this.valeur = valeur;
		this.duree = duree;
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
