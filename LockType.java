public class LockType
{
	private final String type;

  public LockType (String type){
    this.type = type;
  }
	public String getType()
	{
		return this.type;
	}

  public setType(String newType)
  {
    this.type = newType;
  }

  public promote(){
    if(this.type == "READ"){
      this.type = "WRITE";
    }
  }
}
