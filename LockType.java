public class LockType
{
	private String type;

  public LockType (String type){
    this.type = type;
  }
	public String getType()
	{
		return this.type;
	}

  public void setType(String newType)
  {
    this.type = newType;
  }

  public void promote(){
    if(this.type == "READ"){
      this.type = "WRITE";
    }
  }
}
