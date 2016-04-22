package trasmapi.sumo.statistics;

import java.util.ArrayList;

import trasmapi.sumo.Pair;
import trasmapi.sumo.SumoLane;

public class SumoStepLANEStats {
	ArrayList<Pair<String, SumoLANEStats>> lanes;
	
	public SumoStepLANEStats() {
		lanes = new ArrayList<Pair<String, SumoLANEStats>>();
		String s[] = SumoLane.getLaneIdList();
		for(String a:s) {
			lanes.add(new Pair<String, SumoLANEStats>(a, new SumoLANEStats(a)));
		}
	}
}
