package de.dialogdata.aclabs.common;

import java.io.Serializable;

import javax.inject.Named;

import de.dialogdata.aclabs.utils.SecurityUtils;

@Named
public class AbstractBean implements Serializable
{
    public boolean canCreate ( ) { return true; }
    public boolean canDelete ( )
    {
    	return isTeacher ( );
    }
    public boolean canRead ( )
    {
    	return isTeacher ( );
    }
    public boolean canUpdate ( )
    {
    	return isTeacher ( );
    }
    
    private boolean isTeacher ( )
    {
    	return SecurityUtils.getSession ( ).getAttribute ( profile ).equals ( "teacher" );
    }
	
	private static final long serialVersionUID = 7699425363999956376L;
	
}