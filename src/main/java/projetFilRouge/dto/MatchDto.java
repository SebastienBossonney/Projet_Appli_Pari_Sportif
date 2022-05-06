package projetFilRouge.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import projetFilRouge.model.Equipe;
import projetFilRouge.model.EquipeMatch;



public class MatchDto {
	
	private Long id;
	@NotEmpty
	@JsonFormat(/* shape = JsonFormat.Shape.STRING, */ pattern = "dd/MM/yyyy" )
	private Date dateMatch;
	@NotEmpty
	@JsonFormat ( pattern="HH:mm", timezone="Europe/Paris") 
	private Date heureMatch;
	 
	@NotEmpty
	private String lieu;
	
	private String ville;
	
	private String pays;
	
	private List<Equipe> equipes;
	
	private Long sportId;
	
	
	/////getters and setters
	public Date getDateMatch() {
		return dateMatch;
	}

	public void setDateMatch(Date dateMatch) {
		this.dateMatch = dateMatch;
	}

	
	  public Date getHeureMatch() { return heureMatch; }
	  
	  public void setHeureMatch(Date heureMatch) { this.heureMatch = heureMatch; }
	 

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
	
	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public Long getSportId() {
		return sportId;
	}

	public void setSportId(Long sportId) {
		this.sportId = sportId;
	}

}
