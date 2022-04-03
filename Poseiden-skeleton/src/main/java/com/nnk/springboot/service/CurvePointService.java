package com.nnk.springboot.service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    Logger logger = LoggerFactory.getLogger(CurvePointService.class);

    public void saveCurvePoint(CurvePoint curvePoint){
        curvePointRepository.save(curvePoint);
        logger.info("curvePoint saved !");
    }

    public Optional<CurvePoint> findCurvePointById(Integer id){
        logger.info("inside method findCurvePointById in CurvePointService");
        return curvePointRepository.findById(id);
    }

    public void updateCurvePoint(CurvePoint curvePoint){
        curvePoint.setCurveId(curvePoint.getCurveId());
        curvePoint.setTerm(curvePoint.getTerm());
        curvePoint.setValue(curvePoint.getValue());
        curvePointRepository.save(curvePoint);
        logger.info("curvePoint updated !");
    }
    public void deleteCurvePoint(CurvePoint curvePoint){
        curvePointRepository.delete(curvePoint);
        logger.info("curvePoint deleted !");
    }
    public List<CurvePoint> findAllCurvePoint(){
        logger.info("inside method findAllCurvePoint in CurvePointService");
        return curvePointRepository.findAll();
    }

}
