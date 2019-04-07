
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FlightsData {

	public static final int MAX_OPT_PATHS = 3;
	private LinkedList<String, LinkedList<String, FlightData>> flights;

	public FlightsData() {
		flights = new LinkedList<String, LinkedList<String, FlightData>>();
	}

	public void load(File inputFile) throws IOException {
		Scanner input = new Scanner(inputFile);
		input.nextLine();
		while (input.hasNext()) {
			String line = input.nextLine();
			String[] parts = line.split("\\|");
			if (parts.length < 4) {
				input.close();
				throw new RuntimeException("Wrong input file structure");
			}
			String startCity = parts[0];
			String endCity = parts[1];
			int cost = Integer.parseInt(parts[2]);
			int time = Integer.parseInt(parts[3]);
			addFlights(startCity, endCity, cost, time);
		}
		input.close();
	}

	public String buildPath(String startCity, String endCity, int optimize) {
		LinkedList<String, String> visited = new LinkedList<String, String>();
		Stack<String> backtrack = new Stack<String>();
		LinkedList<String, FlightData> list = searchFlights(startCity);
		LinkedList<String, Track> tracks = new LinkedList<String, Track>();
		Stack<String> track = new Stack<String>();
		if (list != null) {
			backtrack.push(startCity);
		}
		while (backtrack.peek() != null) {
			visited.addData(backtrack.peek());
			list = searchFlights(backtrack.peek());
			track.push(backtrack.pop());
			boolean added = false;
			if (list != null) {
				LinkedListNode<FlightData> node = list.getRoot();
				while (node != null) {
					String currentCity = node.getData().getEndCity();
					if (currentCity.equals(endCity)) {
						track.push(currentCity);
						tracks.addData(trackFromStack(track));
						track.pop();
					} else {
						if (!backtrack.contains(currentCity) && visited.getData(currentCity) == null) {
							backtrack.push(currentCity);
							added = true;
						}
					}
					node = node.getNext();
				}
			}
			if (!added)
				track.pop();
		}
		
		int pathCount = 0;
		String result = "";
		while(pathCount < MAX_OPT_PATHS && !tracks.isEmpty()){
			pathCount++;
			result += "Path " + String.valueOf(pathCount) + ": " + getMinPath(tracks,optimize) + "\n";
		}
		
		if(pathCount == 0)
			return "Error: no paths found";
		
		return result;
	}

	private void addFlights(String startCity, String endCity, int cost, int time) {
		addFlight(startCity, endCity, cost, time);
		addFlight(endCity, startCity, cost, time);
	}

	private void addFlight(String startCity, String endCity, int cost, int time) {
		LinkedList<String, FlightData> startList = searchFlights(startCity);
		if (startList == null) {
			startList = new LinkedList<String, FlightData>(startCity);
			flights.addData(startList);
		}
		startList.addData(new FlightData(endCity, cost, time));
	}

	private LinkedList<String, FlightData> searchFlights(String startCity) {
		LinkedList<String, FlightData> searchList = new LinkedList<String, FlightData>(startCity);
		return flights.getData(searchList);
	}

	private Track trackFromStack(Stack<String> track){
		Stack<String> newStack = new Stack<String>();
		while(track.peek()!=null){
			newStack.push(track.pop());
		}
		String start = newStack.pop();
		track.push(start);
		Track result = new Track(start);
		while(newStack.peek()!=null){
			String city = newStack.pop();
			track.push(city);
			FlightData flight = searchFlights(start).getData(new FlightData(city,0,0));
			result.addPath(flight);
			start = city;
		}
		return result;
	}
	
	private Track getMinPath(LinkedList<String,Track> tracks, int optimize){
		LinkedListNode<Track> node = tracks.getRoot();
		Track minTrack = null;
		while(node!=null){
			if(minTrack == null){
				minTrack = node.getData();
			}else{
				if(optimize == FlightData.COST_OPTIMIZE && minTrack.getTotalCost() > node.getData().getTotalCost()){
					minTrack = node.getData();
				}else if(optimize == FlightData.TIME_OPTIMIZE && minTrack.getTotalTime() > node.getData().getTotalTime()){
					minTrack = node.getData();
				}
			}
			node = node.getNext();
		}
		tracks.remove(minTrack);
		return minTrack;
	}
}
