package com.springboot.vaadinproject.location;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, String> {
    @Query("select c from Location c "+
        "where lower(c.name) like lower(concat('%', :searchTerm, '%'))")
    List<Location> search(@Param("searchTerm") String searchTerm);

}