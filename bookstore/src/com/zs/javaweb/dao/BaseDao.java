package com.zs.javaweb.dao;

import java.util.List;

public class BaseDao<T> implements Dao<T> {

	@Override
	public long insert(String sql, Object... args) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(String sql, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T query(String sql, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> queryForList(String sql, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> V getSingleVal(String sql, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batch(String sql, Object[]... params) {
		// TODO Auto-generated method stub
		
	}

}
