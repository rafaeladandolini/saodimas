package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	T findById(Integer id, Connection conn)throws SQLException;
		
    List<T> findAll(Connection conn)throws SQLException;

    void save(T entity, Connection conn) throws SQLException;

    void update(T entity, Connection conn)throws SQLException;

    void delete(T entity, Connection conn)throws SQLException;
    
   
 
}
