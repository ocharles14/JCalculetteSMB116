package com.caensup.jcalculette.observateurs;

public interface IObservable {

    public void ajouterObservateur( IObservateur obs );
    public void retirerObservateur( IObservateur obs );
    public void notifierObservateurs(IObservable obs , Object etat );
}
