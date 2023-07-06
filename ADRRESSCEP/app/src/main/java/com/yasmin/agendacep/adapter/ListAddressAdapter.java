package com.yasmin.agendacep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.yasmin.agendacep.R;
import com.yasmin.agendacep.model.Address;

import java.util.ArrayList;
import java.util.List;

public class ListAddressAdapter extends BaseAdapter {

    private final List<Address> addresses = new ArrayList<>();
    private final Context context;

    public ListAddressAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return addresses.size();
    }

    @Override
    public Address getItem(int posicao) {
        return addresses.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return addresses.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCreate = createView(viewGroup);
        Address AddressReturned = addresses.get(posicao);
        bind(viewCreate, AddressReturned);
        return viewCreate;
    }

    private void bind(View view, Address address) {
       TextView cep = view.findViewById(R.id.item_codezip);
       cep.setText(address.getCodeZip().toString());
       TextView logradouro = view.findViewById(R.id.item_logradouro);
       logradouro.setText(address.getLogradouro());

    }

    private View createView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_address, viewGroup, false);
    }

    public void update(List<Address> addresses){
        this.addresses.clear();
        this.addresses.addAll(addresses);
        notifyDataSetChanged();
    }

    public void remove(Address address) {
        addresses.remove(address);
        notifyDataSetChanged();
    }
}
