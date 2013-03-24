/*
 * ElectronicAddress.java
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
 * ElectronicAddress
 */
@Entity
@Table(name = "tbl_electronic_addresses")
public class ElectronicAddress extends Model {

    @Id
    @Column(name = "electronic_address_id")
    @GeneratedValue
    public Integer electronic_address_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "address_location_id")
    AddressLocation _addressLocation;

    @Basic
    @Column(name = "value", length = 512)
    public String value;
    
    @Basic
    @Column(name = "type", length = 512)
    public String type;

    /* Relations */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_electronicAddress")
    public List<ElectronicAddressArg> electronicAddressArgs;
}
