/**
 * Text2Encrypt.java 1.0 January 20, 2018
 *
 * Copyright (c) 2018 Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Application program to encrypt a text file
 * 
 * @author dpowell2
 * @version 1.0
 * 
 */
public class Text2Encrypt {

  /**
   * Reads an unencrypted text file and writes it out to a file
   * encryped.
   * 
   * @param args String [] of 2 strings. The first string is the
   *        filename of a text file to read. The second string is the
   *        filename to have the encrypted contents written to.
   */
  public static void main(String[] args) {
    int exitStatus = 0;
    int iChar = -1;
    FileReader fr = null;
    PrintWriter pw = null;

    if (args.length != 2) {
      String usage = "Useage: java Text2Encrypt textfile  encryptfile";
      System.out.println(usage);
      exitStatus = 1;
    } else {
      try {
        fr = new FileReader(args[0]);
        pw = new PrintWriter(new EncryptWriter(new FileWriter(args[1])));
        while ((iChar = fr.read()) != -1) {

          pw.print((char) iChar);
        }
        fr.close();
        pw.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.exit(exitStatus);
  }

}
