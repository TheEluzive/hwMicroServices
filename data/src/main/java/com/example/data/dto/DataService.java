package com.example.data.dto;


import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public List<Payment> getPayments() {

        return jdbcTemplate.query(
                "select * from payments",
                (resultSet, i) -> new Payment(
                        resultSet.getLong("id"),
                        resultSet.getLong("senderId"),
                        resultSet.getLong("amount"),
                        resultSet.getString("comment")
                        )
        );
    }
}
