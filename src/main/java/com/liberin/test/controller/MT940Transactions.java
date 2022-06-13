package com.liberin.test.controller;

import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "mt940logbox")
public class MT940Transactions {
    
    @Id
    @Column(name = "filename",columnDefinition = "VARCHAR2(50)")
    private String filename;

    @Lob
    @Column(name = "mt940output", columnDefinition = "CLOB")
    private Clob data;

    
}
