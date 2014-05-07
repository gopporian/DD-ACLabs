package de.dialogdata.aclabs.exceptions;

@SuppressWarnings("serial")
public class UserExistsException extends Exception 
{
	public UserExistsException ( String message )
	{
		super ( message );
	}
}