package exception;

import java.lang.Exception;

import javax.swing.JOptionPane;

public class ValueDomainException extends Exception{
	private String targetValue;
	private String description;
	private String domainCondition;
	
	public ValueDomainException(String targetValue, String description, String domainCondition){
		super();
		this.targetValue = targetValue;
		this.description = description;
		this.domainCondition = domainCondition;
	}
	
	@Override
	public String getMessage(){
		return description + "'s value : " + targetValue + " is out of the domain : " + domainCondition;
	}
	
	public void occurValueDomainError(Exception e){
		JOptionPane.showMessageDialog(null,
				getMessage(), "ValueDomainError", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
		System.exit(1);
	}
}
