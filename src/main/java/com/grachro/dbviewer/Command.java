package com.grachro.dbviewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class Command {
	private String dbCaption;
	private String name;
	private String sql;
	private List<DbRecord> result = Collections.emptyList();
	public boolean editOnly;
	
	public Command(String name, String sql) {
		this.name = name;
		this.sql = sql;
	}

	public String getDbCaption() {
		return dbCaption;
	}

	public String getName() {
		return name;
	}

	public String getSql() {
		return sql;
	}

	public void execute(Database database) {
		dbCaption = database.getCaption();
		OutPut.output("");
		OutPut.output(sql);
		OutPut.output(name);
		result = database.query(sql);
		OutPut.outputHead(result);
		OutPut.outputData(result);
	}

	public String getFirstVal(String colName) {
		if (result == null || result.isEmpty()) {
			return null;
		}
		return result.get(0).get(colName.toUpperCase());
	}

	public String getLastVal(String colName) {
		if (result == null || result.isEmpty()) {
			return null;
		}

		return result.get(result.size() - 1).get(colName.toUpperCase());
	}

	public Set<String> getHeaders() {
		if (result == null || result.isEmpty()) {
			return Collections.emptySet();
		}
		return result.get(0).keySet();
	}

	public List<DbRecord> getResult() {
		return result;
	}

	public List<String> getValues(String colName) {

		if (this.result == null || this.result.isEmpty()) {
			return Collections.emptyList();
		}

		List<String> result = new ArrayList<String>();
		for (DbRecord record : this.result) {
			String value = record.get(colName.toUpperCase());
			result.add(value);
		}

		return result;
	}

	public String jointedStringValues(String colName) {

		List<String> base = getValues(colName);
		List<String> newList = new ArrayList<String>();
		for (String s : base) {
			newList.add("'" + s + "'");
		}

		return StringUtils.join(newList, ",");

	}
}
