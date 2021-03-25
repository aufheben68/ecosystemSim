package odySimulator;

public class Rabbit extends Animal {
	
	public void act(){
		incrementAge();
		giveBirth();
		Location newloc = getField().randomFreeAdjacentLocation(getLocation());
		if(newloc==null){
			setDead();
		}
		else{
			setLocation(newloc);
		}
	}
	
	public Rabbit(int age, Field f, Location loc){
		super(f,loc);
		setAge(age);
	}
	
	protected Animal createAnimal(int age, Field f, Location loc){
		Animal a = new Rabbit(age, f, loc);
		return a;
	}
	
	protected int getMaxAge(){
		return 50;
	}
	
	protected int getMinBirthAge(){
		return 5;
	}
	
	protected int getMaxNewborn(){
		return 5;
	}
	
	protected double getBirthProbability(){
		return 0.15;
	}
}