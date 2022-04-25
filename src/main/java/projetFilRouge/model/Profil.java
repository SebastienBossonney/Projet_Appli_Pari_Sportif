package projetFilRouge.model;

public enum Profil {
	RISQUE("Risque"), MIRISQUE("Risque moyen"), SANSRISQUE("Sans  risque");
	
	private final String profil;

	private Profil(String profil) {
		this.profil = profil;
	}

	public String getProfil() {
		return profil;
	}
}


