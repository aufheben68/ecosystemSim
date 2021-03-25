package odySimulator;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Simulation {
	String filename;
	Field field;
	
	public Simulation(String filename){
		reset(filename);
	}
	
	public void simulate(int numSteps){
		int i;
		field.print();
		field.printStats();
		for(i=0;i<numSteps;i++){
			simulateOneStep();
		}
		field.print();
		field.printStats();
	}
	
	private void simulateOneStep(){
		int i,j;
		for(i=0;i<field.getNumRows();i++){
			for(j=0;j<field.getNumCols();j++){
				Actor a = field.getLocations().get(i).get(j).getActor();
				if(a!=null){
					a.act();
				}
			}
		}
	}
	
	public void reset(String filename){
		int i=0,j=0;
		int numRows,numCols,nextint;
		this.filename = filename;
		Actor newactor;
		
		// Read the field data
		try{
			Scanner rowReader = new Scanner(new File(this.filename));
			Scanner colReader;
			// Read numrows and numcols
			numRows = rowReader.nextInt(); // First line is the number of the rows
			rowReader.nextLine();
			numCols = rowReader.nextInt(); // Second line is the number of the columns
			rowReader.nextLine();
			// Create the field
			this.field = new Field(numRows, numCols);
			
			// Read the rest of the data
			Location newloc; i=0; j=0;
			while(rowReader.hasNextLine())
			{
				j=0;
			    colReader = new Scanner(rowReader.nextLine());
			    while(colReader.hasNextInt())
			    {
			    	newloc = new Location(i,j);
			    	nextint = colReader.nextInt();
			    	if(nextint==1){
			    		newactor = new Hunter(field,newloc);
			    	}
			    	else if(nextint==2){
			    		newactor = new Fox(randInt(0,150),field,newloc);
			    	}
			    	else if(nextint==3){
			    		newactor = new Rabbit(randInt(0,50),field,newloc);
			    	}
			    	else{
			    		newactor = null;
			    	}
			    	this.field.place(newactor, newloc);
			        j++;
			    }
			    i++;
			}
			rowReader.close();
		} catch (IOException ex) {
		  // report
		}
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}