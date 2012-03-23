/**
 * 
 */
package com.demo.util;

/**
 * @author yqx
 *
 */
import java.util.Iterator;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.solder.logging.Logger;

@Named
@ConversationScoped
@Stateful
public class ConversationManager {

	@Inject
	Conversation conversation;

	@Inject
	private Logger log;

	private boolean invalid;
	
	private String message ="Opration Successfully!";
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	public void postValidateEvent(ComponentSystemEvent e) {
		invalid = isInvalid();
		log.infov("------{0}-----",invalid);
	}

	public boolean isInvalid() {
		Iterator<FacesMessage> iterator = FacesContext.getCurrentInstance()
				.getMessages();
		while (iterator.hasNext()) {
			FacesMessage facesMessage = iterator.next();
			if (FacesMessage.SEVERITY_ERROR.equals(facesMessage.getSeverity())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 增加提示消息
	 * @param message
	 */
	public void addInfoMessage(String message){
		if(StringUtils.isEmpty(message))
			return;
		addMessage(message,FacesMessage.SEVERITY_INFO);
	}
	
	public void clearMessage() {
		if (FacesContext.getCurrentInstance() != null) {
			Iterator<FacesMessage> iter = FacesContext.getCurrentInstance()
					.getMessageList().iterator();
			while (iter.hasNext()) {
				FacesMessage fmg = iter.next();
				fmg.setDetail("");
				fmg.setSummary("");
			}
		}
	}

	/**
	 * 增加错误消息
	 * 
	 * @param message
	 */
	public void addErrorMessage(String message) {
		if (StringUtils.isEmpty(message))
			return;
		addMessage(message, FacesMessage.SEVERITY_ERROR);
	}

	/**
	 * 根据业务添加提示信息 此方法是添加消息的公共方法 此处将validator中及其他的消息清空，然后加入新的消息
	 * 
	 * @param message
	 * @param severity
	 */
	public void addMessage(String message, Severity severity) {
		if (FacesContext.getCurrentInstance() != null) {
			// 先清空validator中的消息提醒
//			Iterator<FacesMessage> iter = FacesContext.getCurrentInstance()
//					.getMessageList().iterator();
//			while (iter.hasNext()) {
//				FacesMessage fmg = iter.next();
//				fmg.setDetail("");
//				fmg.setSummary("");
				// 添加自己的业务消息
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(severity, message, message));
//			}
		}
	}

	public void beginPreRenderView(ComponentSystemEvent e) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		String currentViewId = facesContext.getViewRoot().getViewId();
		// log.infov("The tab id is {0}",tabId);
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
			log.debugv("No Conversation need to be end. viewId={0}",
					currentViewId);
		}
	}

	public void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public Conversation getConversation() {
		return conversation;
	}

	public Conversation getConversation(String cid) {
		return conversation;
	}

}
