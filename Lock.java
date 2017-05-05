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
		boolean notWaiting = true;
		while(hasConflict(aLockType))
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				System.out.println("Caught Exception: " + e.getMessage());
			}

			if(waiting)
			{
				System.out.println("TransID: " + trans.id + " || waiting for lock access.")
				waiting = false;
			}
		}
		System.out.println("\n===========================\nTransID: " + trans.id + " || free to proceed.")
		if (holders.isEmpty())
		{
			// no TIDs hold lock
			holders.addElement(trans);
			lockType  = aLockType;
			System.out.println("TransID: " + trans.id + " || aquired " + aLockType.getType() + " lock: No other TIDs present.\n===========================\n")
		}
		else if (!holders.isEmpty() && !isHolder(trans))
		{
				holders.addElement(trans);
				System.out.println("TransID: " + trans.id + " || aquired " + aLockType.getType() + " lock: Lock shared.\n===========================\n")
		}

		/* this transaction is a holder but needs amore exclusive lock*/
		else if (isHolder(trans) && aLockType.getType() == "WRITE")
		{
			lockType.promote();
			System.out.println("TransID: " + trans.id + " || aquired " + aLockType.getType() + " lock: Lock promoted.\n===========================\n")
		}
	}

	public synchronized void release(TransID trans)
	{
		holders.removeElement(trans);
		// remove this holder
		// set locktype to none
		notifyAll();
	}

	//Helper methods added in addition to book methods
	public synchronized boolean isHolder (TransID trans){
		for(int i = 0; i < holders.size(); i++ )
		{
			if (holders[i].id == trans.id)
			{
				return true;
			}
		}
		return false;
	}

	public synchronized boolean hasConflict (LockType aLockType){
		//if requesting read conflict if lock is WRITE
		if ( aLockType.getType() == "READ" && lockType.getType() == "WRITE")
		{
				return true;
		}
		//if requesting WRITE, conflict there are lock of any kind
		else if ( aLockType.getType() = "WRITE" && !holders.isEmpty()){
				return true;
		}

		return false;
	}
}
