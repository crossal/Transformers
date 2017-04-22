import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Team {
	
	private List<Transformer> transformers;
	private Faction faction;
	
	public Team(Faction faction) {
		this.transformers = new ArrayList<Transformer>();
		this.faction = faction;
	}
	
	public void setTransformers(List<Transformer> transformers) {
		this.transformers = transformers;
	}
	
	public List<Transformer> getTransformers() {
		return this.transformers;
	}
	
	public Transformer getTransformer(int index) {
		return this.transformers.get(index);
	}
	
	public void addTransformer(Transformer transformer) {
		this.transformers.add(transformer);
	}
	
	public void addTransformers(List<Transformer> transformers) {
		this.transformers.addAll(transformers);
	}
	
	public void removeTransformer(Transformer transformer) {
		this.transformers.remove(transformer);
	}
	
	public Faction getFaction() {
		
		return this.faction;
	}
	
	public void sortByRank() {
		Collections.sort(transformers);
	}
	
	public static void readTeamsFromFile(File file, Team autobots, Team decepticons) {
		
		List<String> lineList = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				// process the line.
				lineList.add(line);
			}
			br.close();
		} catch(Exception e) {
			System.out.println("Error reading transformers from file: " + e.getMessage());
		}
		
		String[] strings = lineList.toArray(new String[0]);
		readTeamsFromStringArray(strings, autobots, decepticons);
	}
	
	public static void readTeamsFromStringArray(String[] strings, Team autobots, Team decepticons) {
		
		for (String csv : strings) {
			String[] values = csv.split(",");
			for (int i = 0; i < values.length; i++) {
				values[i] = values[i].trim();
			}
			
			Faction faction = values[1].equalsIgnoreCase("A") ? Faction.AUTOBOTS : Faction.DECEPTICONS;
			Transformer transformer = new Transformer(values[0], faction, 
					Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), 
					Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9]));
			
			if (faction == Faction.AUTOBOTS) {
				autobots.addTransformer(transformer);
			} else {
				decepticons.addTransformer(transformer);
			}
		}
		
		autobots.sortByRank();
		decepticons.sortByRank();
	}
	
	public String toString() {
		String result = "";
		
		for (Transformer transformer : this.transformers) {
			if (!result.equals("")) {
				result += ", ";
			}
			result += transformer.getName();
		}
		
		return result;
	}
}
