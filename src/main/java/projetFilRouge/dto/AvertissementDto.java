package projetFilRouge.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class AvertissementDto {

	@Column
	@Size(min=2, max=50, message = "Avertissement should have at least 2 characters")
	private String description;

	public AvertissementDto() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
