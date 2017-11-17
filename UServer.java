/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv;

/**
 *
 * @author Kartik
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class UServer
{
   public static void main(String args[]) throws IOException, ScriptException
      {
          try{
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  String answer = "";
                  char ch = sentence.charAt(0);
                  switch(ch)
                  {
                      case '1' : char cho = sentence.charAt(1);
                                 if(cho=='1')
                                 {
                                     String x = sentence.substring(2);
                                     StringTokenizer st = new StringTokenizer(x);
                                     double p = Double.parseDouble(st.nextToken()); 
                                     double r = Double.parseDouble(st.nextToken());
                                     double t = Double.parseDouble(st.nextToken());
                                     double SI = p*r*t/100;
                                     answer = answer+SI;
                                 }
                                 else
                                 {
                                     String x = sentence.substring(6);
                                     double t = Double.parseDouble(x);
                                     System.out.println(t);
                                     t = Math.toRadians(t);
                                     Double r = 0.0;
                                     if(sentence.contains("sin"))
                                         r = Math.sin(t);
                                     else if(sentence.contains("cos"))
                                         r = Math.cos(t);
                                     else
                                         r = Math.tan(t);
                                     answer = answer+r;       
                                 }
                                 break;
                      case '2' : char cho2 = sentence.charAt(1);
                                 String x = sentence.substring(2);
                                 x = x.toUpperCase();
                                 int ctr = 0;
                                 for(int i=0;i<x.length();i++)
                                 {  
                                     char cc = x.charAt(i);
                                     if(cc=='A'||cc=='E'||cc=='I'||cc=='O'||cc=='U')
                                         ctr++;
                                 }
                                 int v = x.length()-ctr;
                                 if(cho2=='1')
                                    answer = answer+ctr;
                                 else
                                     answer = answer+v;
                                 break;
                      case '3' : String y = sentence.substring(1);
                                 /*ScriptEngineManager mgr = new ScriptEngineManager();
                                 ScriptEngine engine = mgr.getEngineByName("JavaScript");
                                 System.out.println(y);
                                 answer = answer+engine.eval(y);*/
                      try{
                                 if(y.contains("+"))
                                 {
                                     int pos = y.indexOf('+');
                                     String n1 = y.substring(0,pos);
                                     String n2 = y.substring(pos+1);
                                     double z = Double.parseDouble(n1);
                                     double p = Double.parseDouble(n2);
                                     answer = answer+(z+p);
                                 }
                                  if(y.contains("-"))
                                 {
                                     int pos = y.indexOf('-');
                                     String n1 = y.substring(0,pos);
                                     String n2 = y.substring(pos+1);
                                     int z = Integer.parseInt(n1);
                                     int p = Integer.parseInt(n2);
                                     answer = answer+(z-p);
                                 }
                                   if(y.contains("/"))
                                 {
                                     int pos = y.indexOf('/');
                                     String n1 = y.substring(0,pos);
                                     String n2 = y.substring(pos+1);
                                     int z = Integer.parseInt(n1);
                                     int p = Integer.parseInt(n2);
                                     answer = answer+(z/p);
                                 }
                                  if(y.contains("*"))
                                 {
                                     int pos = y.indexOf('*');
                                     String n1 = y.substring(0,pos);
                                     String n2 = y.substring(pos+1);
                                     int z = Integer.parseInt(n1);
                                     int p = Integer.parseInt(n2);
                                     answer = answer+(z*p);
                                 }
                      }
                      catch(NumberFormatException e)
                      {
                          System.out.println(e);
                      }
                                 break;
                      case '4' : char cho4 = sentence.charAt(1);
                                 if(cho4=='1')
                                 {  
                                    String b = sentence.trim();
                                    int l = b.length()-2;
                                    answer = answer+l;
                                 }
                                 else if(cho4=='2')
                                 {
                                     String u = sentence.substring(2);
                                     answer = u.toUpperCase();
                                 }
                                 else if(cho4=='3')
                                 {
                                     String u = sentence.substring(2);
                                     answer = u.toLowerCase(); 
                                 }
                                 else if(cho4=='4')
                                 {
                                     String u = sentence.substring(2);
                                     int p = u.indexOf(' ');
                                     answer = u.substring(0,p)+u.substring(p+1); 
                                 }
                                 else if(ch=='5')
                                 {
                                     String q = sentence.substring(2);
                                     StringTokenizer st = new StringTokenizer(q);
                                     String a1 = st.nextToken();
                                     String a2 = st.nextToken();
                                     answer = answer+a1.compareTo(a2);
                                 }
                                 else
                                 {
                                     sentence = sentence.trim();
                                     String q = sentence.substring(2);
                                     StringTokenizer st = new StringTokenizer(q);
                                     String a1 = st.nextToken();
                                     int sta = Integer.parseInt(st.nextToken());
                                     int en = Integer.parseInt(st.nextToken());
                                     answer = q=q.substring(sta,en);
                                 }
                      default : break;           
                  }
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  sendData = answer.getBytes();
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
               }
      }
          catch(IOException e)
          {
              System.out.println(e);
          }
}
}

