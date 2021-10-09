package com.example.users.services;


import com.example.users.dto.UsernameDto;
import com.example.users.exception.UnexeptedResultException;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final JdbcTemplate jdbcTemplate;

    public String getUsernameById(Long id){
        RowMapper<String> rowMapper = (resultSet, i) -> resultSet.getString("username");
        //language=PostgreSQL
        final var result = jdbcTemplate.query(
                "SELECT username FROM users WHERE id = ?",
                rowMapper,
                id
        );
        if (result.size() > 1)
            throw new UnexeptedResultException();
        else
            return result.get(0);
    }



}
