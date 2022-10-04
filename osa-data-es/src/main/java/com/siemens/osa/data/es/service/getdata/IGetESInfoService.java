package com.siemens.osa.data.es.service.getdata;

import com.siemens.osa.data.es.entity.ESInfo;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface IGetESInfoService {

    /**
     * get all config result with es index.
     *
     * @return {@link List}&lt;{@link ESInfo}&gt;
     * @throws IOException ioexception
     */
    List<ESInfo> getAllConfig() throws IOException;

}
