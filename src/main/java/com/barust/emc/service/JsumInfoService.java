package com.barust.emc.service;

import com.barust.emc.ResponseType;
import com.barust.emc.repository.JsumInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.barust.emc.entity.JsumInfo;
import com.barust.emc.entity.data.JsumData;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsumInfoService {
    @Autowired
    JsumInfoRepository jsumInfoRepository;

    public List<ResponseType> save(JsumInfo jsumInfo){
        List<JsumData> jsumDataList = jsumInfo.getJsumData();
        for (JsumData ls: jsumDataList) {
            ls.setInformation(jsumInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsumInfo rt = jsumInfoRepository.save(jsumInfo);
        for (JsumData jsumData : rt.getJsumData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsumData.getJshshir());
            response.setID_ORG(jsumData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsumData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsumInfo> readAll() {
        return jsumInfoRepository.findAll();
    }
    public List<JsumInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsumInfo> pagedResult = jsumInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsumInfo>();
        }
    }
    public List<JsumInfo> findByDate(Date sana) {
        return jsumInfoRepository.findByDate(sana);
    }
    public List<JsumData> findByJSHSHIR(String jshshir) {
//        JsumData JsumData = jxInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsumInfo JsumInfo = JsumData.getInformation();

        return jsumInfoRepository.findByJSHSHIR(jshshir);
    }
}
