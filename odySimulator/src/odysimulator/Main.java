package odySimulator;

public class Main {
	public static void main(String[] args) {
		Simulation sim = new Simulation("sim-population.txt");
		sim.simulate(1000);
	}
}