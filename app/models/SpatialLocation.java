/*
 * SpatialLocation.java
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
 * RelationDescription
 */
@Entity
@Table(name = "tbl_spatial_locations")
public class SpatialLocation extends Model {

    @Id
    @Column(name = "spatial_location_id")
    @GeneratedValue
    public Integer spatial_location_id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "location_id")
    Location _location;

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
