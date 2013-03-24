/*
 * NamePart.java
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
 * NamePart
 */
@Entity
@Table(name = "tbl_name_parts")
public class NamePart extends Model {

    @Id
    @Column(name = "name_part_id")
    @GeneratedValue
    public Integer name_part_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "complex_name_id")
    ComplexName _complexName;

    @Basic
    @Column(name = "value", length = 512)
    public String value;

    @Basic
    @Column(name = "type", length = 512)
    public String type;
}
