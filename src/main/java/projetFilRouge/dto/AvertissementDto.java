package projetFilRouge.dto;

import javax.validation.constraints.Size;

public class AvertissementDto {

	@Size(min = 2, max = 50, message = "Avertissement should have at least 2 characters")
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
