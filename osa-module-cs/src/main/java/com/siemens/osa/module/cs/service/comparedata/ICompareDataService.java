package com.siemens.osa.module.cs.service.comparedata;

import java.net.UnknownHostException;

public interface ICompareDataService {

    /**
     * compare data between cs and es.
     *
     * @throws UnknownHostException 未知主机异常
     */
    void compareData() throws UnknownHostException;

}
