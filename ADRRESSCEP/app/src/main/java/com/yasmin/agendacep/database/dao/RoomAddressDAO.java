package com.yasmin.agendacep.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yasmin.agendacep.model.Address;

import java.util.List;

@Dao
public interface RoomAddressDAO {

    @Insert
    void save(Address address);

    @Query("SELECT * FROM Address")
    List<Address> all();

    @Delete
    void remove(Address address);

    @Update
    void edit(Address address);
}
