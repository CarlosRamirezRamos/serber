/*
 * RelatedInfo.java
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
 * RelatedInfo
 */
@Entity
@Table(name = "tbl_related_info")
public class RelatedInfo extends Model {

    @Id
    @Column(name = "related_info_id")
    @GeneratedValue
    public Integer related_info_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "registry_object_key")
    RegistryObject _registryObject;

    @Basic
    @Column(name = "value", length = 512)
    public String value;
}
