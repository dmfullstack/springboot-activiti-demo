package com.zzj.it.web;

import java.util.Collection;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.zzj.it.moudels.example.entity.User;

public class MainTest {
public static void main(String[] args) {
	KnowledgeBuilder knowledgeBuilder=KnowledgeBuilderFactory.newKnowledgeBuilder();
	
	knowledgeBuilder.add(ResourceFactory.newClassPathResource("rules/example01.drl",MainTest.class), ResourceType.DRL);

	Collection<KnowledgePackage> pkgs=knowledgeBuilder.getKnowledgePackages();
	
	KnowledgeBase kbase=knowledgeBuilder.newKnowledgeBase();
	
	kbase.addKnowledgePackages(pkgs);
	StatefulKnowledgeSession ksession=kbase.newStatefulKnowledgeSession();
	
	
    // go !
    User user=new User();
    user.setUserName("zhouzj");
    ksession.insert(user);//插入
    ksession.fireAllRules();//执行规则
    ksession.dispose();
}
}
