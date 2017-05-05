public class Coordinator
{
	public TransID openTransaction()
	{
		/*
			Starts a new transaction and delivers a unique TID trans. This
			identifier will be used in the other operations in the transaction.
		*/
		return new TransID();
	}
	public boolean closeTransaction(TransID id)
	{
		/*
			End a transaction: a commit return value indicates that the transaction
			has committed; an abort return value indicates that it has aborted.
		*/
		return false;
	}
}
