package com.siemens.osa.module.cs.service.getes;

import com.siemens.osa.data.es.entity.ESInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGetESService {

    /**
     * get all es.
     *
     * @return {@link List}&lt;{@link ESInfo}&gt;
     */
    List<ESInfo> getES();

}
