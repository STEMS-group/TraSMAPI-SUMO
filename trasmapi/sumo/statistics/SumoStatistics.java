package trasmapi.sumo.statistics;

import java.util.ArrayList;

import trasmapi.genAPI.Statistics;
import trasmapi.sumo.Pair;
import trasmapi.sumo.SumoVehicle;


public class SumoStatistics extends Statistics {
	
	ArrayList<Pair<Integer, SumoStepTLStats>> tls;
	ArrayList<Pair<Integer, SumoStepILStats>> ils;
	ArrayList<Pair<Integer, SumoStepLANEStats>> lanes;
	
	static Integer simStep;
	
	public SumoStatistics() {
		super();
		simStep = 0;
	}
	
	public void enableAll() { 
		enableTrafficLights();
		enableInductionLoops();
		enableLanes();
	}
	
	public void enableTrafficLights() {
		super.enableTrafficLights();
		tls = new ArrayList<Pair<Integer, SumoStepTLStats>>();
	}
	
	public void enableInductionLoops() {
		super.enableInductionLoops();
		ils = new ArrayList<Pair<Integer, SumoStepILStats>>();
	}
	
	public void enableLanes() {
		super.enableLanes();
		lanes = new ArrayList<Pair<Integer, SumoStepLANEStats>>();
	}
	
	public void update() {
		simStep++;
		if(tlflag){
			tls.add(new Pair<Integer, SumoStepTLStats>(simStep, new SumoStepTLStats()));
		}
		if(ilflag){
			ils.add(new Pair<Integer, SumoStepILStats>(simStep, new SumoStepILStats()));
		}
		if(laneflag){
			lanes.add(new Pair<Integer, SumoStepLANEStats>(simStep, new SumoStepLANEStats()));
		}
	}
	
	public Integer getTLTroughput(String id) {
		return getTLTroughput(simStep-1, id);
	}
	
	public Integer getTLTroughput(String id, String lane) {
		return getTLTroughput(simStep-1, id, lane);
	}
	
	public Integer getTLTroughput(Integer index, String id) {
		SumoStepTLStats n = null;
		SumoStepTLStats m = null;
		for(Pair<Integer, SumoStepTLStats> a:tls) {
			if(a.first().equals(index-1))
				n = a.second();
			if(a.first().equals(index))
				m = a.second();
		}
		if(n == null || m == null)
			return -1;
		
		SumoVehicle[] previous = n.getVehicles(id);
		SumoVehicle[] actual = m.getVehicles(id);
		
		/*System.out.println("PREVIEWS");
		for(SumoVehicle vp:previous){
			System.out.println(vp.getId());
		}
		System.out.println("END");*/
		
		Integer counter = new Integer(0);
		for(SumoVehicle vp:previous){
			boolean flag = false;
			for(SumoVehicle va:actual){
				if(vp.equals(va))
					flag = true;
			}
			if(!flag)
				counter++;
		}
		return counter;
	}
	
	public Integer getTLTroughput(Integer index, String id, String laneId){
		SumoStepTLStats n = null;
		SumoStepTLStats m = null;
		for(Pair<Integer, SumoStepTLStats> a:tls) {
			if(a.first().equals(index-1))
				n = a.second();
			if(a.first().equals(index))
				m = a.second();
		}
		if(n == null || m == null)
			return -1;
		
		SumoVehicle[] previous = n.getVehicles(id, laneId);
		SumoVehicle[] actual = m.getVehicles(id, laneId);
		
		/*System.out.println("PREVIEWS");
		for(SumoVehicle vp:previous){
			System.out.println(vp.getId());
		}
		System.out.println("END");*/
		
		Integer counter = new Integer(0);
		for(SumoVehicle vp:previous){
			boolean flag = false;
			for(SumoVehicle va:actual){
				if(vp.equals(va)) {
					flag = true;
					break;
				}
			}
			if(!flag)
				counter++;
		}
		return counter;
	}
}
