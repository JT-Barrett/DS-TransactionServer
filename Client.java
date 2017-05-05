import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client
{
  String[] accountNames = ["Jessie", "George", "Otte", "Palmer", "Jacobs", "Doerry", "Maggie", "Bill", "Steve", "Samantha"];
  public static void main(String [] args)
  {
    for (int i = 0; i < 50; i++)
    {
      ClientThread newThread = new ClientThread();
      newThread.start();
    }
  }

  public class ClientThread extends Thread {

    public void run(){

      //String host = "";
      //int port = ;

      //set up random account and random
      int accountIndex = randInt(0, accountNames.length - 1);
      int randAmmount = randInt(0, 50);

      Socket server = new Socket(host, port);

      // create job and job request message
      TransMessage msg = new TransMessage(accountNames[accountIndex], "withdrawl", randAmmount);

      // sending withdrawl out to the transaction server in a message
      ObjectOutputStream writeToNet = new ObjectOutputStream(server.getOutputStream());
      writeToNet.writeObject(msg);

      accountIndex = randInt(0, 9);
      // create job and job request message
      TransMessage msg = new TransMessage(accountNames[accountIndex], "deposit", randAmmount);

      // sending deposit out to the transaction server in a message
      ObjectOutputStream writeToNet = new ObjectOutputStream(server.getOutputStream());
      writeToNet.writeObject(msg);

       //close the thread by returning
       return;
    }
  }

  public static int randInt(int min, int max) {

    Random rand = new Random();

    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
  }
}
