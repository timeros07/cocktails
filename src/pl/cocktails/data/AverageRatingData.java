package pl.cocktails.data;

import java.io.Serializable;

public class AverageRatingData implements Serializable{

	private static final long serialVersionUID = -7278733321639019917L;
	
	private Double ratings;
	
	private Long numberOfVotes;

	public AverageRatingData(){}
	
	public AverageRatingData(Double ratings, Long numberOfVotes){
		this.ratings = ratings;
		this.numberOfVotes = numberOfVotes;
	}
	
	public Double getRatings() {
		return ratings;
	}

	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}

	public Long getNumberOfVotes() {
		return numberOfVotes;
	}

	public void setNumberOfVotes(Long numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
}
