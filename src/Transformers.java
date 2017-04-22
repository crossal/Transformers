import java.io.File;

public class Transformers {
	
	public static void main(String[] args) {
		
		Team autobots = new Team(Faction.AUTOBOTS);
		Team decepticons = new Team(Faction.DECEPTICONS);
		
		if(args.length > 0) {
            File file = new File(args[0]);
            Team.readTeamsFromFile(file, autobots, decepticons);
            System.out.println("File read.\nAutobots: " + autobots.toString() + "\nvs\nDecepticons: " + decepticons.toString());
        } else {
        	String[] teams = new String[]{
        			"Soundwave, D, 8,9,2,6,7,5,6,10",
        			"Bluestreak, A, 6,6,7,9,5,2,9,7",
        			"Hubcap, A, 4,4,4,4,4,4,4,4"
        	};
        	Team.readTeamsFromStringArray(teams, autobots, decepticons);
        	System.out.println("No file to read. Using default teams..\nAutobots: " + autobots.toString() + "\nvs\nDecepticons: " + decepticons.toString());
        }
		
		int numberOfBattles = 0;
		int smallerTeamSize = Math.min(autobots.getTransformers().size(), decepticons.getTransformers().size());
		int largerTeamSize = Math.max(autobots.getTransformers().size(), decepticons.getTransformers().size());
		
		Team survivingAutobots = new Team(Faction.AUTOBOTS);
		Team survivingDecepticons = new Team(Faction.DECEPTICONS);
		
		int autobotWins = 0;
		int decepticonWins = 0;
		boolean specialEnding = false;
		
		for (int i = 0; i < smallerTeamSize; i++) {
			numberOfBattles++;
			Transformer autobot = autobots.getTransformer(i);
			Transformer decepticon = decepticons.getTransformer(i);
			int battleResult = autobot.battle(decepticon);
			if (battleResult == 2) {
				// all competitors destroyed
				specialEnding = true;
				break;
			} else if (battleResult == 1) {
				// autobot won
				autobotWins++;
				survivingAutobots.addTransformer(autobot);
			} else if (battleResult == -1) {
				// decepticon won
				decepticonWins++;
				survivingDecepticons.addTransformer(decepticon);
			} else {
				// tied - both transformers destroyed
				autobotWins++;
				decepticonWins++;
			}
		}
		
		if (!specialEnding && smallerTeamSize != largerTeamSize) {
			if (autobots.getTransformers().size() > decepticons.getTransformers().size()) {
				survivingAutobots.addTransformers(autobots.getTransformers().subList(smallerTeamSize, largerTeamSize));
			} else {
				survivingDecepticons.addTransformers(decepticons.getTransformers().subList(smallerTeamSize, largerTeamSize));
			}
		}
		
		System.out.println("\n" + numberOfBattles + " battle(s)");
		if (specialEnding || autobotWins == decepticonWins) {
			System.out.println("Tied");
		} else {
			System.out.println("Winning team (" + (autobotWins > decepticonWins ? "Autobots" : "Decepticons") + "): " + (autobotWins > decepticonWins ? survivingAutobots.toString() : survivingDecepticons.toString()));
			System.out.println("Survivors from the losing team (" + (autobotWins < decepticonWins ? "Autobots" : "Decepticons") + "): " + (autobotWins < decepticonWins ? survivingAutobots.toString() : survivingDecepticons.toString()));
		}
	}
}
