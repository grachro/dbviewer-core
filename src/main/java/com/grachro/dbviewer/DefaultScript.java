package com.grachro.dbviewer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Scriptの標準実装
 */
public abstract class DefaultScript implements Script {

	private String caption;
	private String description;
	private List<SqlParameter> parameterList = new ArrayList<SqlParameter>();

	public DefaultScript(String caption) {
		this.caption = caption;
	}

	public DefaultScript(String caption, String description) {
		this.caption = caption;
		this.description = description;
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
		return parameterList;
	}

	public void addParameter(String name) {
		parameterList.add(new SqlParameter(name));
	}

	public void addParameter(String name, String caption) {
		parameterList.add(new SqlParameter(name, caption));
	}

	public void addParameter(String name, String caption, String description) {
		parameterList.add(new SqlParameter(name, caption, description));
	}

	/**
	 * ScriptHolder用
	 */
	public Set<Script> getScriptList() {
		Set<Script> result = new HashSet<Script>();
		result.add(this);
		return result;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Command execute(Database db, List<Command> commandList, String name, String sql) {
		Command command = new Command(name, sql);
		commandList.add(command);
		command.execute(db);
		return command;
	}

	public Command editSqlOnly(List<Command> commandList, String name, String sql) {
		Command command = new Command(name, sql);
		command.editOnly = true;
		commandList.add(command);
		return command;
	}
}
