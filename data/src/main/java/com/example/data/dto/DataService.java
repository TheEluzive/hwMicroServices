package com.example.data.dto;


import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;

@Service
@AllArgsConstructor
public class DataService {
    private final JdbcTemplate jdbcTemplate;

    public void addPayment(Payment payment){
        jdbcTemplate.update(
                "insert into payments(\"senderId\", amount, comment) values (?,?,?)",
                payment.getSenderId(),
                payment.getAmount(),
                payment.getComment()
        );
    }
}
