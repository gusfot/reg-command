package com.builton.command;

public interface Command {

	Object execute() ;

	void setData(Object text);

	Object getData();

	void setArgs(Object[] args);
	
	Object[] getArgs();
		
}
