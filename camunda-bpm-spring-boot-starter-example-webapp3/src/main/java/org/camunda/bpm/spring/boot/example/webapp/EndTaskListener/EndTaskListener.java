package org.camunda.bpm.spring.boot.example.webapp.EndTaskListener;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.extension.reactor.CamundaReactor;
import org.camunda.bpm.extension.reactor.bus.CamundaEventBus;
import org.camunda.bpm.extension.reactor.bus.CamundaSelector;
import org.camunda.bpm.extension.reactor.spring.listener.ReactorTaskListener;
import org.camunda.bpm.spring.boot.example.webapp.EndProcessListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

//type = "userTask",
@Component
@CamundaSelector(type = "userTask", event = TaskListener.EVENTNAME_COMPLETE)
public class EndTaskListener extends ReactorTaskListener {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  
	
	@Autowired
	  private SimpMessagingTemplate template;
	
	
  public void init() {
	    CamundaEventBus eventBus = CamundaReactor.eventBus();
	    eventBus.register(new EndProcessListener());
	} 
	
 
  
  
  @Override
  public void notify(DelegateTask delegateTask) {
   // logger.debug("assigned to {}", delegateTask.getAssignee());
    logger.warn("Invoking UserTask Complete");
    
//  String activityName = delegateExecution.getCurrentActivityName();
   // String a2 = delegateExecution.getProcessBusinessKey();
   // logger.warn("TaskDefinitionKey Complete Task : "+delegateTask.getTaskDefinitionKey());
    //logger.warn("Task Name Complete : "+delegateTask.getName());

   
 // tache static 

/*
    logger.warn("getDescription : "+delegateTask.getDescription().toString());
    logger.warn("getId : "+delegateTask.getId().toString());
    logger.warn(delegateTask.getCaseDefinitionId());
    		 logger.warn(delegateTask.getTaskDefinitionKey());
    		 logger.warn(delegateTask.toString());
    		 logger.warn(delegateTask.getExecutionId());
    		 logger.warn(delegateTask.getExecution().getEventName());
   */
   
   
   //test si user task
    
    //generalisé 
   template.convertAndSend("/topic/notification","la tâche User : " +delegateTask.getName()+" est terminée");
    
  }

 
  @Autowired
 	public
    void register(CamundaEventBus eventBus) {
      eventBus.register((TaskListener) this);
  }

 
}