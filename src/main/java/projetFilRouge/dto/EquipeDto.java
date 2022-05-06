package projetFilRouge.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EquipeDto {

	@NotEmpty()
	@Size(min=2, max=30, message = "Name should have at least 2 characters")
	private String nom;


	
	public EquipeDto() {
		super();
	}

	public EquipeDto( String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	
}
