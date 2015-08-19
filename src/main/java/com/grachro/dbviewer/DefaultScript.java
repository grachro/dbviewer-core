package com.grachro.dbviewer;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Scriptの標準実装
 */
public abstract class DefaultScript implements Script {

	private String caption;

	public DefaultScript(String caption) {
		this.caption = caption;
	}

	@Override
	public String getScriptId() {
		return this.getClass().getName();
	}

	@Override
	public String getScriptCaption() {
		return caption;
	}

	@Override
	public List<SqlParameter> getUseParams() {
		return Collections.emptyList();
	}

	/**
	 * ScriptHolder用
	 */
	public Set<Script> getScriptList() {
		Set<Script> result = new HashSet<Script>();
		result.add(this);
		return result;
	}

	public Command execute(Database db, List<Command> commandList, String name, String sql) {
		Command command = new Command(name, sql);
		commandList.add(command);
		command.execute(db);
		return command;
	}
	
	public Command editSqlOnly(Database db, List<Command> commandList, String name, String sql) {
		Command command = new Command(name, sql);
		command.editOnly = true;
		commandList.add(command);
		return command;
	}
}
