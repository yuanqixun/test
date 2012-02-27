/**
 * 
 */
package com.demo.util;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author yqx
 *
 */
@ApplicationScoped
public class ApplicationConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Produces
	@PersistenceContext(unitName="test")
	EntityManager em;
}
