
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FlightPlan {

	public static void main(String[] args) {
		if(args.length < 3){
			System.err.println("Usage: java FlightPlan <FlightDataFile> <PathsToCalculateFile> <OutputFile>");
		}else{
			FlightsData flights = new FlightsData();
			try {
				flights.load(new File(args[0]));
				Scanner input = new Scanner(new File(args[1]));
				PrintWriter output = new PrintWriter(new File(args[2]));
				input.nextLine();
				int flightCount = 1;
				while(input.hasNext()){
					String line = input.nextLine();
					String [] parts = line.split("\\|");
					int optimize = FlightData.COST_OPTIMIZE;
					String optStr = "Cost";
					if(parts[2].equals("T")){
						optimize = FlightData.TIME_OPTIMIZE;
						optStr = "Time";
					}
					output.write(String.format("Flight %d: %s, %s (%s)\n", flightCount,parts[0],parts[1],optStr));
					output.write(flights.buildPath(parts[0], parts[1], optimize));
					output.write("\n");
					flightCount ++;
				}
				output.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
