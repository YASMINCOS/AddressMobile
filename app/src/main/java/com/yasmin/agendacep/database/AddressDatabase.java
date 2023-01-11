package com.yasmin.agendacep.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yasmin.agendacep.database.dao.RoomAddressDAO;
import com.yasmin.agendacep.model.Address;

@Database(entities = {Address.class}, version = 1, exportSchema = false)

public abstract class AddressDatabase extends RoomDatabase {

    public static  final  String NOME_BD = "address.db";

    public abstract RoomAddressDAO getRoomAddressDao();

    public static  AddressDatabase getInstance(Context context){

        return Room.databaseBuilder(context
                        , AddressDatabase.class, NOME_BD)
                .allowMainThreadQueries()
                .build();
    }
}
