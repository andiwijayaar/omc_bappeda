package com.omcbappeda.sumsel.utilities;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

public abstract class AbstractHibernate4Dao<T> {
	protected Class<T> entityClass = (Class) A(getClass()).getActualTypeArguments()[0];
	
	public T get(Serializable paramSerializable) {
		return (T) getSession().get(entityClass, paramSerializable);
	}

	public Serializable save(T paramT) {
		Serializable serializable = getSession().save(paramT);
		SessionUtils.commitSession();
		return serializable;
	}

	public void update(T paramT) {
		getSession().update(paramT);
	}

	public void delete(T paramT) {
		getSession().delete(paramT);
	}

	public List<T> getAll() {
		return getSession().createCriteria(entityClass).list();
	}

	public void save(List<T> paramList) {
		int i = 0;
		Iterator localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			Object localObject = localIterator.next();
			getSession().save(localObject);
			i++;
			if (i % 50 == 0) {
				flushSession();
			}
		}
		flushSession();
	}

	public void update(List<T> paramList) {
		int i = 0;
		Iterator localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			Object localObject = localIterator.next();
			getSession().update(localObject);
			i++;
			if (i % 50 == 0) {
				flushSession();
			}
		}
		flushSession();
	}

	protected void flushSession() {
		getSession().flush();
		getSession().clear();
	}

	protected Session getSession() {
		return SessionUtils.getSession();
	}

	private ParameterizedType A(Class paramClass) {
		if ((paramClass.getGenericSuperclass() instanceof ParameterizedType)) {
			return (ParameterizedType) paramClass.getGenericSuperclass();
		}
		return A(paramClass.getSuperclass());
	}
}
