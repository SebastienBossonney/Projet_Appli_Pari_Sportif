package projetFilRouge.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResultatDto {

	@NotEmpty()
	@Size(min = 2, max = 20, message = "Team's name should have at least 2 characters")
	private String equipe1;
	@NotNull()
	@Size(min = 1, max = 3, message = "Team's score should have at least 1 characters")
	private int scoreEquipe1;
	@NotEmpty()
	@Size(min = 2, max = 20, message = "Team's name should have at least 2 characters")
	private String equipe2;
	@NotNull()
	@Size(min = 1, max = 3, message = "Team's score should have at least 1 characters")
	private int scoreEquipe2;
	@NotNull()
	private boolean matchNul = false;
	@NotEmpty()
	private Long matchId;

	public ResultatDto() {
		super();
	}

	public ResultatDto(
			@NotEmpty @Size(min = 2, max = 20, message = "Team's name should have at least 2 characters") String equipe1,
			@NotNull @Size(min = 1, max = 3, message = "Team's score should have at least 1 characters") int scoreEquipe1,
			@NotEmpty @Size(min = 2, max = 20, message = "Team's name should have at least 2 characters") String equipe2,
			@NotNull @Size(min = 1, max = 3, message = "Team's score should have at least 1 characters") int scoreEquipe2,
			@NotNull boolean matchNul, @NotEmpty Long matchId) {
		super();
		this.equipe1 = equipe1;
		this.scoreEquipe1 = scoreEquipe1;
		this.equipe2 = equipe2;
		this.scoreEquipe2 = scoreEquipe2;
		this.matchNul = matchNul;
		this.matchId = matchId;
	}

	public String getEquipe1() {
		return equipe1;
	}

	public void setEquipe1(String equipe1) {
		this.equipe1 = equipe1;
	}

	public int getScoreEquipe1() {
		return scoreEquipe1;
	}

	public void setScoreEquipe1(int scoreEquipe1) {
		this.scoreEquipe1 = scoreEquipe1;
	}

	public String getEquipe2() {
		return equipe2;
	}

	public void setEquipe2(String equipe2) {
		this.equipe2 = equipe2;
	}

	public int getScoreEquipe2() {
		return scoreEquipe2;
	}

	public void setScoreEquipe2(int scoreEquipe2) {
		this.scoreEquipe2 = scoreEquipe2;
	}

	public boolean isMatchNul() {
		return matchNul;
	}

	public void setMatchNul(boolean matchNul) {
		this.matchNul = matchNul;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

}