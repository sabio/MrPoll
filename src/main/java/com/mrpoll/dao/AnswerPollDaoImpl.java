package com.mrpoll.dao;

import com.mrpoll.controller.ChoiceResult;
import com.mrpoll.controller.FormChoiceResponse;
import com.mrpoll.controller.FormResponse;
import com.mrpoll.controller.QuestionResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    
    
    @Override
    public List<QuestionResult> getResults(Integer idPoll) {
        String query =  
                "SELECT " +
                "   cr.question_id, q.question_text, cr.choice_id, c.choice_text, count(*) count " +
                "FROM " +
                "   response r " +
                "INNER JOIN choice_response cr on r.id = cr.response_id " +
                "INNER JOIN question q on q.id = cr.question_id " +
                "INNER JOIN choice c on c.id = cr.choice_id " +
                "WHERE " +
                "   r.poll_id = ? " +
                "GROUP BY " +
                "   cr.question_id, q.question_text, cr.choice_id, c.choice_text";
        
        List<QuestionResult> questionResult = new ArrayList<>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> dbResults = jdbcTemplate.queryForList(query, new Object[]{idPoll});
        
        dbResults.forEach(row -> {
            if(questionResult.isEmpty() || !questionResult.get(questionResult.size()-1).equals(row.get("question_id"))  ){
                QuestionResult qr = new QuestionResult();
                qr.setId((Integer)row.get("question_id"));
                qr.setQuestionText(query);
            }
            
            ChoiceResult cr = new ChoiceResult();
            cr.setId((Integer)row.get("choice_id"));
            cr.setChoiceText((String)row.get("choice_text"));
            cr.setCount((Integer)row.get("count"));
            
            questionResult.get(questionResult.size()-1).addChoiceResult(cr);
        });
        
        return questionResult;
    }
    
    
    
}