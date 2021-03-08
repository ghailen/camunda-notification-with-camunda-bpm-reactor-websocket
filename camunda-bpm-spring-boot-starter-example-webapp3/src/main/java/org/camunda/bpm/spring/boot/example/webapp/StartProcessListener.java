package org.camunda.bpm.spring.boot.example.webapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.extension.reactor.CamundaReactor;
import org.camunda.bpm.extension.reactor.bus.CamundaEventBus;
import org.camunda.bpm.extension.reactor.bus.CamundaSelector;
import org.camunda.bpm.extension.reactor.projectreactor.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@CamundaSelector(type = "startEvent", event = ExecutionListener.EVENTNAME_END)
public class StartProcessListener implements ExecutionListener {

  Logger logger = LoggerFactory.getLogger(StartProcessListener.class);
public static List<String> list=new ArrayList<String>();
  @Autowired
  private SimpMessagingTemplate template;
  
  public void init() {
	    CamundaEventBus eventBus = CamundaReactor.eventBus();
	    eventBus.register(new EndProcessListener());
	} 
	  
  
  @Autowired
  void register(CamundaEventBus eventBus) {
    eventBus.register(this);
  }
  
  @Override
  public void notify(DelegateExecution delegateExecution) throws Exception {
    logger.warn("invoking process Listener");

    
     
    template.convertAndSend("/topic/notification","le processus avec l'ID "  +delegateExecution.getProcessDefinitionId()+"  est lancé");
    //template.convertAndSend("/topic/notification","Votre demande est en cours de traitement");
   
   // List<String> list=new ArrayList<String>();
  //  System.out.println("var : "+delegateExecution.getVariables());
   // System.out.println("variable : "+delegateExecution.getVariableNames());
	  delegateExecution.getVariableNames().stream().forEach(x->{
		  list.add(x);
	  });
	//  System.out.println("la liste : "+list);
  
  }

/*
  public List<String> getvariableproc(DelegateExecution delegateExecution) throws Exception{
	  	  
	  List<String> list=new ArrayList<String>();
	  delegateExecution.getVariableNames().stream().forEach(x->{
		  list.add(x);
	  });
	  
	     return list;
  }*/
  
  
}