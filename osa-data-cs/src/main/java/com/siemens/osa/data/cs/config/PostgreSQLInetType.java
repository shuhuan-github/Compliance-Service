package com.siemens.osa.data.cs.config;

import com.vladmihalcea.hibernate.type.ImmutableType;
import com.vladmihalcea.hibernate.util.ReflectionUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class PostgreSQLInetType
        extends ImmutableType<Inet> {

    /**
     * postgre sqlInet type.
     */
    public PostgreSQLInetType() {
        super(Inet.class);
    }

    /**
     * sqlType.
     *
     * @return {}
     */
    @Override
    public int[] sqlTypes() {
        return new int[]{
                Types.OTHER
        };
    }

    /**
     * 得到.
     *
     * @param resultSet           resultSet
     * @param strings             string
     * @param contractImplementor contractImplementer
     * @param o                   o
     * @return {@link Inet}
     * @throws SQLException sqlexception Exception
     */
    @Override
    protected Inet get(ResultSet resultSet, String[] strings,
                       SharedSessionContractImplementor contractImplementor, Object o) throws SQLException {
        String ip = resultSet.getString(strings[0]);
        return (ip != null) ? new Inet(ip) : null;
    }

    /**
     * set.
     *
     * @param preparedStatement                preparedStatementInAdvance
     * @param inet                             inet
     * @param i                                indexPosition
     * @param sharedSessionContractImplementor sharingSessionContractImplementers
     * @throws SQLException sqlexception Exception
     */
    @Override
    protected void set(PreparedStatement preparedStatement, Inet inet, int i,
                       SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (inet == null) {
            preparedStatement.setNull(i, Types.OTHER);
        } else {
            Object holder = ReflectionUtils.newInstance(
                    "org.postgresql.util.PGobject"
            );
            ReflectionUtils.invokeSetter(
                    holder,
                    "type",
                    "inet"
            );
            ReflectionUtils.invokeSetter(
                    holder,
                    "value",
                    inet.getAddress()
            );
            preparedStatement.setObject(i, holder);
        }
    }
}
