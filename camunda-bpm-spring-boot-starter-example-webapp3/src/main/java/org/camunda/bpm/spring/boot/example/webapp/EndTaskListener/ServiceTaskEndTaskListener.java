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
@CamundaSelector(type = "serviceTask", event = TaskListener.EVENTNAME_COMPLETE)
public class ServiceTaskEndTaskListener extends ReactorTaskListener {



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
	  
	    
	//  String activityName = delegateExecution.getCurrentActivityName();
	    //logger.warn("Task Name Complete : "+delegateTask.getName());
		  logger.warn("Invoking ScriptTask Complete");
	    
	    if ((delegateTask.getBpmnModelElementInstance().getElementType().getTypeName())=="serviceTask") {
	    template.convertAndSend("/topic/notification","la tâche Service nommée : " +delegateTask.getName()+" est terminée");
	    }
	  
	    
	  }

	  @Autowired
	 	public
	     void register(CamundaEventBus eventBus) {
	       eventBus.register((TaskListener) this);
	   }
	  
}
