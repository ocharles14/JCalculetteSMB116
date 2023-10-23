package com.caensup.jcalculette.observateurs;

public interface IObservateur {

    public void ecouterChangementEtat( Observable obs, Object etat );

}
