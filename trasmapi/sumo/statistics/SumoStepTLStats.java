package trasmapi.sumo.statistics;

import java.util.ArrayList;

import trasmapi.sumo.Pair;
import trasmapi.sumo.SumoTrafficLight;
import trasmapi.sumo.SumoVehicle;

public class SumoStepTLStats {
	ArrayList<Pair<String, SumoTLStats>> tls;
	
	public SumoStepTLStats() {
		tls = new ArrayList<Pair<String, SumoTLStats>>();
		String[] s = SumoTrafficLight.getTrafficLightsIDList();
		for(String a:s){
			tls.add(new Pair<String, SumoTLStats>(a, new SumoTLStats(a)));
		}
	}
	
	public SumoVehicle[] getVehicles(String id) {
		for(Pair<String, SumoTLStats> a:tls) {
			if(a.first().equals(id)) {
				return a.second().getVehicles();
			}
		}
		return null;
	}
	
	public SumoVehicle[] getVehicles(String id, String laneId) {
		for(Pair<String, SumoTLStats> a:tls) {
			if(a.first().equals(id)) {
				return a.second().getVehicles(laneId);
			}
		}
		return null;
	}
	
}
