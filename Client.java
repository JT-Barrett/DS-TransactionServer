import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.io.*;

public class Client
{
  public static void main(String [] args)
  {
    for (int i = 0; i < 1; i++)
    {
      ClientThread newThread = new ClientThread();
      newThread.start();
    }
  }
}
