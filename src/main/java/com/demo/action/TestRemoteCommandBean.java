/**
 * 
 */
package com.demo.action;



import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.solder.logging.Logger;

/**
 * @author yqx
 *
 */
@ConversationScoped
@Named
public class TestRemoteCommandBean implements Serializable{
	@Inject
	Logger log;
	
	public void remoteCommandListener(ActionEvent event){
	    FacesContext context = FacesContext.getCurrentInstance();
	    Map map = context.getExternalContext().getRequestParameterMap();
	    String param1 = (String) map.get("param");
	    log.infov("param:{0}",param1);
	}
}
