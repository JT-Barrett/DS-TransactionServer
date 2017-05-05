// From Textbook, completed by JT Barrett and David Miller
import java.io.*;
import java.util.*;

public class Lock
{
	private Object object;
	// the object being protected by the lock
	private Vector holders;
	// the TIDs of current hol	ders
	private LockType lockType;
	// the current type
	public void setObject (Object newObj){
		this.object = newObj;
	}
	public Object getObject (){
		return this.object;
	}

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

			if(notWaiting)
			{
				System.out.println("TransID: " + trans.id + " || waiting for lock access.");
				notWaiting = false;
			}
		}
		System.out.println("TransID: " + trans.id + " || free to proceed.");
		if (holders.isEmpty())
		{
			// no TIDs hold lock
			holders.addElement(trans);
			lockType  = aLockType;
			System.out.println("TransID: " + trans.id + " || aquired " + aLockType.getType() + " lock: No other TIDs present.");
		}
		else if (!holders.isEmpty() && !isHolder(trans))
		{
				holders.addElement(trans);
				System.out.println("TransID: " + trans.id + " || aquired " + aLockType.getType() + " lock: Lock shared.");
		}

		/* this transaction is a holder but needs amore exclusive lock*/
		else if (isHolder(trans) && aLockType.getType() == "WRITE")
		{
			lockType.promote();
			System.out.println("TransID: " + trans.id + " || aquired " + aLockType.getType() + " lock: Lock promoted.");
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
			TransID transTemp = (TransID) holders.get(i);
			if (transTemp.getID() == trans.id)
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
		else if ( aLockType.getType() == "WRITE" && !holders.isEmpty()){
				return true;
		}

		return false;
	}
}
