public class Client
{
  String[] accountNames = ["Jessie", "George", "Otte", "Palmer", "Jacobs", "Doerry", "Maggie", "Bill", "Steve", "Samantha"];
  public static void main(String [] args)
  {
    for (int i = 0; i < 100; i++)
    {
      ClientThread newThread = new ClientThread();
      newThread.start();
    }
  }

  public class ClientThread extends Thread {

    public void run(){
       //message a withdrawl and a deposit to TransactionServer
       int accountIndex = randInt(0, 9);
       int randAmmount = randInt(0, 50);

       /* withdrawl random amount from random account */

       accountIndex = randInt(0, 9);
       /* deposit same amount elsewhere */

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
