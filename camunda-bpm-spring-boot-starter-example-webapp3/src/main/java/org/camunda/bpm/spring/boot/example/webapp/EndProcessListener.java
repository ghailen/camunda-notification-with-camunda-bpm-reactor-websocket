package org.camunda.bpm.spring.boot.example.webapp;

import org.camunda.bpm.engine.delegate.DelegateExecution;
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
@CamundaSelector(type = "endEvent", event = ExecutionListener.EVENTNAME_END)
public class EndProcessListener implements ExecutionListener {

	Logger logger = LoggerFactory.getLogger(StartProcessListener.class);
	
	@Autowired
	  private SimpMessagingTemplate template;
	
	public void init() {
	    CamundaEventBus eventBus = CamundaReactor.eventBus();
	    eventBus.register(new EndProcessListener());
	} 
	 

	  @Override
	  public void notify(DelegateExecution delegateExecution) throws Exception {
	    logger.warn("invoking process end Listener");
	  //  String activityName = delegateExecution.getCurrentActivityName();

   
	    //template.convertAndSend("/topic/notification","Votre demande a été traité avec succés");
	    
	    
	    template.convertAndSend("/topic/notification","le processus avec l'ID "  +delegateExecution.getProcessDefinitionId()+"  est terminé");
	    
	    
	  }

	  
	  @Autowired
	  void register(CamundaEventBus eventBus) {
	    eventBus.register(this);	
	    
	    //CamundaReactor.eventBus().register(new EndProcessListener());
	    
	  }
	  
	  
	  

	  
}
