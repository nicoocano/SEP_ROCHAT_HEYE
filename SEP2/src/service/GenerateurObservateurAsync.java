package service;

import java.util.concurrent.ScheduledFuture;

import client.Generateur;

public interface GenerateurObservateurAsync {

	public ScheduledFuture<Void> update(Generateur g);
}
