package com.omcbappeda.sumsel.utilities;

import java.io.Serializable;
import java.util.List;

public abstract interface Dao <T> {
	T get(Serializable paramSerializable);
	  
	List<T> getAll();
	  
	Serializable save(T paramT);
	  
	void update(T paramT);
	  
	void delete(T paramT);
	  
	void save(List<T> paramList);
	
	void update(List<T> paramList);

}
