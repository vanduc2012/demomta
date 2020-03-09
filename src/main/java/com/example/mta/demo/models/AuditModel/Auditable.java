//package com.example.mta.demo.models.AuditModel;
//
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import java.util.Date;
//
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public abstract class Auditable<T> {
//    @CreatedBy
//    protected T createdBy;
//    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    protected Date creationDate;
//    @LastModifiedBy
//    protected T lastModifiedBy;
//    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    protected Date lastModifiedDate;
//
//}
