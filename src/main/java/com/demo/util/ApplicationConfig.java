/**
 * 
 */
package com.demo.util;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.solder.core.ExtensionManaged;

/**
 * @author yqx
 *
 */
public class ApplicationConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Produces
    @ExtensionManaged
    @ConversationScoped
    @BDP
    @PersistenceUnit(unitName="test")
    EntityManagerFactory conversationEMF;
}
