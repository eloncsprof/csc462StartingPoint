/*
 * FunctionApplication.java 1.0 January 20, 2018
 *
 * Copyright (c) 2018 David J. Powell, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

/**
 * Application sets the starting point, sets the optimization
 * technique, runs optimize and show result for a Sams Club
 * maximization problem and a Dell minimization problem. Though only
 * optimizing these two functions the framework is flexible and the
 * user can easily add more Functions.
 * 
 * @author dpowell2
 * @version 1.0
 */
public class FunctionApplication {

  /**
   * Application to optimize an Elon function using one of a variety
   * of optimization techniques. Currently the only two working
   * techniques are RandomWalk and Powell. We will change this in
   * subsequent homeworks and implment NelderMead in Homework 5
   * 
   * @param args Command line parameters that are not currently used.
   */
  public static void main(String[] args) {
    // test sams club
    double[] startPoint = { -5.0, 0.0 };
    Function function = new SamsClub(startPoint);
    System.out.println("Starting Point:\n" + function);
    function.setOptimizationTechnique("edu.elon.math.RandomWalk");
    function.optimize();
    System.out.println("Optimal Point:" + function.getOutput() + "\n" + function);

    // compare RandomWalk to Powell
    startPoint[0] = -5.0;
    startPoint[1] = 0.0;
    function = new SamsClub(startPoint);
    System.out.println("Starting Point:\n" + function);
    function.setOptimizationTechnique("edu.elon.math.Powell");
    function.optimize();
    System.out.println("Optimal Point:" + function.getOutput() + "\n" + function);

    // test dell
    startPoint[0] = 0;
    startPoint[1] = 0;
    function = new Dell(startPoint);
    System.out.println("Starting Point:\n" + function);
    function.setOptimizationTechnique("edu.elon.math.RandomWalk");
    function.optimize();
    System.out.println("Optimal Point:" + function.getOutput() + "\n" + function);
    // compare RandomWalk to Powell
    startPoint[0] = 0;
    startPoint[1] = 0;
    function = new Dell(startPoint);
    System.out.println("Starting Point:\n" + function);
    function.setOptimizationTechnique("edu.elon.math.Powell");
    function.optimize();
    System.out.println("Optimal Point:" + function.getOutput() + "\n" + function);

    System.exit(0);
  }

}
