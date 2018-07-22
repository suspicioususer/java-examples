package form;

import org.apache.struts.validator.ValidatorForm;

public class MainForm extends ValidatorForm {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}