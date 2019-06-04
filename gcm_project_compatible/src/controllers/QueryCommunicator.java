package controllers;


import java.io.Serializable;

//class that have a query and the type of the query. Objects from this class will be send from client to server
public class QueryCommunicator implements Serializable{
	// Class variables 
	private static final long serialVersionUID = 1L;

	private String query;
	private type t;
	
	public enum type {SELECT_query, UPDATE_query};
	
	// Class methods
	public QueryCommunicator(String query) //gets query and classifies it using the first word
	{
		this.query=query;
		if(query.startsWith("SELECT"))
			t=type.SELECT_query;
		else {
			if(query.startsWith("UPDATE"))
				t=type.UPDATE_query;
		}
	}
	
	public type getType()
	{
		return t;
	}
	
	public String getQuery()
	{
		return query;
	}
}
