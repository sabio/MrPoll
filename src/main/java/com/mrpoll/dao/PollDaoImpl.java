package com.mrpoll.dao;

import com.mrpoll.model.Choice;
import com.mrpoll.model.Poll;
import com.mrpoll.model.Question;
import com.mrpoll.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("pollDao")
public class PollDaoImpl extends AbstractDao<Integer, Poll> implements PollDao {

    @Override
    public void save(Poll poll) {
        persist(poll);
    }

    @Override
    public Poll findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public User getPollOwner(Integer idPoll) {
        Poll poll = findById(idPoll);

        if (poll == null) {
            return null;
        }
        User user = poll.getUserId();
        this.getSession().evict(poll);
        return user;
    }

    @Override
    public void removeQuestion(Integer idQuestion) {
        Query query = this.getSession().createQuery("delete Question where id = :idQuestion");
        query.setParameter("idQuestion", idQuestion);
        query.executeUpdate();
    }

    @Override
    public void removeChoice(Integer idChoice) {
        Query query = this.getSession().createQuery("delete Choice where id = :idChoice");
        query.setParameter("idChoice", idChoice);
        query.executeUpdate();
    }

    @Override
    public void update(Poll poll) {
        Poll pollFromBD = this.findById(poll.getId());

        pollFromBD.getQuestions().forEach((question) -> {
            if (!poll.getQuestions().contains(question)) {
                removeQuestion(question.getId());
            } else {
                question.getChoices().stream()
                        .filter(
                            (choice) -> !poll.containsChoice(choice)
                        ).forEach((choice) -> 
                            removeChoice(choice.getId())
                        );
            }
        });
        this.getSession().evict(pollFromBD);
        super.update(poll);
    }

    @Override
    public void deletePollById(Integer id) {
        Poll poll = getByKey(id);
        delete(poll);
    }

}
