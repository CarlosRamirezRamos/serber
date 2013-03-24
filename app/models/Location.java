/*
 * Location.java
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
 * Location
 */
@Entity
@Table(name = "tbl_locations")
public class Location extends Model {

    @Id
    @Column(name = "location_id")
    @GeneratedValue
    public Integer location_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "registry_object_key")
    RegistryObject _registryObject;

    /* Relations */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_location")
    public List<SpatialLocation> spatialLocations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_location")
    public List<AddressLocation> addressLocations;
}
