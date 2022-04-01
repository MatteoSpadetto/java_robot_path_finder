package de.tuhh.diss.lab3;

import MazebotSim.MazebotSimulation;
import MazebotSim.Visualization.GuiMazeVisualization;

public class MainSimulated {
	
	/**
	 *  This class is only intended to set up and start a simulation. Don't put
	 *  task specific information into this class. Just call the main function,
	 *  e.g., Task1.main() of the program you want to simulate in here. 
	 *  
	 *  You may, however, change the maze, or starting position here. For detailed
	 *  information about the simulator and how it can be configured, visit
	 *  https://collaborating.tuhh.de/cuy1171/diss-mazebot-simulation/-/wikis/home
	 */
	public static void main(String[] args) {
		MazebotSimulation sim = new MazebotSimulation("mazes/mazes_test/blue.png", 1.5,  1.13);
		GuiMazeVisualization gui = new GuiMazeVisualization(1.5, sim.getStateAccessor());
		sim.scaleSpeed(1);
		sim.setRobotPosition(0.5, 0.5, 90);
		
		sim.startSimulation();
		gui.startVisualization();	
				
		Task3.main(args);
				
		sim.stopSimulation();
	}

}
