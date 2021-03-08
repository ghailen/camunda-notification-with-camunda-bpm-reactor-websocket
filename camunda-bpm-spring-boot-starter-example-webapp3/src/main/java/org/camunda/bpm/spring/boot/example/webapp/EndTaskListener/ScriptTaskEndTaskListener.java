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

@Component
@CamundaSelector(type ="scriptTask", event = TaskListener.EVENTNAME_COMPLETE)
public class ScriptTaskEndTaskListener extends ReactorTaskListener {
	

	 private final Logger logger = LoggerFactory.getLogger(getClass());
	  

	 
		@Autowired
		  private SimpMessagingTemplate template;
		
		
	  public void init() {
		    CamundaEventBus eventBus = CamundaReactor.eventBus();
		    eventBus.register(new EndProcessListener());
		} 
		  

	  @Autowired
		public
	   void register(CamundaEventBus eventBus) {
	     eventBus.register((TaskListener) this);
	 }
	  
	  
	  @Override
	  public void notify(DelegateTask delegateTask) {
	   // logger.debug("assigned to {}", delegateTask.getAssignee());
	    logger.warn("Invoking ScriptTask Complete");

	    //logger.warn("TaskDefinitionKey Complete Task : "+delegateTask.getTaskDefinitionKey());
		  
	    //logger.warn("Task Name Complete : "+delegateTask.getName());
	    
	 //   logger.warn("l'id de task script est  : "+delegateTask.getId());
	    
	   if ((delegateTask.getBpmnModelElementInstance().getElementType().getTypeName())=="scriptTask")
	    {	    
	    template.convertAndSend("/topic/notification","la tâche Script nommée : " +delegateTask.getName()+" est terminée");
	    }
	  
	    
	  }


}
