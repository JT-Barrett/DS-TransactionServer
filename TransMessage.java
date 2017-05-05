public class TransMessage
{
	public String accountName;
	public String type;
	public int amount;

	public TransMessage(String accountName, String type, int amount) {
			this.accountName = accountName;
			this.type = type;
			this.amount = amount;
	}
}

