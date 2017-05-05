import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

class TransThread implements Runnable {
   public String accountName;
   public String type;
   public int amount;
   public LockManager lockmngr;
   public Branch myBranch;
   public int transCount;

   public TransThread(String accountName, String type, int amount, LockManager lockmngr, Branch myBranch, int transCount) {
       this.accountName = accountName;
       this.type = type;
       this.amount = amount;
   }

   public void run() {
      //create a new transaction object
      TransID trans = new TransID(transCount);
      Account acc = myBranch.lookUp(this.accountName);
      LockType lockt = new LockType("READ");

      //read lock account and get balance
      lockmngr.setLock(acc, trans, lockt);
      int balance = acc.getBalance();
      lockmngr.unLock(trans);

      //write lock account and perform the transaction
      lockt = new LockType("WRITE");
      //if it's a deposit, add, if it's a withdrawl, subtract
      if(this.type == "deposit")
      {
        lockmngr.setLock(acc, trans, lockt);
        acc.setBalance( balance + this.amount);
        lockmngr.unLock(trans);
        System.out.println("TransID: " + trans.id + " deposited $" + this.amount + " into account '" + this.accountName + "'");
      } else {
        lockmngr.setLock(acc, trans, lockt);
        acc.setBalance( balance - this.amount);
        lockmngr.unLock(trans);
        System.out.println("TransID: " + trans.id + " withdrew $" + this.amount + " from account '" + this.accountName + "'");
      }
      //remove reference to object to delete it
      trans = null;
   }
}
