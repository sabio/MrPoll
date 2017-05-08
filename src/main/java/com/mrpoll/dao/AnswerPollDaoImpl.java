package com.mrpoll.dao;

import com.mrpoll.model.ChoiceResult;
import com.mrpoll.model.FormChoiceResponse;
import com.mrpoll.model.FormResponse;
import com.mrpoll.controller.QuestionResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    
    
    @Override
    public List<QuestionResult> getResults(Integer idPoll) {
        StringBuilder query = new StringBuilder()  
                .append("SELECT ")
                .append("   c.question_id, q.question_text, c.id choice_id, c.choice_text,")
                .append("   ( ")
                .append("	SELECT COUNT(*) ")
                .append("       FROM choice_response ")
                .append("       WHERE ")
                .append("		question_id = c.question_id ")
                .append("        and choice_id = c.id ")
                .append("   ) count ")
                .append("FROM ")
                .append("   choice c ")
                .append("INNER JOIN ")
                .append("   question q on q.id = c.question_id ")
                .append("INNER JOIN ")
                .append("   poll p on p.id = q.poll_id ")
                .append("WHERE ")
                .append("   p.id = ? ")
                .append("GROUP BY ")
                .append("   c.question_id, q.question_text, c.id, c.choice_text ")
                .append("order by ")
                .append("   c.id ");
        
        List<QuestionResult> questionResultList = new ArrayList<>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> dbResults = jdbcTemplate.queryForList(query.toString(), new Object[]{idPoll});
        
        dbResults.forEach(row -> {
            QuestionResult qr;
            if(questionResultList.isEmpty() || !questionResultList.get(questionResultList.size()-1).getId().equals(row.get("question_id"))  ){
                qr = new QuestionResult();
                qr.setId((Integer)row.get("question_id"));
                qr.setQuestionText((String)row.get("question_text"));
                questionResultList.add(qr);
            }
            else{
                qr = questionResultList.get(questionResultList.size()-1);
            }
            
            ChoiceResult cr = new ChoiceResult();
            cr.setId((Integer)row.get("choice_id"));
            cr.setChoiceText((String)row.get("choice_text"));
            cr.setCount((Long)row.get("count"));
            
            qr.addChoiceResult(cr);
        });
        
        
        //Calculate percentage
        questionResultList.forEach(qr -> {
            Long total = qr.getChoiceResults().stream().mapToLong(ChoiceResult::getCount).sum();
            
            qr.getChoiceResults().forEach(cr -> {
                cr.setPercent(total == 0 ? 0d : (100.0 * (new Double(cr.getCount()) / new Double(total))));
            });

        });
        
        return questionResultList;
    }
    
    
    
}