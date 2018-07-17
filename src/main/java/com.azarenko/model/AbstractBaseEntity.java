package com.azarenko.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.Generated;

import javax.persistence.*;


@MappedSuperclass
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public abstract class AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public AbstractBaseEntity() {
    }

    public AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /* @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (!(o instanceof AbstractBaseEntity)) return false;

         AbstractBaseEntity that = (AbstractBaseEntity) o;

         return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
     }

     @Override
     public int hashCode() {
         return getId() != null ? getId().hashCode() : 0;
     }
 */
    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), id);
    }
}
