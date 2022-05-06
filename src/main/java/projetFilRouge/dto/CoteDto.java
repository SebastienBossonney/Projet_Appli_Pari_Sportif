package projetFilRouge.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import projetFilRouge.model.ChoixCote;

public class CoteDto {

	private Long id;
	@NotNull()
	private ChoixCote statut;
	@NotEmpty()
	private double valeur;

	private Long matchId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChoixCote getStatut() {
		return statut;
	}

	public void setStatut(ChoixCote statut) {
		this.statut = statut;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
}
