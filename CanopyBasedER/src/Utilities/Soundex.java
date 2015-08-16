/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author JanithaT
 */
public class Soundex { 
    private static String getCode(char c){
  switch(c){
    case 'B': case 'F': case 'P': case 'V':
      return "1";
    case 'C': case 'G': case 'J': case 'K':
    case 'Q': case 'S': case 'X': case 'Z':
      return "2";
    case 'D': case 'T':
      return "3";
    case 'L':
      return "4";
    case 'M': case 'N':
      return "5";
    case 'R':
      return "6";
    default:
      return "";
  }
}
 
public static String soundex(String s){
  String code, previous, soundex;
    //System.out.println(s);
  code = s.toUpperCase().charAt(0) + "";
  previous = "7";
  for(int i = 1;i < s.length();i++){
    String current = getCode(s.toUpperCase().charAt(i));
    if(current.length() > 0 && !current.equals(previous)){
      code = code + current;
    }
    previous = current;
  }
  soundex = (code + "0000").substring(0, 4);
  return soundex;
}



}