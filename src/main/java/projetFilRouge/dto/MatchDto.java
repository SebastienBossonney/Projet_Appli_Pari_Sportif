package projetFilRouge.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;



public class MatchDto {
	
	@NotEmpty
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
	private Date dateMatch;
	@NotEmpty
	@JsonFormat ( pattern="HH:mm") 
	private Date heureMatch;
	 
	@NotEmpty
	private String lieu;
	
	private String ville;
	
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

	private String pays;

}
