package com.ntt.data.dao.impl;

import org.springframework.stereotype.Repository;

import com.ntt.data.dao.IPositionDao;
import com.ntt.data.model.Position;

@Repository
public class PositionDao extends GenericDao<Position> implements IPositionDao {

}
