package custom;

public class CommonValues {
	
	private volatile boolean state = true;
	private volatile String result = "";

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	
	
	

}
