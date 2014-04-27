package de.dialogdata.aclabs.common;

import java.io.Serializable;
import javax.inject.Named;


/**
 * Class which checks the permissions of an user
 * 
 * Current permissions :
 * 
 *          read  |  create |  delete  |  update  |
 * student   X    |    -    |    -     |    -     |
 * teacher   X    |    X    |    X     |    X     |
 */

@Named
public class AbstractBE implements Serializable
{
    public boolean canRead ( ) { return true; }
    public boolean canDelete ( )
    {
    	return isTeacher ( );
    }
    public boolean canCreate ( )
    {
    	return isTeacher ( );
    }
    public boolean canUpdate ( )
    {
    	return isTeacher ( );
    }
    
    private boolean isTeacher ( )
    {
    	return true; // doar pentru testare - cazul in care user-ul e admin - se da display
    	//return false; // doar pentru testare - cazul in care user-ul nu e admin - nu se da display
    	//return SecurityUtils.getSession ( ).getAttribute ( profile ).equals ( "teacher" );
    }
	
	private static final long serialVersionUID = 7699425363999956376L;
	
}