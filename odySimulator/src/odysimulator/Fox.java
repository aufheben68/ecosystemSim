package odySimulator;

import java.util.ArrayList;

public class Fox extends Animal {
	private int food;
	// Inherited from Actor->Animal
	
	public void act(){
		incrementAge();
		giveBirth();
		Location findfood = findFood();
		if(findfood!=null){
			this.food += 7;
			((Rabbit)findfood.getActor()).setDead();
			setLocation(findfood);
		}
		else{
			Location newloc = getField().randomFreeAdjacentLocation(getLocation());
			if(newloc==null){
				setDead();
			}
			else{
				setLocation(newloc);
			}
		}
		reduceFoodLevel();
	}
	
	public Fox(int age, Field f, Location loc){
		super(f,loc);
		setAge(age);
		this.food = 7;
	}
	
	protected Animal createAnimal(int age, Field f, Location loc){
		Animal a = new Fox(age, f, loc);
		return a;
	}
	
	protected int getMaxAge(){
		return 150;
	}
	
	protected int getMinBirthAge(){
		return 10;
	}
	
	protected int getMaxNewborn(){
		return 5;
	}
	
	protected double getBirthProbability(){
		return 0.35;
	}
	
	private void reduceFoodLevel(){
		this.food--;
		if(this.food <= 0){
			setDead();
		}
	}
	
	private Location findFood(){
		Actor a;
		ArrayList<Location> adjacentlocations = getField().freeAdjacentLocations(getLocation());
		int i;
		for(i=0;i<adjacentlocations.size();i++){
			a = adjacentlocations.get(i).getActor();
			if(a != null){
				if (a instanceof Rabbit) {
					return adjacentlocations.get(i);
				}
			}
		}
		return null;
	}
}