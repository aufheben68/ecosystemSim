package odySimulator;

public class Hunter implements Actor {
	private Field field;
	private Location loc;
	
	public void act(){
		Location newloc = getField().randomFreeLocation();
		setLocation(newloc);
		fire();fire();fire();fire();fire();fire();
	}
	
	public boolean isAlive(){
		return true;
	}
	
	
	public Hunter(Field f, Location loc){
		this.field = f;
		this.loc = loc;
	}
	
	@SuppressWarnings("unused")
	private Location getLocation(){
		return this.loc;
	}
	
	private void setLocation(Location loc){
		this.loc.SetActor(null);
		this.loc = loc;
	}
	
	private Field getField(){
		return  this.field;
	}
	
	private void fire(){
		Actor a;
		Location randomlocation = getField().randomLocation();
		a = randomlocation.getActor();
		if(a != null){
			if (a instanceof Rabbit || a instanceof Fox) {
				((Animal)a).setDead();
			}
		}
	}
}