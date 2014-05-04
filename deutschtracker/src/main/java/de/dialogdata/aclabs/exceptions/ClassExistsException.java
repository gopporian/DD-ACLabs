package de.dialogdata.aclabs.exceptions;

@SuppressWarnings("serial")
public class ClassExistsException extends Exception
{
	public ClassExistsException ( String message ) 
	{
		super ( message );
	}
}