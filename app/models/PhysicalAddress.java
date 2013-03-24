/*
 * PhysicalAddress.java
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
 * PhysicalAddress
 */
@Entity
@Table(name = "tbl_physical_addresses")
public class PhysicalAddress extends Model {

    @Id
    @Column(name = "physical_address_id")
    @GeneratedValue
    public Integer physical_address_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "address_location_id")
    AddressLocation _addressLocation;

    @Basic
    @Column(name = "type", length = 512)
    public String type;

    @Basic
    @Column(name = "lang", length = 64)
    public String lang;

    /* Relations */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_physicalAddress")
    public List<AddressPart> addressParts;
}
