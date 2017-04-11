package com.omcbappeda.sumsel.service;

import java.util.List;

import com.omcbappeda.sumsel.model.Bucket;

public interface BucketService {

	void save(Bucket bucket);
	void save(List<Bucket> buckets);
	void update(Bucket bucket);
	void update(List<Bucket> buckets);
	void delete(Bucket bucket);
	List<Bucket> getAll();
	Bucket get(String id);
}
