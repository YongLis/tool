package com.sh.lang.compile;

import java.io.File;
import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.DocumentationTool.Location;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 * 编译工具 
 */
public class CompileUtils {
	
	
	/**
	 * @param javaSource 源文件
	 * @param classpath 目标路径
	 */
	public static boolean compile(String javaSource, String classpath){
		try {
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
			StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(
			        diagnostics, null, null);
			StandardLocation oLocation = StandardLocation.CLASS_OUTPUT;
			fileManager.setLocation(oLocation,
			        Arrays.asList(new File[] { new File(classpath) }));
			Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(javaSource);
			JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager,
			        diagnostics, null, null, compilationUnits);
			boolean result = task.call();
			fileManager.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	

	public static void main(String[] args) {
		CompileUtils.compile("", "");

	}

}
