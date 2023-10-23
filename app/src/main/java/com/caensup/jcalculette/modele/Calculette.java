package com.caensup.jcalculette.modele;


import com.caensup.jcalculette.observateurs.Observable;

public class Calculette  extends Observable implements CalculetteI {
  private PileI<Integer> pile;
  
  public Calculette(){
	  this.pile = new Pile2<Integer>(PileI.CAPACITE_PAR_DEFAUT);
  }
  
  public Calculette(PileI<Integer> pile){
	  this.pile = pile;
  }
	public void enter(int i) throws CalculetteException {
		try{
		  pile.empiler(i);
		  notifierObservateurs( this , pile.toString() );
		}catch(PilePleineException e){
			throw new CalculetteException(e.getMessage());
		}
	}

	public int result() throws CalculetteException {
	  try{
		return pile.sommet();
	  }catch(PileVideException e){
		throw new CalculetteException(e.getMessage());
	  }
	}

	public int pop() throws CalculetteException {
		  try{
				return pile.depiler();
			  }catch(PileVideException e){
				throw new CalculetteException(e.getMessage());
		  }
    }
	
	
	public void add() throws CalculetteException {
		try{
		  int op2 = pile.depiler();
		  int op1 = pile.depiler();
		  pile.empiler(op1 + op2);
		  notifierObservateurs( this , pile.toString());
		}catch(PilePleineException e){
			throw new CalculetteException(e.getMessage());
		}catch(PileVideException e){
			throw new CalculetteException(e.getMessage());
		}
	}

	public void sub() throws CalculetteException {
		try{
			  int op2 = pile.depiler();
			  int op1 = pile.depiler();
			  pile.empiler(op1 - op2);
			  notifierObservateurs( this , pile.toString());
			}catch(PilePleineException e){
				throw new CalculetteException(e.getMessage());
			}catch(PileVideException e){
				throw new CalculetteException(e.getMessage());
			}
	}

	public void div() throws CalculetteException {
		try{
			int op2 = pile.depiler();
			int op1 = pile.depiler();
			pile.empiler(op1 / op2);
			notifierObservateurs( this , pile.toString());
		}catch(PilePleineException e){
			throw new CalculetteException(e.getMessage());
		}catch(PileVideException e){
			throw new CalculetteException(e.getMessage());
		}

	}

	public void mul() throws CalculetteException {
		// TODO Auto-generated method stub
		try{
			int op2 = pile.depiler();
			int op1 = pile.depiler();
			pile.empiler(op1 * op2);
			notifierObservateurs( this , pile.toString());
		}catch(PilePleineException e){
			throw new CalculetteException(e.getMessage());
		}catch(PileVideException e){
			throw new CalculetteException(e.getMessage());
		}

	}
	
	public void clear(){
		while(!pile.estVide()){
			try{
				pile.depiler();
			}catch(PileVideException e){	
			}
		}
		notifierObservateurs( this , pile.toString());
	}

	public String toString(){
		return pile.toString();
	}
	public boolean isEmpty(){
		return pile.estVide();
	}
	public boolean isFull(){
		 return pile.estPleine();
	}
	public int size(){
		return pile.taille();
	}
	public int capacity(){
	  return pile.capacite();
	}

}
