package exception;

import java.lang.Exception;

import javax.swing.JOptionPane;

public class ExceptionalCaseException extends Exception{
	private String targetValue;
	private String description;
	
	public ExceptionalCaseException(String targetValue, String description){
		super();
		this.targetValue = targetValue;
		this.description = description;
	}
	
	@Override
	public String getMessage(){
		return description + "'s case for value : " + targetValue + " is not prepared";
	}
	
	public void occurExceptionalCaseError(Exception e){
		JOptionPane.showMessageDialog(null,
				getMessage(), "CaseError", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
		System.exit(1);
	}
}
