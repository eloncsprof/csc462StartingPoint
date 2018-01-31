/*
 * Function.java 1.0 January 20, 2018
 *
 * Copyright (c) 2018 David J. Powell, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.util.ArrayList;

/**
 * Standard interface for consistency in the Elon evaluation process.
 * Each concrete Elon Function must extend this class.
 * 
 * @author dpowell2
 * @version 1.0
 */
public abstract class Function {

  /**
   * constant to represent new line
   */
  public static final String EOL = "\n";

  /**
   * constant to represent one blank space
   */
  public static final String SPACE = " ";

  private ArrayList<String> inputNames;

  private ArrayList<Double> inputValues;

  // true if minimization function and false if maximization
  private boolean minimize;

  private String optimizationTechnique = "edu.elon.math.RandomWalk";

  private Double output;

  private String title;

  /**
   * Default constructor
   */
  public Function() {
    // empty
  }

  /**
   * Evaluates the current set of input values to calculate the
   * function value. We currently consider one output value for a
   * function. If the function has multiple output values then the
   * function must have these combined into a single value.
   * 
   * @return Double of function result from evaluation at current
   *         point.
   */
  public abstract Double evaluate();

  /**
   * Returns an ArrayList of String for the names of each input
   * parameter. This allows the class creator to make the names
   * meaningful to a user instead of X1, X2, ...
   * 
   * @return ArrayList<String> of names for each input parameter
   */
  public ArrayList<String> getInputNames() {
    return inputNames;
  }

  /**
   * Returns the current value of each input for the function. All
   * function inputs are treated as Double
   * 
   * @return ArrayList<Double> of values representing current point.
   */
  public ArrayList<Double> getInputValues() {
    return inputValues;
  }

  /**
   * Gets the full package qualified classname of the currently set
   * optimization technique
   * 
   * @return String representing package qualified classname of
   *         optimization technique
   */
  public String getOptimizationTechnique() {
    return optimizationTechnique;
  }

  /**
   * Gets the function output value resulting from the evaluation of
   * the current input set.
   * 
   * @return Double representing function result
   */
  public Double getOutput() {
    return output;
  }

  /**
   * Gets the name of the function
   * 
   * @return String representing the user friendly name of the
   *         function.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Gets the direction of the optimization problem. If true then we
   * have a minimization problem otherwise a maximization problem
   * 
   * @return boolean value of true if minimization
   */
  public boolean isMinimize() {
    return minimize;
  }

  /**
   * Optimizes function. The optimizer is required to leave the best
   * design point and function value as the current state of the
   * function in <code>inputValues</code> and <code>output</code>
   * 
   * @return Double representing best achieved function value.
   */
  public Double optimize() {
    Function function = this;
    Double optimalValue = null;

    if (this.getOptimizationTechnique().contains("NelderMead")) {
      NelderMead nm = new NelderMead();
      optimalValue = nm.goSimplex(function);
      function.getInputValues();
    } else if (this.getOptimizationTechnique().contains("RandomWalk")) {
      RandomWalk rw = new RandomWalk();
      optimalValue = rw.guess(function);
      function.getInputValues();
    } else if (this.getOptimizationTechnique().contains("Powell")) {
      Powell powell = new Powell();
      optimalValue = powell.findMinimum(function);
      function.getInputValues();
    }

    return optimalValue;
  }

  /**
   * Set the current set of input names for the input parameters to
   * the inputNames passed as a parameter.
   * 
   * @param inputNames ArrayList<String> of names for set of input
   *        parameters for the function.
   */
  public void setInputNames(ArrayList<String> inputNames) {
    this.inputNames = inputNames;
  }

  /**
   * Sets the current value of the input set for the function.
   * 
   * @param inputValues ArrayList<Double> representing the value of
   *        each input parameter. The input set is assumed to be all
   *        Doubles.
   */
  public void setInputValues(ArrayList<Double> inputValues) {
    this.inputValues = inputValues;
  }

  /**
   * Sets function to be a minimization or a maximization
   * 
   * @param minimize boolean of true if minimization
   */
  public void setMinimize(boolean minimize) {
    this.minimize = minimize;
  }

  /**
   * Sets internal field to the value of the passed parameter which
   * represents the package qualified class name of the optimization
   * technique to use.
   * 
   * @param optimizationTechnique String representing package and
   *        class name of the optimizer to use for the problem.
   */
  public void setOptimizationTechnique(String optimizationTechnique) {
    this.optimizationTechnique = optimizationTechnique;
  }

  /**
   * Sets the value of the function result.
   * 
   * @param output Double instance of function result
   */
  public void setOutput(Double output) {
    this.output = output;
  }

  /**
   * Sets the user friendly name of the function
   * 
   * @param title String representing function name
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * User friendly representation of the function state and
   * configuration. Shows the function name, input variable names and
   * input variable values
   * 
   * @return String representing state of function.
   */
  @Override
  public String toString() {
    StringBuffer s = new StringBuffer();
    s.append("Function: " + this.getTitle() + EOL);
    for (int i = 0; i < getInputValues().size(); i++) {
      s.append(getInputNames().get(i) + SPACE + getInputValues().get(i) + EOL);
    }
    return s.toString();
  }

}
