/**
 * 
 */
package com.demo.util;

/**
 * @author yqx
 *
 */
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.solder.logging.Logger;

@Named
@ConversationScoped
@Stateful
public class ConversationManager {

    @Inject
    Conversation conversation;

    @Inject
    private Logger log;
    
    public void beginPreRenderView(ComponentSystemEvent e) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        String currentViewId = facesContext.getViewRoot()
                .getViewId();
        //log.infov("The tab id is {0}",tabId);
        if (conversation.isTransient()) {
            conversation.begin();
            log.debugv("New Conversation. viewId={0}", currentViewId);
        } else {
            log.debugv("Use old Conversation. viewId={0}", currentViewId);
        }
    }
    
    public void endPreRenderView(ComponentSystemEvent e) {
        String currentViewId = FacesContext.getCurrentInstance().getViewRoot()
                .getViewId();
        if (!conversation.isTransient()) {
            conversation.end();
            log.debugv("End Conversation. viewId={0}", currentViewId);
        } else {
            log.debugv("No Conversation need to be end. viewId={0}", currentViewId);
        }
    }
    
    public void endConversation(){
        if (!conversation.isTransient()) {
            conversation.end();
        } 
    }
    
    public Conversation getConversation()
    {
        return conversation;
    }
    
    public Conversation getConversation(String cid){
        return conversation;
    }
    
    
}
