
public class Transformer implements Comparable<Transformer> {
	private String name;
	private Faction faction;
	private int strength;
	private int intelligence;
	private int speed;
	private int endurance;
	private int rank;
	private int courage;
	private int firepower;
	private int skill;
	
	private int rating;
	
	public Transformer(String name, Faction faction, int strength, int intelligence, int speed, int endurance, int rank, int courage, int firepower, int skill) {
		
		this.name = name;
		this.faction = faction;
		this.strength = strength;
		this.intelligence = intelligence;
		this.speed = speed;
		this.endurance = endurance;
		this.rank = rank;
		this.courage = courage;
		this.firepower = firepower;
		this.skill = skill;
		
		validateAttributes();
		
		this.rating = this.strength + this.intelligence + this.speed + this.endurance + this.firepower;
	}
	
	private void validateAttributes() {
		
		String attributeName = "";
		
		if (this.strength < 1 || this.strength > 10) {
			attributeName = attributeName + "strength";
		}
		
		if (this.intelligence < 1 || this.intelligence > 10) {
			attributeName = attributeName.equalsIgnoreCase("") ? "intelligence" : attributeName + ", intelligence";
		}
		
		if (this.speed < 1 || this.speed > 10) {
			attributeName = attributeName.equalsIgnoreCase("") ? "speed" : attributeName + ", speed";
		}
		
		if (this.endurance < 1 || this.endurance > 10) {
			attributeName = attributeName.equalsIgnoreCase("") ? "endurance" : attributeName + ", endurance";
		}
		
		if (this.rank < 1 || this.rank > 10) {
			attributeName = attributeName.equalsIgnoreCase("") ? "rank" : attributeName + ", rank";
		}
		
		if (this.courage < 1 || this.courage > 10) {
			attributeName = attributeName.equalsIgnoreCase("") ? "courage" : attributeName + ", courage";
		}
		
		if (this.firepower < 1 || this.firepower > 10) {
			attributeName = attributeName.equalsIgnoreCase("") ? "firepower" : attributeName + ", firepower";
		}
		
		if (this.skill < 1 || this.skill > 10) {
			attributeName = attributeName.equalsIgnoreCase("") ? "skill" : attributeName + ", skill";
		}
		
		if (!attributeName.equalsIgnoreCase("")) {
			throw new IllegalArgumentException("'"+attributeName+"' value should be in the range of [1-10].");
	    }
	}
	
	public String getName() {
		return this.name;
	}
	
	public Faction getFaction() {
		return this.faction;
	}
	
	/**
	 * Returns 2 for special game ending scenario with all competitors destroyed.
	 * Returns 1 if argument is defeated.
	 * Returns 0 if battle is tied.
	 * Returns -1 if argument is the winner.
	 * @param 	opponent the transformer to battle against
	 * @return	a negative integer, zero, or a positive integer as this object wins, ties, or loses to the specified object.
	 */
	public int battle(Transformer opponent) {
		
		if (this.name.equalsIgnoreCase("Optimus Prime") || this.name.equalsIgnoreCase("Predaking")) {
			if (opponent.name.equalsIgnoreCase("Optimus Prime") || opponent.name.equalsIgnoreCase("Predaking")) {
				return 2;
			} else {
				return 1;
			}
		}
		
		if (opponent.name.equalsIgnoreCase("Optimus Prime") || opponent.name.equalsIgnoreCase("Predaking")) {
			if (this.name.equalsIgnoreCase("Optimus Prime") || this.name.equalsIgnoreCase("Predaking")) {
				return 2;
			} else {
				return -1;
			}
		}
		
		if (this.courage < opponent.courage - 3 && this.strength < opponent.strength - 2) {
			return -1;
		}
		if (opponent.courage < this.courage - 3 && opponent.strength < this.strength - 2) {
			return 1;
		}
		
		if (this.skill < opponent.skill - 2) {
			return -1;
		}
		if (opponent.skill < this.skill - 2) {
			return 1;
		}
		
		if (this.rating == opponent.rating) {
			return 0;
		} else if (this.rating > opponent.rating) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public int compareTo(Transformer o) {
		Integer a = new Integer(this.rank);
		Integer b = new Integer(o.rank);
		return b.compareTo(a);
	}
}
