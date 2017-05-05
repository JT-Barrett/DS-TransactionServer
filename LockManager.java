// From Textbook, completed by JT Barrett and David Miller
import java.lang.Integer;
import java.util.*;

public class LockManager
{
	private Hashtable <Object, Lock> theLocks;

	public LockManager (Hashtable <Object, Lock> hash){
		this.theLocks = hash;
	}

	public void setLock(Object object, TransID trans, LockType lockType)
	{
		Lock foundLock;
		synchronized(this)
		{
            // find the lock associated with object
            foundLock = searchHashTableForLock(object);

			// if there isn’t one, create it and add it to the hashtable
            if (foundLock == null)
            {
                foundLock = new Lock();
								foundLock.setObject(object);
								theLocks.put(foundLock.getObject(), foundLock);
            }
		}
		foundLock.acquire(trans, lockType);

	}
	// synchronize this one because we want to remove all entries
	public synchronized void unLock(TransID trans)
	{
		Enumeration e = theLocks.elements();
		while(e.hasMoreElements())
		{
			Lock aLock = (Lock)(e.nextElement());
			if(aLock.isHolder(trans))
			{
				aLock.release(trans);
			}
		}
	}

    public Lock searchHashTableForLock(Object obj)
    {
        for (Object object2: theLocks.entrySet()){
            // make a reference to the object as a map entry
            Map.Entry entry = (Map.Entry) obj;
            if(entry.getKey().equals(obj))
            {
                return entry.getValue();
            }
        }
        return null;
    }

}
