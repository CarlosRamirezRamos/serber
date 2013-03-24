/*
 * ComplexName.java
 */


package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.JoinColumn;
import javax.persistence.Basic;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import play.db.ebean.Model;


/**
 * ComplexName
 */
@Entity
@Table(name = "tbl_complex_names")
public class ComplexName extends Model {

    @Id
    @Column(name = "complex_name_id")
    @GeneratedValue
    public Integer complex_name_id;

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

    @Basic
    @Column(name = "lang", length = 64)
    public String lang;

    /* Relations */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_complexName")
    public List<NamePart> nameParts;
}
