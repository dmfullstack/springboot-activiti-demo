package com.zzj.it.common.geneator;

import org.mybatis.generator.api.ShellRunner;

public class CreateMybatis {

	public static void main(String[] args) {
		String path=System.getProperty("user.dir");
		args=new String[] {"-configfile",path+"\\src\\main\\resources\\mybatis-generator.xml","-overwrite"};
		ShellRunner.main(args);
	}

}
