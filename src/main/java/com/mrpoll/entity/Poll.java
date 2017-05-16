/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrpoll.entity;

import com.mrpoll.entity.Choice;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "poll")
public class Poll implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @Size(min = 2, max = 45)
    @Column(name = "uuid", unique=true)
    private String uuid;
    
    @Column(name = "expiration_datetime")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDatetime;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "poll_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    @Fetch (FetchMode.SELECT) 
    private List<Question> questions;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Poll() {
    }

    public Poll(Integer id) {
        this.id = id;
    }

    public Poll(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getExpirationDatetime() {
        return expirationDatetime;
    }

    public void setExpirationDatetime(Date expirationDatetime) {
        this.expirationDatetime = expirationDatetime;
    }
    
    
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poll)) {
            return false;
        }
        Poll other = (Poll) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mrpoll.model.Poll[ id=" + id + " ]";
    }
    
    public boolean isNew(){
        return this.getId() == null;
    }

    public boolean containsChoice(Choice choiceToFind) {
        if(this.getQuestions() == null) return false;
        
        return getQuestions().stream().anyMatch(
                (question) -> (question.getChoices() != null && question.getChoices().contains(choiceToFind))
        );
    }
    
}
