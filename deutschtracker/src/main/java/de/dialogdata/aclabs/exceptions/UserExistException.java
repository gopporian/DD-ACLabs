package de.dialogdata.aclabs.exceptions;

@SuppressWarnings("serial")
public class UserExistException extends Exception 
{
	public UserExistException ( String message )
	{
		super ( message );
	}
}