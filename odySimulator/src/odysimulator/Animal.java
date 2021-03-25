package odySimulator;

import java.util.ArrayList;
import java.util.Random;

public abstract class Animal implements Actor {
	private Field field;
	private Location loc;
	private int age;
	private boolean alive;

	
	public abstract void act();

	public boolean isAlive(){
		return this.alive;
	}

	public Animal(Field f, Location loc){
		this.field = f;
		this.loc = loc;
	}

	public void setDead(){
		this.alive = false;
		this.loc.SetActor(null);
	}
	
	protected Location getLocation(){
		return loc;
	}
	
	protected void setLocation(Location newLoc){
		this.loc.SetActor(null);
		this.loc = newLoc;
	}
	
	protected Field getField(){
		return this.field;
	}
	
	protected int getAge(){
		return this.age;
	}
	
	protected void setAge(int a){
		this.age = a;
	}
	
	protected void incrementAge(){
		this.age++;
		if(this.age > getMaxAge()){
			setDead();
		}
	}
	
	protected abstract Animal createAnimal(int age, Field f, Location loc);
	
	protected void giveBirth(){
		Animal a;
		ArrayList<Location> freeadjacentlocations = field.freeAdjacentLocations(this.loc);
		int numberofnewborn = numberOfNewborn();
		int i;
		if(numberofnewborn > freeadjacentlocations.size()){
			numberofnewborn = freeadjacentlocations.size();
		}
		for(i=0;i<numberofnewborn;i++){
			a = createAnimal(0, field, freeadjacentlocations.get(i));
			freeadjacentlocations.get(i).SetActor(a);
		}
	}

	protected int numberOfNewborn(){
		if(this.age >= getMinBirthAge()){
			float rndnum = randFloat(0.0f,1.0f);
			if(rndnum < getBirthProbability()){
				int M = getMaxNewborn();
				int newbornnum =  randInt(1,M);
				return newbornnum;
			}
		}
		return 0;
	}

	protected abstract int getMaxAge();
	
	protected abstract int getMinBirthAge();
	
	protected abstract int getMaxNewborn();
	
	protected abstract double getBirthProbability();
	
	protected static float randFloat(float min, float max) {
		float random;
		random = (float)Math.random() < 0.5 ? ((1-(float)Math.random()) * (max-min) + min) : ((float)Math.random() * (max-min) + min);
		return random;
	}
	
	protected static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}