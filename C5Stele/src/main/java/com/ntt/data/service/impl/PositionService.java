package com.ntt.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.data.dao.IPositionDao;
import com.ntt.data.model.Position;
import com.ntt.data.service.IPositionService;

@Service(value = "positionService")
public class PositionService implements IPositionService {

	@Autowired
	private IPositionDao positionDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Position> getAll() {
		return positionDao.getAll();
	}

}
