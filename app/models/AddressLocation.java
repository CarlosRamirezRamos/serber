/*
 * AddressLocation.java
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
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import play.db.ebean.Model;


/**
 * AddressLocation
 */
@Entity
@Table(name = "tbl_address_locations")
public class AddressLocation extends Model {

    @Id
    @Column(name = "address_location_id")
    @GeneratedValue
    public Integer address_location_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "location_id")
    Location _location;

    /* Relations */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_addressLocation")
    public List<ElectronicAddress> electronicAddresses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_addressLocation")
    public List<PhysicalAddress> physicalAddresses;
}
