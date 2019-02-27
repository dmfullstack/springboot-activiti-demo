package com.zzj.it.example;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.it.Application;
import com.zzj.it.moudels.example.entity.User;

/**
 * drools 激活组
 * @author zhouzj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ActivationGroupExample {

	@Test
	public void test() {
		KnowledgeBuilder knowledgeBuilder=KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		knowledgeBuilder.add(ResourceFactory.newClassPathResource("rules/activation-group-example.drl"), ResourceType.DRL);

		Collection<KnowledgePackage> pkgs=knowledgeBuilder.getKnowledgePackages();
		
		KnowledgeBase kbase=knowledgeBuilder.newKnowledgeBase();
		
		kbase.addKnowledgePackages(pkgs);
		StatefulKnowledgeSession ksession=kbase.newStatefulKnowledgeSession();
		
		
        // go !
        User user=new User();
        user.setUserName("zhouzj");
        user.setAmount(121);
        ksession.insert(user);//插入
        ksession.fireAllRules();//执行规则
        ksession.dispose();
	}

}
