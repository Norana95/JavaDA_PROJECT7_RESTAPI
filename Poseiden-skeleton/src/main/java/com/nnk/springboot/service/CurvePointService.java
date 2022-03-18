package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    public void createCurvePoint(CurvePoint curvePoint){
        curvePointRepository.save(curvePoint);
    }
    public void findCurvePointById(Integer id){
        curvePointRepository.findById(id);
    }
    public void updateCurvePoint(CurvePoint curvePoint){
        curvePoint.setCurveId(curvePoint.getCurveId());
        curvePoint.setAsOfDate(curvePoint.getAsOfDate());
        curvePoint.setTerm(curvePoint.getTerm());
        curvePoint.setValue(curvePoint.getValue());
        curvePoint.setCreationDate(curvePoint.getCreationDate());
    }
    public void deleteCurvePoint(CurvePoint curvePoint){
        curvePointRepository.delete(curvePoint);
    }

}
