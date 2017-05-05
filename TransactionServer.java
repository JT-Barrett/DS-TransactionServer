import java.net.ServerSocket;
import java.net.Socket;

import java.io.ObjectInputStream;

import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TransactionServer
{
  int transaction = 0;

  public static void main(String [] args)
  {
    Branch Bigbranch = new Branch();
    Bigbranch.create("Jessie", 10);
    Bigbranch.create("George", 10);
    Bigbranch.create("Otte", 10);
    Bigbranch.create("Palmer", 10);
    Bigbranch.create("Jacobs", 10);
    Bigbranch.create("Doerry", 10);
    Bigbranch.create("Maggie", 10);
    Bigbranch.create("Bill", 10);
    Bigbranch.create("Steve", 10);
    Bigbranch.create("Samantha", 10);

    Hashtable<Object, Lock> accountLocks = new Hashtable<Object, Lock>();
    LockManager accountLockManager = new LockManager(accountLocks);

    System.out.println("Accounts initiated. Waiting for transaction requests...");

    //wait for messages and send them to threads as they come in
    ObjectInputStream readFromNet;
    TransMessage msg;

    while(true){
      readFromNet = new ObjectInputStream(client.getInputStream());
      msg = (TransMessage) readFromNet.readObject();

      Runnable r = new transThread(msg.accountName, msg.type, msg.amount);
      new Thread(r).start();
    }

    class transThread implements Runnable {
       public String accountName;
       public String type;
       public int amount;

       public transThread(String accountName, String type, int amount) {
           this.accountName = accountName;
           this.type = type;
           this.amount = amount;
       }

       public void run() {
          //create a new transaction object
          TransID trans = new TransID(id);
          Account acc = Bigbranch.lookUp(this.accountName); // somehow this isn't allowed and causing a cascade of other errors??
          LockType lockt = new LockType("READ");

          //read lock account and get balance
          accountLockManager.setLock(acc, trans, lockt); // where is this declared??
          balance = acc.getBalance();
          accountLockManager.unLock(trans);

          //write lock account and perform the transaction
          lockt = new LockType("WRITE");
          //if it's a deposit, add, if it's a withdrawl, subtract
          if(this.type == "deposit")
          {
            accountLockManager.setLock(acc, trans, lockt);
            acc.setBalance( balance + this.amount);
            accountLockManager.unLock(trans);
            System.out.println("TransID: " + trans.id + " deposited $" + this.amount + " into account '" + this.accountName + "'");
          } else {
            accountLockManager.setLock(acc, trans, lockt);
            acc.setBalance( balance - this.amount);
            accountLockManager.unLock(trans);
            System.out.println("TransID: " + trans.id + " withdrew $" + this.amount + " from account '" + this.accountName + "'");
          }
          //remove reference to object to delete it
          trans = null;
       }
    }
  }
}
