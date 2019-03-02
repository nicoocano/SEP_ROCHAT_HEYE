package service;

import java.util.concurrent.ScheduledFuture;

import client.ObservateurGenerateur;

public interface GenerateurAsync {

	public void attach(ObservateurGenerateur o);
	public void detach(ObservateurGenerateur o);
	public ScheduledFuture<Integer>  getValue();
	public void remove();
}
