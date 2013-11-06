/*
 * Identifier.java
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
 * Identifier
 */
@Entity
@Table(name = "tbl_identifiers")
public class Identifier extends Model {

    @Id
    @Column(name = "identifier_id")
    @GeneratedValue
    public Integer identifier_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "registry_object_key")
    RegistryObject _registryObject;

    @Basic
    @Column(name = "value", length = 512)
    public String value;

    @Basic
    @Column(name = "type", length = 512)
    public String type;
}
