package com.siemens.osa.data.cs.config;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet implements Serializable {

    /** inet address . */
    private final String address;

    /**
     * inet.
     *
     * @param addresses inet address
     */
    public Inet(final String addresses) {
        this.address = addresses;
    }

    /**
     * get inetAddress.
     *
     * @return {@link String}
     */
    public String getAddress() {
        return address;
    }

    /**
     * ifCompareEqual.
     *
     * @param o o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Inet inet = (Inet) o;

        return address != null ? address.equals(inet.address) : inet.address == null;
    }

    /**
     * hashCode.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }

    /**
     * Convert to inet address.
     *
     * @return {@link InetAddress}
     */
    public InetAddress toInetAddress() {
        try {
            String host = address.replaceAll("\\/.*$", "");

            return InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
        }
    }

}
