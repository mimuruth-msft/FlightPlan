
public class FlightData implements Comparable<FlightData>{

	private String endCity;
	private int cost;
	private int time;
	
	public static final int COST_OPTIMIZE = 1;
	public static final int TIME_OPTIMIZE = 2;
	
	public FlightData(String endCity, int cost, int time){
		this.endCity = endCity;
		this.cost = cost;
		this.time = time;
	}
	
	public String getEndCity(){
		return endCity;
	}
	
	public int getCost(){
		return cost;
	}
	
	public int getTime(){
		return time;
	}

	@Override
	public int compareTo(FlightData o) {
		return endCity.compareTo(o.getEndCity());
	}
	
}
