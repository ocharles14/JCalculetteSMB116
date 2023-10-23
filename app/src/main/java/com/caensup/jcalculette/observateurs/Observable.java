package com.caensup.jcalculette.observateurs;

import java.util.ArrayList;
import java.util.List;

public class Observable implements IObservable {

    protected List<IObservateur> observateurList = new ArrayList<>();

    @Override
    public void ajouterObservateur(IObservateur obs) {
        observateurList.add( obs );
    }

    @Override
    public void retirerObservateur(IObservateur obs) {
       observateurList.remove( obs );
    }

    @Override
    public void notifierObservateurs(IObservable obs, Object etat) {
        for( IObservateur o : observateurList ) {
            o.ecouterChangementEtat( this , etat );
        }
    }
}
