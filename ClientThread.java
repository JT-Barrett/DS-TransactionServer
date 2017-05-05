import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.io.*;

public class ClientThread extends Thread {

  public void run(){
    String[] accountNames = {"Jessie", "George", "Otte", "Palmer", "Jacobs", "Doerry", "Maggie", "Bill", "Steve", "Samantha"};

    //set up random account and random
    int accountIndex = randInt(0, accountNames.length - 1);
    int randAmmount = randInt(0, 50);
    Socket server = null;
    try{
      server = new Socket("localhost", 23657);
    } catch (Exception e) {
      System.out.println("could not connect socket to server");
    }
    // create job and job request message

    try{
      TransMessage msg = new TransMessage(accountNames[accountIndex], "withdrawl", randAmmount);
      // sending withdrawl out to the transaction server in a message
      ObjectOutputStream writeToNet = new ObjectOutputStream(server.getOutputStream());
      //error happening here
      writeToNet.writeObject(msg);

      accountIndex = randInt(0, 9);
      // create job and job request message
      msg = new TransMessage(accountNames[accountIndex], "deposit", randAmmount);

      // sending deposit out to the transaction server in a message
      writeToNet = new ObjectOutputStream(server.getOutputStream());
      System.out.println("hello1");
      writeToNet.writeObject(msg);
      System.out.println("hello1");
    } catch (Exception e) {
      System.out.println("could not send messages through socket");
    }

     //close the thread by returning
     return;
  }

  public static int randInt(int min, int max) {

    Random rand = new Random();

    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
  }
}
