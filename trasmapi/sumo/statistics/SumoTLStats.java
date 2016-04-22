package trasmapi.sumo.statistics;

import java.util.ArrayList;

import trasmapi.sumo.Pair;
import trasmapi.sumo.SumoLane;
import trasmapi.sumo.SumoLanePosition;
import trasmapi.sumo.SumoTrafficLight;
import trasmapi.sumo.SumoVehicle;

public class SumoTLStats {
	String green;
	String brake;
	String yellow;
	ArrayList<Pair<String, SumoVehicle[]>> vehiclesByLane;
	
	public SumoTLStats(String id) {
		SumoTrafficLight probe = new SumoTrafficLight(id);
		String[] s = probe.getTrafficLightState();
		green = s[0];
		brake = s[1];
		yellow = s[2];
		
		vehiclesByLane = new ArrayList<Pair<String, SumoVehicle[]>>();
		SumoLane[] lanes = probe.getTrafficLightsLaneList();
		for(SumoLane l:lanes){
			ArrayList<SumoVehicle> vehicles = new ArrayList<SumoVehicle>(); 
			SumoVehicle[] rawlist = SumoVehicle.getVehiclesList();
			for(SumoVehicle a:rawlist){
				SumoLanePosition pos = a.getPosition();
				if(pos.lane.equals(l.getId())) {
					vehicles.add(a);
				}			
			}
			SumoVehicle[] veh = new SumoVehicle[vehicles.size()];
			for(int i=0; i<vehicles.size(); i++) {
				veh[i] = vehicles.get(i);
			}
			/*System.out.println("ADDING");
			for(SumoVehicle f:veh){
				System.out.println(f.getId());
			}
			System.out.println("END");*/
			vehiclesByLane.add(new Pair<String, SumoVehicle[]>(l.getId(), veh));
		}
	}
	
	public SumoVehicle[] getVehicles(){
		ArrayList<SumoVehicle> vec = new ArrayList<SumoVehicle>();
		for(Pair<String, SumoVehicle[]> a:vehiclesByLane){
			for(SumoVehicle sv:a.second()) {
				vec.add(sv);
			}
		}
		SumoVehicle[] veh = new SumoVehicle[vec.size()];
		for(int i=0; i<vec.size(); i++) {
			veh[i] = vec.get(i);
		}
		return veh;
	}

	public SumoVehicle[] getVehicles(String lane){
		ArrayList<SumoVehicle> vec = new ArrayList<SumoVehicle>();
		for(Pair<String, SumoVehicle[]> a:vehiclesByLane){
			if(a.first().equals(lane)){
				for(SumoVehicle sv:a.second()) {
					vec.add(sv);
				}
			}
		}
		SumoVehicle[] veh = new SumoVehicle[vec.size()];
		for(int i=0; i<vec.size(); i++) {
			veh[i] = vec.get(i);
		}
		return veh;
	}
	
}
