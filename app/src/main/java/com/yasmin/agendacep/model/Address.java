package com.yasmin.agendacep.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Address {

    @PrimaryKey(autoGenerate = true)
    private Integer codeZip;
    private int id = 0;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;


    public Address() {

    }

    public Integer getCodeZip() {
        return codeZip;
    }

    public void setCodeZip(Integer codeZip) {
        this.codeZip = codeZip;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String lograduro) {
        this.logradouro = lograduro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @NonNull

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
