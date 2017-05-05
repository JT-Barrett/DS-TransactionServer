import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;


public class TransactionServer
{
  static int transaction = 0;

  public static void main(String [] args)
  {
    Branch bigbranch = new Branch();
    bigbranch.create("Jessie", 10);
    bigbranch.create("George", 10);
    bigbranch.create("Otte", 10);
    bigbranch.create("Palmer", 10);
    bigbranch.create("Jacobs", 10);
    bigbranch.create("Doerry", 10);
    bigbranch.create("Maggie", 10);
    bigbranch.create("Bill", 10);
    bigbranch.create("Steve", 10);
    bigbranch.create("Samantha", 10);

    Hashtable<Account, Lock> accountLocks = new Hashtable<Account, Lock>();
    LockManager accountLockManager = new LockManager(accountLocks);

    System.out.println("Accounts initiated. Waiting for transaction requests...");

    //wait for messages and send them to threads as they come in
    ServerSocket server = null;
    Socket client = null;
    TransMessage msg = null;
    try{
      server = new ServerSocket(23657);
      client = server.accept();
    } catch (Exception e) {
      System.out.println("could not connect socket to client");
    }
    while(true){
      try {
      ObjectInputStream readFromNet = new ObjectInputStream(client.getInputStream());
      msg = (TransMessage) readFromNet.readObject();
      } catch(Exception e) {
        System.out.println("Server could not receive anything from the socket.");
      }


      TransThread r = new TransThread(msg.getAccountName(), msg.getType(), msg.getAmount(), accountLockManager, bigbranch, transaction++);
      new Thread(r).start();
    }
  }
}
