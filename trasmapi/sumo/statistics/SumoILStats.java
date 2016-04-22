package trasmapi.sumo.statistics;

import trasmapi.sumo.SumoInductionLoop;
import trasmapi.sumo.SumoVehicle;

public class SumoILStats {
	SumoVehicle[] vehicles;
	Float occupancy, meanVelocity;
	
	Integer simStep;
	
	public SumoILStats(String id) {
		SumoInductionLoop probe = new SumoInductionLoop(id);
		vehicles = probe.getVehiclesList();
		occupancy = probe.getOccupancy();
		meanVelocity = probe.getMeanVelocity();
	}

}
