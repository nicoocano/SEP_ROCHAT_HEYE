package client;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;

import main.Main;
import service.GenerateurObservateurAsync;
import strategy.AlgoDiffusion;

public class GenerateurImpl implements Generateur {
	private AlgoDiffusion algo;
	private String name;
	int value;

	
	public GenerateurImpl(AlgoDiffusion alg, String n) {
		algo = alg;
		name=n;
		value = 0;
	}
	
	public void attach(GenerateurObservateurAsync o) {
		algo.attach(o);
	}
	public void detach(GenerateurObservateurAsync o) {
		//todo
	}
	public int getValue(ObservateurGenerateur o){
		System.out.println("observateur");
		algo.getValue(o);
		return value;
	}
	
	public void tick() {
		
		Main.scheduler.scheduleAtFixedRate(new Runnable() {
			public void run() {
				if(algo.getLocked()) {
					value += 1;
				}
			}
		},0,1000,MILLISECONDS);
		algo.execute(this);
		
	}
	public void remove(GenerateurObservateurAsync g) {
		algo.remove(g);
	}
}
