package com.barust.emc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.barust.emc.entity.data.JsqmData;
import com.barust.emc.entity.MainEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jsqm_info")
public class JsqmInfo extends MainEntity implements Serializable {

    @JsonProperty("information_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "dd.MM.yyyy HH:mm:ss")
    private Timestamp information_Date;

    @JsonProperty("JSQMData")
    @OneToMany(mappedBy = "information", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<JsqmData> jsqmData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsqmInfo that = (JsqmInfo) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
