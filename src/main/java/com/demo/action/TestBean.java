/**
 * 
 */
package com.demo.action;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.solder.logging.Logger;

import com.demo.domain.Many;
import com.demo.util.BDP;

/**
 * @author yqx
 *
 */
@Named
@ConversationScoped
public class TestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@BDP
	EntityManager em;
	
	@Inject
	Logger log;
	
	private Many many;
	
	@PostConstruct
	public void afterCreate(){
		many = em.find(Many.class, "1");
		log.infov("------{0}", many);
	}

	public Many getMany() {
		return many;
	}

	public void setMany(Many many) {
		this.many = many;
	}

	
	
	
}
