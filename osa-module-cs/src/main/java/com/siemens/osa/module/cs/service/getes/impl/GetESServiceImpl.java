package com.siemens.osa.module.cs.service.getes.impl;

import com.siemens.osa.data.es.service.getdata.impl.GetESInfoServiceImpl;
import com.siemens.osa.data.es.entity.ESInfo;
import com.siemens.osa.module.cs.service.getes.IGetESService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class GetESServiceImpl implements IGetESService {

    /** the es module of GetESInfoServiceImpl object. */
    private GetESInfoServiceImpl esInfoGetService;

    /**
     * constructor.
     *
     * @param esInfoGetServices esInfoGetService
     */
    public GetESServiceImpl(final GetESInfoServiceImpl esInfoGetServices) {
        this.esInfoGetService = esInfoGetServices;
    }

    /**
     * get all es result.
     *
     * @return {@link List}&lt;{@link ESInfo}&gt;
     */
    @Override
    public List<ESInfo> getES() {
        try {
            return esInfoGetService.getAllConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

}
