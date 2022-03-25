package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    public void saveCurvePoint(CurvePoint curvePoint){
        curvePointRepository.save(curvePoint);
    }

    public Optional<CurvePoint> findCurvePointById(Integer id){
        return curvePointRepository.findById(id);
    }

    public void updateCurvePoint(CurvePoint curvePoint){
        curvePoint.setCurveId(curvePoint.getCurveId());
        curvePoint.setTerm(curvePoint.getTerm());
        curvePoint.setValue(curvePoint.getValue());
        curvePointRepository.save(curvePoint);
    }
    public void deleteCurvePoint(CurvePoint curvePoint){
        curvePointRepository.delete(curvePoint);
    }
    public List<CurvePoint> findAllCurvePoint(){
        return curvePointRepository.findAll();
    }

}
