package strategy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

import client.Generateur;
import client.ObservateurGenerateur;
import service.GenerateurObservateurAsync;

public class AlgoDiffusionSequentielle implements AlgoDiffusion{

	private int nbUpdate;
	private Set<GenerateurObservateurAsync> m_observateurs;
	
	public AlgoDiffusionSequentielle() {
		m_observateurs = new HashSet<GenerateurObservateurAsync>();
		nbUpdate=0;
	}
	public boolean getLocked() {
		return true;
	}
	public void execute(Generateur gen) {
		if(nbUpdate==0) {
			for(GenerateurObservateurAsync obs : m_observateurs ) {
				nbUpdate++;
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
		//m_observateurs_waiting.remove(g);
	}
}
