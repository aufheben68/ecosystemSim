package odySimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Field {
	private int numRows;
	private int numCols;
	private ArrayList<ArrayList<Location>> locations;
	
	public Field(int numRows, int numCols){
		int i,j;
		this.numRows = numRows;
		this.numCols = numCols;
		locations = new ArrayList<ArrayList<Location>>(this.numRows);
		for(i=0;i<numRows;i++){
			ArrayList<Location> locationrows = new ArrayList<Location>(this.numCols);
			for(j=0;j<numCols;j++){
				locationrows.add(new Location(i, j));
			}
			locations.add(locationrows);
		}
	}
	
	public int getNumRows(){
		return this.numRows;
	}
	
	public int getNumCols(){
		return this.numCols;
	}
	
	public void clear(){
		numRows = 0;
		numCols = 0;
		locations = null;
	}
	
	public void clear(Location loc){
		loc.SetActor(null);
	}
	
	public void place(Actor actor, Location loc){
		locations.get(loc.getRow()).get(loc.getCol()).SetActor(actor);
	}
	
	public Actor getActorAt(Location loc){
		return loc.getActor();
	}
	
	public ArrayList<ArrayList<Location>> getLocations(){
		return locations;
	}
	
	public ArrayList<Location> adjacentLocations(Location loc){
		int row = loc.getRow();
		int col = loc.getCol();
		int lastrow = numRows-1;
		int lastcol = numCols-1;
		ArrayList<Location> returnval = new ArrayList<Location>();
		if(row==0 && col==0){ // loc exists in the first row / first column
			returnval.add(locations.get(1).get(0));
			returnval.add(locations.get(1).get(1));
			returnval.add(locations.get(0).get(1));
		}
		else if(row==0 && col==lastcol){ // loc exists in the first row / last column
			returnval.add(locations.get(0).get(lastcol-1));
			returnval.add(locations.get(1).get(lastcol-1));
			returnval.add(locations.get(1).get(lastcol));
		}
		else if(row==lastrow && col==0){ // loc exists in the last row / first column
			returnval.add(locations.get(lastrow-1).get(0));
			returnval.add(locations.get(lastrow-1).get(1));
			returnval.add(locations.get(lastrow).get(1));
		}
		else if(row==0 && col==lastcol){ // loc exists in the last row / last column
			returnval.add(locations.get(lastrow-1).get(lastcol));
			returnval.add(locations.get(lastrow-1).get(lastcol-1));
			returnval.add(locations.get(lastrow).get(lastcol-1));
		}
		else if(row>0 && row<lastrow && col==0){ // loc exists between first and last row / first column
			returnval.add(locations.get(row-1).get(0));
			returnval.add(locations.get(row-1).get(1));
			returnval.add(locations.get(row).get(1));
			returnval.add(locations.get(row+1).get(1));
			returnval.add(locations.get(row+1).get(0));
		}
		else if(row>0 && row<lastrow && col==lastcol){ // loc exists between first and last row / last column
			returnval.add(locations.get(row-1).get(lastcol));
			returnval.add(locations.get(row-1).get(lastcol-1));
			returnval.add(locations.get(row).get(lastcol-1));
			returnval.add(locations.get(row+1).get(lastcol-1));
			returnval.add(locations.get(row+1).get(lastcol));
		}
		else if(row==0 && col>0 && col<lastcol){ // loc exists in the first row / between first and last column
			returnval.add(locations.get(0).get(col-1));
			returnval.add(locations.get(1).get(col-1));
			returnval.add(locations.get(1).get(col));
			returnval.add(locations.get(1).get(col+1));
			returnval.add(locations.get(0).get(col+1));
		}
		else if(row==lastrow && col>0 && col<lastcol){ // loc exists in the last row / between first and last column
			returnval.add(locations.get(lastrow).get(col-1));
			returnval.add(locations.get(lastrow-1).get(col-1));
			returnval.add(locations.get(lastrow-1).get(col));
			returnval.add(locations.get(lastrow-1).get(col+1));
			returnval.add(locations.get(lastrow).get(col+1));
		}
		else if(row>0 && row<lastrow && col>0 && col<lastcol){ // loc exists in a non-edge cell of the field
			returnval.add(locations.get(row-1).get(col-1));
			returnval.add(locations.get(row-1).get(col));
			returnval.add(locations.get(row-1).get(col+1));
			returnval.add(locations.get(row).get(col-1));
			returnval.add(locations.get(row).get(col+1));
			returnval.add(locations.get(row+1).get(col-1));
			returnval.add(locations.get(row+1).get(col));
			returnval.add(locations.get(row+1).get(col+1));
		}
		else{
			// Error
		}
		// shuffle the list
	    Collections.shuffle(returnval);
	    return returnval;
	}
	
	public ArrayList<Location> freeAdjacentLocations(Location loc){
		int i;
		ArrayList<Location> returnval = adjacentLocations(loc);
		for(i=returnval.size()-1;i>=0;i--){
			if(returnval.get(i).getActor()!=null){
				returnval.remove(i);
			}
		}
		return returnval;
	}
	
	public Location randomFreeAdjacentLocation(Location loc){
		ArrayList<Location> loclist = freeAdjacentLocations(loc);
		if(loclist.size() > 0){
			int rndnum = randInt(0, loclist.size()-1);
			return loclist.get(rndnum);
		}
		return null;
	}
	
	public Location randomLocation(){
		int i,j;
		ArrayList<Location> loclist = new ArrayList<Location>();
		for(i=0;i<numRows;i++){
			for(j=0;j<numCols;j++){
				loclist.add(locations.get(i).get(j));
			}
		}
		if(loclist.size() > 0){
			int rndnum = randInt(0, loclist.size()-1);
			return loclist.get(rndnum);
		}
		return null;
	}
	
	public Location randomFreeLocation(){
		int i,j;
		ArrayList<Location> loclist = new ArrayList<Location>();
		for(i=0;i<numRows;i++){
			for(j=0;j<numCols;j++){
				if(locations.get(i).get(j).getActor()==null){
					loclist.add(locations.get(i).get(j));
				}
			}
		}
		if(loclist.size() > 0){
			int rndnum = randInt(0, loclist.size()-1);
			return loclist.get(rndnum);
		}
		return null;
	}
	
	public void print(){
		int i,j;
		Actor a;
		for(i=0;i<this.numRows;i++){
			for(j=0;j<this.numCols;j++){
				a = locations.get(i).get(j).getActor();
				if(a == null){
					System.out.print(".");
				}
				else{
					if (a instanceof Rabbit) {
						System.out.print("R");
					}
					else if (a instanceof Fox) {
						System.out.print("F");
					}
					else if (a instanceof Hunter) {
						System.out.print("H");
					}
				}
				if(j < (this.numCols-1)){
					System.out.print(" ");
				}
			}
			if(i < (this.numRows-1)){
				System.out.println();
			}
		}
	}
	
	public void printStats(){
		int i,j;
		int sumrabbits=0,sumfoxes=0,sumhunters=0;
		Actor a;
		for(i=0;i<this.numRows;i++){
			for(j=0;j<this.numCols;j++){
				a = locations.get(i).get(j).getActor();
				if(a != null){
					if (a instanceof Rabbit) {
						sumrabbits++;
					}
					else if (a instanceof Fox) {
						sumfoxes++;
					}
					else if (a instanceof Hunter) {
						sumhunters++;
					}
				}
			}
		}
		System.out.println();
		System.out.println("Rabbits: "+sumrabbits);
		System.out.println("Foxes  : "+sumfoxes);
		System.out.println("Hunters: "+sumhunters);
		System.out.println();
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}