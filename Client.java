
public class Client
{
  public static void main(String [] args)
  {
    for (int i = 0; i < 50; i++)
    {
      ClientThread newThread = new ClientThread();
      newThread.start();
    }
  }
}


