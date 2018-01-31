/**
 * Powell.java 1.0 January 20, 2018
 *
 * Copyright (c) 2018 David J. Powell. All Rights Reserved
 */
package edu.elon.math;

import com.cureos.numerics.Calcfc;
import com.cureos.numerics.Cobyla;
import com.cureos.numerics.CobylaExitStatus;

import java.util.ArrayList;

/**
 * Class uses an implementation from GitHub at
 * https://github.com/cureos/jcobyla that is a java implementation of
 * the Michael Powell Direct Search Method. No. There is no
 * relationship between Dave Powell and Michael Powell
 *
 * @author dpowell2
 * @version 1.0
 *
 */
public class Powell {
  private int maxIterations = 40000;
  private int printLevel = 0;
  private double rhobeg = 0.5;
  private double rhoend = 1.0e-06;

  /**
   * Creates instance of Michael Powell's optimization algorithm with
   * default values of initialization of ten thousand evaluations.
   */
  public Powell() {
    // intentionally empty
  }

  /**
   * Given a Function to optimize with a given starting point, use
   * Powell direct optimization method for 10000 iterations. When
   * completed return the optimal value and leave set the best input
   * vector in the function
   * 
   * @param function Function model to be optimized
   * @return optimal value found.
   */
  public double findMinimum(Function function) {
    ArrayList<Double> startingPoint = function.getInputValues();
    double[] inputArray = convertArrayListToDouble(startingPoint);
    int numberInputs = inputArray.length;
    Calcfc calcfc = new Calcfc() {

      @Override
      public double Compute(int aN, int aM, double[] aX, double[] aCon) {
        function.setInputValues(convertDoubleArrayToArrayList(aX));
        double result = function.evaluate();
        if (!function.isMinimize()) {
          result = result * -1;
        }
        return result;
      }
    };

    CobylaExitStatus status =
      Cobyla.FindMinimum(calcfc, numberInputs, 0, inputArray, rhobeg, rhoend, printLevel, maxIterations);
    System.out.println("Powell optimization exit status: " + status);

    // at end the optimal input vector is in numberInputs
    // need to put into function and evaluate one last time and return
    // optimal vaue
    function.setInputValues(convertDoubleArrayToArrayList(inputArray));
    double result = function.evaluate();
    // need to return the actual value and not the sign flip for
    // optimization
    if (!function.isMinimize()) {
      result = result * -1;
    }
    return result;
  }

  /**
   * Converts an arraylist of Double into an array of doubles
   * 
   * @param aStartingPoint arraylist of Double representing an input
   *        point for an optimization problem
   * @return double array of input point represented as a 1D vector
   */
  private double[] convertArrayListToDouble(ArrayList<Double> aStartingPoint) {
    int length = aStartingPoint.size();
    double[] inputArray = new double[length];
    for (int i = 0; i < length; i++) {
      inputArray[i] = aStartingPoint.get(i);
    }
    return inputArray;
  }

  /**
   * Converts a input point repesented as a one dimensional array of
   * doubles into an arraylist of double
   * 
   * @param aInputArray input array of double values
   * @return input arraylist of Double values
   */
  private ArrayList<Double> convertDoubleArrayToArrayList(double[] aInputArray) {
    ArrayList<Double> bestInputPoint = new ArrayList<Double>();
    for (double d : aInputArray) {
      bestInputPoint.add(d);
    }
    return bestInputPoint;
  }

}
