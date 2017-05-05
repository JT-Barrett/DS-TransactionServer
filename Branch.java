import java.util.ArrayList;
public class Branch
{
	ArrayList<Account> accounts = new ArrayList<Account>();
	public Account create(String name, int balance)
	{
		Account newAccount = new Account(name, balance);
		accounts.add(newAccount);
		return newAccount;

	}
	public Account lookUp(String name)
		{
		for(Account account : accounts)
		{
			if (account.name == name)
			{
				return account;
			}
		}
	}
}
