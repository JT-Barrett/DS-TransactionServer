public class Account
{
	private int balance;
	public final String name;
	public Account(String name, int balance)
	{
		this.name = name;
		this.balance = balance;
	}
	public synchronized void setBalance(int amount)
	{
		balance = amount;
	}
	public synchronized int getBalance()
	{
		return balance;
	}
	public synchronized void withdraw(int amount)
	{
		balance -= amount;
	}
	public synchronized void deposit(int amount)
	{
		balance += amount;
	}
}


