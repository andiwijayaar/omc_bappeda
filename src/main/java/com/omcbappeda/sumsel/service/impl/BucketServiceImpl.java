package com.omcbappeda.sumsel.service.impl;

import java.util.List;

import com.omcbappeda.sumsel.dao.BucketDAO;
import com.omcbappeda.sumsel.model.Bucket;
import com.omcbappeda.sumsel.service.BucketService;

public class BucketServiceImpl implements BucketService {

	private BucketDAO bucketDAO;
	
	public void setBucketDao(BucketDAO bucketDAO) {
		this.bucketDAO = bucketDAO;
	}

	@Override
	public void save(Bucket bucket) {
		this.bucketDAO.save(bucket);
	}

	@Override
	public Bucket get(String id) {
		return this.bucketDAO.get(id);
	}

	@Override
	public void save(List<Bucket> buckets) {
		this.bucketDAO.save(buckets);
	}

	@Override
	public void update(Bucket bucket) {
		this.bucketDAO.update(bucket);
	}

	@Override
	public void update(List<Bucket> buckets) {
		this.bucketDAO.update(buckets);
		
	}

	@Override
	public void delete(Bucket bucket) {
		this.bucketDAO.delete(bucket);
	}

	@Override
	public List<Bucket> getAll() {
		return this.bucketDAO.getAll();
	}
	
}
