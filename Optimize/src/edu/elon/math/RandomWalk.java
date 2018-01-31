/*
 * RandomWalk.java 1.0 January 20, 2018
 *
 * Copyright (c) 2018 David J. Powell, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.util.ArrayList;

/**
 * Randomly try different points and keep the best seen. The default
 * number of evaluations is 10000
 * 
 * @author dpowell2
 * @version 1.0
 * 
 */
public class RandomWalk {
  private ArrayList<Double> bestInputValues;
  private double largestValue;
  private int maxNumberEvaluations;
  private Double optimalValue;

  private double smallestValue;

  /**
   * Default constructor sets limits on range of random value
   * selection to plus and minus 100. Sets maximum number of function
   * evaluations to 10000
   * 
   */
  public RandomWalk() {
    maxNumberEvaluations = 10000;
    smallestValue = -100.0;
    largestValue = 100.0;
  }

  /**
   * makes a copy of the input values.
   * 
   * @param start ArrayList<Double> representing an input point.
   * @return ArrayList<Double> representing duplicate copy of input
   *         point.
   * 
   */
  public ArrayList<Double> copy(ArrayList<Double> start) {
    ArrayList<Double> newArray = new ArrayList<Double>();
    for (int i = 0; i < start.size(); i++) {
      newArray.add(new Double(start.get(i)));
    }
    return newArray;
  }

  /**
   * Gets the maximum value any input parameter may have.
   * 
   * @return double representing largest value of an input parameter.
   */
  public double getLargestValue() {
    return largestValue;
  }

  /**
   * Gets the maximum number of function evaluations allowed before
   * termination
   * 
   * @return int representing max number of function evaluations
   */
  public int getMaxNumberEvaluations() {
    return maxNumberEvaluations;
  }

  /**
   * Gets the smallest value that a input may have
   * 
   * @return double representing the smallest value that an input may
   *         have
   */
  public double getSmallestValue() {
    return smallestValue;
  }

  /**
   * Side effect of setting the optimal value and the optimal values
   * of inputs in the function instance passed as a parameter.
   * 
   * @param f Function instance
   * @return Double instance containing best objective value
   */
  public Double guess(Function f) {
    Double currentValue;
    ArrayList<Double> currentValues = new ArrayList<Double>();
    int size = f.getInputValues().size();
    // evaluate base point to set as currentBest
    optimalValue = f.evaluate();
    bestInputValues = copy(f.getInputValues());

    // loop until max interations
    for (int j = 0; j < maxNumberEvaluations; j++) {
      currentValues = new ArrayList<Double>();

      for (int i = 0; i < size; i++) {
        currentValues.add(new Double(randomDouble()));
      }
      f.setInputValues(currentValues);
      currentValue = f.evaluate();
      //
      if (!f.isMinimize()) {
        if (currentValue.doubleValue() > optimalValue.doubleValue()) {
          optimalValue = currentValue;
          bestInputValues = currentValues;
        }
      } else {
        if (currentValue.doubleValue() < optimalValue.doubleValue()) {
          optimalValue = currentValue;
          bestInputValues = currentValues;
        }
      }
    }
    // set best inputs and value in f
    f.setInputValues(bestInputValues);
    f.setOutput(optimalValue);
    return optimalValue;
  }

  /**
   * randomly picks a value between the allowable limits.
   * 
   * @return double value within input range.
   */
  public double randomDouble() {
    return smallestValue + ((largestValue - smallestValue) * Math.random());
  }

  /**
   * Sets the largest value that an input may have
   * 
   * @param largestValue double representing the largest value that an
   *        input may have.
   */
  public void setLargestValue(double largestValue) {
    this.largestValue = largestValue;
  }

  /**
   * Set the maximum number of function evaluations based on value of
   * parameter.
   * 
   * @param maxNumberEvaluations int representing max number of
   *        function evaluations.
   */
  public void setMaxNumberEvaluations(int maxNumberEvaluations) {
    this.maxNumberEvaluations = maxNumberEvaluations;
  }

  /**
   * Sets the smallest value that an input may have
   * 
   * @param smallestValue int double representing the smallest value
   *        that an input may have.
   */
  public void setSmallestValue(double smallestValue) {
    this.smallestValue = smallestValue;
  }

}
