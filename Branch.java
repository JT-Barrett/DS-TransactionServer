import java.util.ArrayList;
public class Branch
{
	ArrayList<Account> accounts = new ArrayList<Account>();
	public Account create(String name)
	{
		Account newAccount = new Account(name);
		accounts.add(newAccount);
		return newAccount;

	}
	public Account lookUp(String name)
	{
		forEach(Account account)
		{
			if (account.name == name)
			{
				return account;
			}
		}
	}
	/*
		The below is not necessary but would be nice for displaying output
	*/
	public int branchTotal()
	{
		int total = 0;
		forEach(Account account)
		{
			total += account.balance;
		}
		return total;
	}
	public static void main(String[] args)
	{
		for(int i = 0; i < 10; i++)
		{
			Account account = create(Integer.toString(i));
			account.deposit(10);
		}
	}
}
