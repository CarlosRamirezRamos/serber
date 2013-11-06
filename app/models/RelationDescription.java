/*
 * RelationDescription.java
 */


package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.JoinColumn;
import javax.persistence.Basic;

import play.db.ebean.Model;


/**
 * RelationDescription
 */
@Entity
@Table(name = "tbl_relation_descriptions")
public class RelationDescription extends Model {

    @Id
    @Column(name = "relation_description_id")
    @GeneratedValue
    public Integer relation_description_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "relation_id")
    Relation _relation;

    @Basic
    @Column(name = "description", length = 512)
    public String description;

    @Basic
    @Column(name = "type", length = 512)
    public String type;

    @Basic
    @Column(name = "lang", length = 64)
    public String lang;

    @Basic
    @Column(name = "url", length = 512)
    public String url;
}
