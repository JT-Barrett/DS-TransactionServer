// From Textbook, completed by JT Barrett and David Miller
import java.io.*;

public class Lock
{
	private Object object;
	// the object being protected by the lock
	private Vector holders;
	// the TIDs of current holders
	private LockType lockType;
	// the current type
	public synchronized void acquire(TransID trans, LockType aLockType )
	{
		while(/*another transaction holds the lockin conflicting mode*/)
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				System.out.println("Caught Exception: " + e.getMessage());
			}
		}
		if (holders.isEmpty())
		{
			// no TIDs hold lock
			holders.addElement(trans);
			lockType  = aLockType;
		}
		else if (/*another transaction holds the lock, share it*/))
		{
			if (/* this transaction not a holder*/)
			{
				holders.addElement(trans);
			}
		}
		else if (/* this transaction is a holder but needs amore exclusive lock*/)
		{
			lockType.promote();
		}
	}
	public synchronized void release(TransID trans )
	{
		holders.removeElement(trans);
		// remove this holder
		// set locktype to none
		notifyAll();
	}
}
