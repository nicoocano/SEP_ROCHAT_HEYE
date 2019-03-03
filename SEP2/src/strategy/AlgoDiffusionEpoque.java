package strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import client.Generateur;
import client.GenerateurImpl;
import client.ObservateurGenerateur;
import service.GenerateurObservateurAsync;

public class AlgoDiffusionEpoque implements AlgoDiffusion{

	public Map<ObservateurGenerateur,List<Integer>> m_mapObservateurValeurs;
	private Set<GenerateurObservateurAsync> m_observateurs;
	private GenerateurImpl m_gen;
	
	public AlgoDiffusionEpoque() {
		m_mapObservateurValeurs = new HashMap<ObservateurGenerateur,List<Integer>>();
		m_observateurs = new HashSet<GenerateurObservateurAsync>();
	}
	public boolean getLocked() {
		return true;
	}
	public void execute(Generateur gen) {
		    m_gen=(GenerateurImpl) gen;
			for(GenerateurObservateurAsync obs : m_observateurs ) {
				obs.update(gen);
			}
	
	}
	
	public void getValue(ObservateurGenerateur o) {
		if (m_mapObservateurValeurs.containsKey(o)) {
			m_mapObservateurValeurs.get(o).add(m_gen.value);
		}
		else {
			List<Integer> tmp = new ArrayList<Integer>();
			tmp.add(m_gen.value);
			m_mapObservateurValeurs.put(o, tmp);
		}
	}
	
	public void attach(GenerateurObservateurAsync obs) {
		m_observateurs.add(obs);
	}
	
	public void remove(GenerateurObservateurAsync g) {
		//m_observateurs_waiting.remove(g);
	}
}