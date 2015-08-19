package com.grachro.dbviewer;

import java.util.List;
import java.util.Map;


public interface Script {
	public String getScriptId();

	public String getScriptCaption();

	public List<SqlParameter> getUseParams();

	public void doScript(List<Database> databaseList, List<Command> commandList, Map<String, String> sqlParams);
}
