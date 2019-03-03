package strategy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import client.Generateur;
import client.ObservateurGenerateur;
import service.GenerateurObservateurAsync;

public class AlgoDiffusionAtomique implements AlgoDiffusion {

	
	private int nbUpdate;
	private Set<GenerateurObservateurAsync> m_observateurs;
	private Set<GenerateurObservateurAsync> m_observateurs_waiting;
	
	public AlgoDiffusionAtomique() {
		m_observateurs = new HashSet<GenerateurObservateurAsync>();
		m_observateurs_waiting = new HashSet<GenerateurObservateurAsync>();
		nbUpdate=0;
		
	}
	public boolean getLocked() {
		return m_observateurs_waiting.size() == 0;
	}
	public void execute(Generateur gen) {
		if(nbUpdate == 0) {
			for(GenerateurObservateurAsync obs : m_observateurs ) {
				nbUpdate++;
				m_observateurs_waiting.add(obs);
				ScheduledFuture<Void>result = obs.update(gen);
			}
		}
	}
	
	public void getValue(ObservateurGenerateur o) {
		nbUpdate--;
	}
	
	public void attach(GenerateurObservateurAsync obs) {
		m_observateurs.add(obs);
	}
	
	public void remove(GenerateurObservateurAsync g) {
		m_observateurs_waiting.remove(g);
	}
	
	
}
