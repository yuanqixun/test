/**
 * 
 */
package com.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.demo.domain.Many;
import com.demo.domain.One;

/**
 * @author yqx
 * 
 */
@RunWith(Arquillian.class)
public class JpaPersistenceTest {
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackages(true, "com.demo")
				.addAsManifestResource("test-persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	EntityManager em;

	@Inject
	UserTransaction utx;

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}

	@Before
	public void preparePersistenceTest() throws Exception {
		clearDatabase();
		insertData();
		startTransaction();
	}

	private void insertData() throws Exception {
		utx.begin();
		em.joinTransaction();
		One a = new One();
		a.setDescription("a-desc");
		Many b = new Many();
		b.setDescription("b-desc");
		b.setOneId(a);
		List<Many> manyCollection = new ArrayList<Many>();
		a.setManyCollection(manyCollection);
		a.getManyCollection().add(b);
		Many b2 = new Many();
		b2.setDescription("b2-desc");
		b2.setOneId(a);
		a.getManyCollection().add(b2);
//		em.persist(a);
		em.merge(a);
		em.flush();
		utx.commit();
	}

	private void clearDatabase() throws Exception {
		utx.begin();
		em.joinTransaction();
		
		// em.createQuery("delete from Many").executeUpdate();
		utx.commit();
	}

	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}

	@Test
	public void testOne2ManyQuery() throws Exception {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<One> query = builder.createQuery(One.class);
		Root<One> one = query.from(One.class);
		query.select(one);
		List<One> result = em.createQuery(query).getResultList();
		Assert.assertNotNull(result);
		One a = result.get(0);
		Collection<Many> manySet = a.getManyCollection();
		Assert.assertFalse(manySet.isEmpty());
		for (Many many : manySet) {
			System.out.println(many);
		}
	}

	@Test
	public void testMany2OneQuery() throws Exception {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Many> query = builder.createQuery(Many.class);
		Root<Many> many = query.from(Many.class);
		query.select(many);
		List<Many> result = em.createQuery(query).getResultList();
		Assert.assertFalse(result.isEmpty());
		One a = result.get(0).getOneId();
		Assert.assertNotNull(a);
	}
	
	@Test
	public void testOne2ManyDelete() throws Exception{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<One> query = builder.createQuery(One.class);
		Root<One> one = query.from(One.class);
		query.select(one);
		List<One> result = em.createQuery(query).getResultList();
		Assert.assertNotNull(result);
		One a = result.get(0);
		em.remove(a);
		result = em.createQuery(query).getResultList();
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void testMany2OneDelete() throws Exception{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Many> query = builder.createQuery(Many.class);
		Root<Many> many = query.from(Many.class);
		query.select(many);
		List<Many> result = em.createQuery(query).getResultList();
		Assert.assertFalse(result.isEmpty());
		for (Many item : result) {
			em.remove(item);
		}
		em.flush();
		result = em.createQuery(query).getResultList();
		Assert.assertTrue(result.isEmpty());
		List<One> oneResult = em.createQuery("select o from One o ").getResultList();
		Assert.assertFalse(oneResult.isEmpty());
	}
	
}
