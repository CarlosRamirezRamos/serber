/*
 * AddressPart.java
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
 * AddressPart
 */
@Entity
@Table(name = "tbl_address_parts")
public class AddressPart extends Model {

    @Id
    @Column(name = "address_part_id")
    @GeneratedValue
    public Integer address_part_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "physical_address_id")
    PhysicalAddress _physicalAddress;

    @Basic
    @Column(name = "value", length = 512)
    public String value;

    @Basic
    @Column(name = "type", length = 512)
    public String type;

    @Basic
    @Column(name = "lang", length = 64)
    public String lang;
}
