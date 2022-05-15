package projetFilRouge.dto;

import javax.validation.constraints.Size;

public class AvertissementDto {

	private Long id;
	
	@Size(min = 2, max = 50, message = "Avertissement should have at least 2 characters")
	private String description;
	
	private Long utilisateurId;

	public AvertissementDto() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(Long utilisateurId) {
		this.utilisateurId = utilisateurId;
	}
	

}
