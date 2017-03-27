package com.mrpoll.dao;

import com.mrpoll.controller.FormChoiceResponse;
import com.mrpoll.controller.FormResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository("answerPollDao")
public class AnswerPollDaoImpl implements AnswerPollDao{
    
    @Autowired
    private DataSource dataSource;

    @Override
    public void insertResponse(FormResponse formResponse) {
        String insertResponseSql = "INSERT INTO response (poll_id, response_datetime) VALUES (?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection con) -> {
            PreparedStatement statement = con.prepareStatement(insertResponseSql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, formResponse.getIdPoll());
            statement.setTimestamp(2, new java.sql.Timestamp(formResponse.getResponseDate().getTime()));
            return statement;
        }, holder);
        
        Integer responsePrimaryKey = holder.getKey().intValue();
        formResponse.getFormChoiceResponses().forEach((c)-> insertChoiceResponse(responsePrimaryKey,c));
    }
    
    private void insertChoiceResponse(Integer responseId, FormChoiceResponse formChoiceResponse) {
        String insertChoiceResponseSql = "INSERT INTO choice_response (response_id, question_id, choice_id) VALUES (?,?,?)";
        new JdbcTemplate(dataSource).update(insertChoiceResponseSql, responseId, formChoiceResponse.getIdQuestion(), formChoiceResponse.getIdChoice());
    }
    
}
