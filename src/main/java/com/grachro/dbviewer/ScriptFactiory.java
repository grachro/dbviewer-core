package com.grachro.dbviewer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.grachro.util.ClassUtils;

public class ScriptFactiory {
	private final static String METHOD_NAME = "getScriptList";

	/**
	 * packageName直下でScriptHolderアノテーションが付けられた全クラスを返します。
	 */
	public static Set<Script> loadScriptsFromPackageName(String packageName) {
		List<Class<?>> classList;
		try {
			classList = ClassUtils.loadClasses(packageName);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		Set<Script> result = new HashSet<Script>();

		Set<Class<?>> scriptHolderList = ClassUtils.filterAnnotation(classList, ScriptHolder.class);
		for (Class<?> clazz : scriptHolderList) {
			Set<Script> scriptList = loadScripts(clazz);
			result.addAll(scriptList);
		}

		return result;
	}

	/**
	 * holderClassをインスタンス化し、getScriptList()からスクリプトのリストを取得
	 */
	private static Set<Script> loadScripts(Class<?> holderClass) {

		Method method;
		try {
			method = holderClass.getMethod(METHOD_NAME);
		} catch (Exception e) {
			throw new IllegalStateException(holderClass + "." + METHOD_NAME + " access is fatal..", e);
		}

		try {
			Object holder = holderClass.newInstance();
			@SuppressWarnings("unchecked")
			Set<Script> scriptList = (Set<Script>) method.invoke(holder);

			return scriptList;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

	}

	public static Set<Script> loadScriptsFromHolderObjects(Object holder) {

		Method method;
		try {
			method = holder.getClass().getMethod(METHOD_NAME);
		} catch (Exception e) {
			throw new IllegalStateException(holder.getClass() + "." + METHOD_NAME + " access is fatal..", e);
		}

		try {
			@SuppressWarnings("unchecked")
			Set<Script> scriptList = (Set<Script>) method.invoke(holder);

			return scriptList;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
