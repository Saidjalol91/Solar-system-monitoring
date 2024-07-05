package com.example.learning.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;
import java.util.List;

public class AbstractDao {
	protected Log log = LogFactory.getLog(AbstractDao.class);

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	protected void printQueryId(String queryId) {
		if(log.isDebugEnabled()){
			log.debug("\t QueryId  \t:  " + queryId);
		}
	}

	public Object insert(String queryId, Object params){
		printQueryId(queryId);
		return sqlSessionTemplate.insert(queryId, params);
	}

	public Object update(String queryId, Object params){
		printQueryId(queryId);
		return sqlSessionTemplate.update(queryId, params);
	}

	public Object delete(String queryId, Object params){
		printQueryId(queryId);
		return sqlSessionTemplate.delete(queryId, params);
	}

	public Object selectOne(String queryId){
		printQueryId(queryId);
		return sqlSessionTemplate.selectOne(queryId);
	}

	public Object selectOne(String queryId, Object params){
		printQueryId(queryId);
		return sqlSessionTemplate.selectOne(queryId, params);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String queryId){
		printQueryId(queryId);
		return sqlSessionTemplate.selectList(queryId);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String queryId, Object params){
		printQueryId(queryId);
		return sqlSessionTemplate.selectList(queryId, params);
	}
}
