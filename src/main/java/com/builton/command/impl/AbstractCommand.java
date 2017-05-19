package com.builton.command.impl;

import com.builton.command.Command;

public abstract class AbstractCommand implements Command {

	protected Object data;
	
	protected Object[] args;
	
	public AbstractCommand() {}
	
	public AbstractCommand(Object data, Object[] args) {
		this.data=data;
		this.args=args;
	}
	
	@Override
	public Object getData() {
		return data;
	}

	@Override
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public Object[] getArgs() {
		return args;
	}

	@Override
	public void setArgs(Object[] args) {
		this.args = args;
	}
}
