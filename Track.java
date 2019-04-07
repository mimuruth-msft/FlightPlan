
public class Track implements Comparable<Track>{
	private int totalCost;
	private int totalTime;
	private String path;
	
	public Track(String start){
		path = start;
		totalCost = 0;
		totalTime = 0;
	}
	
	public void addPath(String city, int cost, int time){
		path += " -> " + city;
		totalCost += cost;
		totalTime += time;
	}
	
	public void addPath(FlightData flight){
		addPath(flight.getEndCity(),flight.getCost(),flight.getTime());
	}
	
	public String getPath(){
		return path;
	}
	
	public int getTotalCost(){
		return totalCost;
	}
	
	public int getTotalTime(){
		return totalTime;
	}

	@Override
	public int compareTo(Track o) {
		return path.compareTo(o.getPath());
	}

	@Override
	public String toString() {
		return path + ". Time: " + String.valueOf(totalTime) + " Cost: " + String.valueOf(totalCost);
	}
	
	
}
