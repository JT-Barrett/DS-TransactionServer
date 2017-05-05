public class TransMessage
{
	private String accountName;
	private String type;
	private int amount;

	public TransMessage(String accountName, String type, int amount) {
			this.accountName = accountName;
			this.type = type;
			this.amount = amount;
	}

	public String getAccountName(){
		return this.accountName;
	}
	public String getType(){
		return this.type;
	}
	public int getAmount(){
		return this.amount;
	}
}
