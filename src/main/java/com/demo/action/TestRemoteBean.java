/**
 * 
 */
package com.demo.action;

import java.io.Serializable;

import javax.inject.Named;

import org.jboss.seam.remoting.annotations.WebRemote;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @author yqx
 *
 */
@Named
public class TestRemoteBean implements Serializable {
	
	@WebRemote
	public String getTreeData(String rootId) throws JSONException{
		JSONArray result = new JSONArray();
		JSONObject node1 = new JSONObject();
		node1.accumulate("name", "node1");
		JSONArray node1Children = new JSONArray();
		JSONObject node1Child1 = new JSONObject();
		node1Child1.accumulate("name", "node1Child1");
		node1Children.add(node1Child1);
		
		node1.accumulate("children", node1Children);
		result.add(node1);
		
		JSONObject node2 = new JSONObject();
		node2.accumulate("name", "node2");
		JSONArray node2Children = new JSONArray();
		JSONObject node2Child2 = new JSONObject();
		node2Child2.accumulate("name", "node2Child2");
		node2Children.add(node2Child2);
		
		node2.accumulate("children", node2Children);
		result.add(node2);
		return result.toString();
		
	}
	
	public static void main(String[] args) throws JSONException{
		TestRemoteBean bean = new TestRemoteBean();
		System.out.println(bean.getTreeData(null));
	}
}
