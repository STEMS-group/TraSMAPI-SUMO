package trasmapi.sumo.statistics;

import java.util.ArrayList;

import trasmapi.sumo.SumoLanePosition;
import trasmapi.sumo.SumoVehicle;

public class SumoLANEStats {
	ArrayList<SumoVehicle> vehicles;
	Float meanVelocity;
	
	Integer simStep;
	
	public SumoLANEStats(String id) {
		SumoVehicle[] rawlist = SumoVehicle.getVehiclesList();
		Float acc = new Float(0);
		Integer count = new Integer(0);
		for(SumoVehicle a:rawlist){
			SumoLanePosition pos = a.getPosition();
			if(pos.lane == id) {
				vehicles.add(a);
				acc += a.getSpeed();
				count++;
			}			
		}
		meanVelocity = acc/(float)count;
	}
	
	public Integer getSimStep() {
		return simStep;
	}
}
