/**
 * Encrypt2Text.java 1.0 January 20, 2018
 *
 * Copyright (c) 2018 Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Application program to decrypt an encrypted file to a text file
 * 
 * @author dpowell2
 * @version 1.0
 * 
 */
public class Encrypt2Text {

  /**
   * Reads an encrypted file and writes it out to a unencrypted text
   * file.
   * 
   * @param args String [] of 2 strings. The first string is the
   *        filename of a encrypted file to read. The second string is
   *        the filename to have the unencrypted contents written to.
   */
  public static void main(String[] args) {
    int exitStatus = 0;
    DecryptReader dr = null;
    PrintWriter pw = null;
    int iChar = -1;

    if (args.length != 2) {
      String usage = "Useage: java Encrypt2Text encryptfile textfile";
      System.out.println(usage);
      exitStatus = 1;
    } else {
      try {
        dr = new DecryptReader(new BufferedReader(new FileReader(args[0])));
        pw = new PrintWriter(new FileWriter(args[1]));
        while ((iChar = dr.read()) != -1) {
          pw.print((char) iChar);
        }
        dr.close();
        pw.close();
      } catch (FileNotFoundException e) {
        String message = "the encrpted file, " + args[0] + "was not found";
        System.out.println(message);
        e.printStackTrace();
      } catch (IOException e) {
        String message1 = "IO exception when running Encrypt2Text";
        System.out.println(message1);
        e.printStackTrace();
      }
    }
    System.exit(exitStatus);

  }

}
