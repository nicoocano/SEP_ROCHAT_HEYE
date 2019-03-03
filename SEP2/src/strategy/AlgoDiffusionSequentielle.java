package strategy;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

import client.Generateur;
import client.ObservateurGenerateur;
import main.Main;
import service.GenerateurObservateurAsync;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AlgoDiffusionSequentielle implements AlgoDiffusion{

	private int nbUpdate;
	private Set<GenerateurObservateurAsync> m_observateurs;
	private Set<GenerateurObservateurAsync> m_observateurs_waiting;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public AlgoDiffusionSequentielle() {
		m_observateurs = new HashSet<GenerateurObservateurAsync>();
		m_observateurs_waiting = new HashSet<GenerateurObservateurAsync>();
		nbUpdate=0;
		
	}
	public boolean getLocked() {
		return true;
		//return m_observateurs_waiting.size() == 0;
	}
	public void execute(Generateur gen) {
		 
		ScheduledFuture result = Main.scheduler.schedule(new Runnable() {
			public void run() {
				for(GenerateurObservateurAsync obs : m_observateurs ) {
					m_observateurs_waiting.add(obs);
					ScheduledFuture<Void>result = obs.update(gen);
				}	
				/*try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		},1000,MILLISECONDS);
		
		
	}
	
	public void getValue(ObservateurGenerateur o) {
		nbUpdate++;
	}
	
	public void attach(GenerateurObservateurAsync obs) {
		m_observateurs.add(obs);
	}
	
	public void remove(GenerateurObservateurAsync g) {
		m_observateurs_waiting.remove(g);
	}
}
