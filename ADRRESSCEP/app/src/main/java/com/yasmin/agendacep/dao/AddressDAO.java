package com.yasmin.agendacep.dao;

import androidx.annotation.Nullable;

import com.yasmin.agendacep.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressDAO {

    private final static List<Address> addresses = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void save(Address address) {
        address.setId(contadorDeIds);
        addresses.add(address);
        UpdateIds();
    }

    private void UpdateIds() {
        contadorDeIds++;
    }

    public void edit(Address address) {
        Address addressfound = searchAddressId(address);
        if (addressfound != null) {
            int positionAddress = addresses.indexOf(addressfound);
            addresses.set(positionAddress, address);
        }
    }

    @Nullable
    private Address searchAddressId(Address address) {
        for (Address a :
                addresses) {
            if (a.getId() == address.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Address> todos() {
        return new ArrayList<>(addresses);
    }

    public void remove(Address address) {
        Address addressdevolution = searchAddressId(address);
        if(addressdevolution != null){
            addresses.remove(addressdevolution);
        }
    }
}
