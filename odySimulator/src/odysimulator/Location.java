package odySimulator;

public class Location {
	private int row;
	private int col;
	private Actor actor;
	
	public Location(int row, int col){
		this.row = row;
		this.col = col;
		this.actor = null;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public void SetActor(Actor a){
		this.actor = a;
	}
	
	public Actor getActor(){
		return this.actor;
	}
	
	public String toString(){
		return Integer.toString(this.row)+","+Integer.toString(this.row);
	}
}