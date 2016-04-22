package trasmapi.sumo.statistics;

import java.util.ArrayList;

import trasmapi.sumo.Pair;
import trasmapi.sumo.SumoInductionLoop;

public class SumoStepILStats {
	ArrayList<Pair<String, SumoILStats>> ils;
	
	public SumoStepILStats() {
		ils = new ArrayList<Pair<String, SumoILStats>>();
		String[] s = SumoInductionLoop.getInductionLoopIdList();
		for(String a:s) {
			ils.add(new Pair<String, SumoILStats>(a, new SumoILStats(a)));
		}
	}
}
