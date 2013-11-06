/*
 * Relation.java
 */


package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Basic;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import play.db.ebean.Model;


/**
 * Relation
 */
@Entity
@Table(name = "tbl_relations")
public class Relation extends Model {

    @Id
    @Column(name = "relation_id")
    @GeneratedValue
    public Integer relation_id;

    @Basic
    @Column(name = "registry_object_key")
    public Long registry_object_key;

    @ManyToOne
    @JoinColumn(name = "registry_object_key")
    RegistryObject _registryObject;

    @Basic
    @Column(name = "related_registry_object_key")
    public Long related_registry_object_key;

    @ManyToOne
    @JoinColumn(name = "related_registry_object_key")
    RegistryObject _relatedRegistryObject;

    /* Relations */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_relation")
    public List<RelationDescription> relationDescriptions;
}
