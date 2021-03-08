package org.camunda.bpm.spring.boot.example.webapp.StartTaskListener;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.extension.reactor.CamundaReactor;
import org.camunda.bpm.extension.reactor.bus.CamundaEventBus;
import org.camunda.bpm.extension.reactor.bus.CamundaSelector;
import org.camunda.bpm.extension.reactor.spring.listener.ReactorTaskListener;
import org.camunda.bpm.spring.boot.example.webapp.EndProcessListener;
import org.camunda.bpm.spring.boot.example.webapp.StartProcessListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@CamundaSelector(type= "serviceTask" ,event = TaskListener.EVENTNAME_CREATE)
public class StartServiceTaskListener extends  ReactorTaskListener {

	  @Autowired
	  private SimpMessagingTemplate template;
	  
	  Logger logger = LoggerFactory.getLogger(StartProcessListener.class);
	  
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
  
    	if ((delegateTask.getBpmnModelElementInstance().getElementType().getTypeName())=="serviceTask")
        {
      template.convertAndSend("/topic/notification","la tâche Service nommée" +delegateTask.getName()+" qui est delegué à "+delegateTask.getAssignee()+" est exécutée");
      //template.convertAndSend("/topic/notification", delegateTask.getTaskDefinitionKey()+" : est exécutée");
      
        }
    	
 
      
    }
    
    
   
    
}
