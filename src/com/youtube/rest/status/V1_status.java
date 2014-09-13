/**
 * 
 */
package com.youtube.rest.status;

/**
 * @author Ramesh R. Baral
 * @Version 1.0
 * @since Sep 12, 2014
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.youtube.dao.Oracle308tube;

@Path("/v1/status")
public class V1_status {

private static final String api_version = "00.02.00"; //version of the api
	
	/** 
	 * This method sits at the root of the api.  It will return the name
	 * of this api.
	 * 
	 * @return String - Title of the api
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root.  You will need to add version
	 * into the URL path.
	 * 
	 * Example:
	 * http://localhost:7001/com.youtube.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}
	
	/**
	 * This method will connect to the oracle database and return the date/time stamp.
	 * It will then return the date/time to the user in String format
	 * 
	 * This was explained in Part 3 of the Java Rest Tutorial Series on YouTube
	 * 
	 * Pre-episode 6, updated because Oracle308tube.java no longer accessible.
	 * 
	 * @return String -  returns the database date/time stamp
	 * @throws Exception
	 */
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		PreparedStatement query=null;
		String myString=null;
		String returnString = null;
		Connection conn=null;
		
		try {
			
			conn=Oracle308tube.Oracle308tubeConn().getConnection();
			query=conn.prepareStatement("select now()");
			ResultSet rs=query.executeQuery();
			while(rs.next()){
				myString=rs.getString("now()");
			}
			
			returnString = "<p>Database Status</p> " +
				"<p>MySQL Database Date/Time return: " + myString + "</p>";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			conn.close();
		}
		
		return returnString; 
	}
	
}
