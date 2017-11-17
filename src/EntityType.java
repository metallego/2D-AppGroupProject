
public enum EntityType {

	HERO, ENEMY, CHEST;

	public String toString() {
		switch(this) {
		case HERO: return "hero";
		case ENEMY: return "enemy";
		case CHEST: return "chest";
		}
		return "n/a";
	}


}
