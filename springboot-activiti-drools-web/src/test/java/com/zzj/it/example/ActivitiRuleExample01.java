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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzj.it.Application;
import com.zzj.it.moudels.example.entity.User;

/**
 * drools 激活组
 * activation-group 激活组指定规则为一组时，只会选择一条规则进行执行
 * 使用
 * @author zhouzj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ActivitiRuleExample01 {
	private final Logger logger=LoggerFactory.getLogger(ActivitiRuleExample01.class);
	@Test
	public void test() {
		KnowledgeBuilder knowledgeBuilder=KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		knowledgeBuilder.add(ResourceFactory.newClassPathResource("rules/activiti-rule-example01.drl"), ResourceType.DRL);

		Collection<KnowledgePackage> pkgs=knowledgeBuilder.getKnowledgePackages();
		
		KnowledgeBase kbase=knowledgeBuilder.newKnowledgeBase();
		
		kbase.addKnowledgePackages(pkgs);
		StatefulKnowledgeSession ksession=kbase.newStatefulKnowledgeSession();
		
		
        // go !
        User user=new User();
        user.setUserName("zhouzj");
        user.setAmount(121);
        user.setIdentity("gold");
        
        ksession.insert(user);//插入
        ksession.fireAllRules();//执行规则
        logger.error("优惠后金额,{}",user.getResult());
        
        user=new User();
        user.setUserName("zhouzj");
        user.setAmount(121);
        user.setIdentity("silver");
        
        ksession.insert(user);//插入
        ksession.fireAllRules();//执行规则
        logger.error("优惠后金额,{}",user.getResult());
        ksession.dispose();
	}

}
