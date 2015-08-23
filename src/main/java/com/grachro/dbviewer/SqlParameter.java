package com.grachro.dbviewer;

import org.apache.commons.lang3.StringUtils;

public class SqlParameter {
	private final String name;
	private final String caption;
	private final String description;

	public SqlParameter(String name, String caption, String description) {
		super();
		this.name = name;
		this.caption = caption;
		this.description = description;
	}

	public SqlParameter(String name, String caption) {
		this(name, caption, null);
	}

	public SqlParameter(String name) {
		this(name, null, null);
	}

	public String getName() {
		return this.name;
	}

	public String getCaption() {
		if (StringUtils.isEmpty(caption)) {
			return name;
		}
		return caption;
	}

	public String getDescription() {
		return StringUtils.defaultString(this.description, StringUtils.EMPTY);
	}

}
