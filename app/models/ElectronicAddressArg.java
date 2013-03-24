/*
 * ElectronicAddressArg.java
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
 * ElectronicAddressArg
 */
@Entity
@Table(name = "tbl_electronic_address_args")
public class ElectronicAddressArg extends Model {

    @Id
    @Column(name = "electronic_address_arg_id")
    @GeneratedValue
    public Integer electronic_address_arg_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "electronic_address_id")
    ElectronicAddress _electronicAddress;

    @Basic
    @Column(name = "name", length = 512)
    public String name;

    @Basic
    @Column(name = "required")
    public boolean required;

    @Basic
    @Column(name = "type", length = 512)
    public String type;

    @Basic
    @Column(name = "use_", length = 512)
    public String use;
}
