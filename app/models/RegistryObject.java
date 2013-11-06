/*
 * RegistryObject.java
 */


package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Basic;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import play.db.ebean.Model;


/**
 * RegistryObject
 */
@Entity
@Table(name = "tbl_registry_objects")
public class RegistryObject extends Model {
    public static Finder<Long, RegistryObject> find =
        new Finder<Long, RegistryObject>(Long.class, RegistryObject.class);

    @Id
    @Column(name = "registry_object_key")
    @GeneratedValue
    public Long registry_object_key;

    @Basic
    @Column(name = "registry_object_class", length = 512)
    public String registry_object_class;

    @Basic
    @Column(name = "type", length = 32)
    public String type;

    @Basic
    @Column(name = "originating_source", length = 512)
    public String originating_source;

    @Basic
    @Column(name = "originating_source_type", length = 512)
    public String originating_source_type;

    /* Relations */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_registryObject")
    public List<Identifier> identifiers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_registryObject")
    public List<Subject> subjects;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_registryObject")
    public List<Description> descriptions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_registryObject")
    public List<ComplexName> complexNames;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_registryObject")
    public List<AccessPolicy> accessPolicies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_registryObject")
    public List<RelatedInfo> relatedInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_registryObject")
    public List<Location> locations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_registryObject")
    public List<Relation> relations;  // "related by"

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "_relatedRegistryObject")
    List<Relation> _relations;  // "related to"

    /* Methods */

    public static List<RegistryObject> all() {
        return RegistryObject.find.all();
    }

    public static List<RegistryObject> list(String search, String page) {
        return RegistryObject.find
        .where()
        .like("complexNames.nameParts.value", "%" + search + "%")
        .findList();
    }

    public static RegistryObject retrive(Long key) {
        return RegistryObject.find.byId(key);
    }

    public static void save(RegistryObject registryObject) {
        registryObject.save();
    }

    public static void update(Long key) {
        RegistryObject.find.ref(key).update();
    }

    public static void delete(Long key) {
        RegistryObject.find.ref(key).delete();
    }
}
