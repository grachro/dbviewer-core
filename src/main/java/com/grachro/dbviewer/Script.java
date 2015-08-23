package com.grachro.dbviewer;

import java.util.List;
import java.util.Map;

public interface Script {
	String getScriptId();

	String getScriptCaption();

	List<SqlParameter> getUseParams();

	void doScript(List<Database> databaseList, List<Command> commandList, Map<String, String> sqlParams);

	String getDescription();
}
