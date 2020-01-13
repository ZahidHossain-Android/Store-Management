package com.example.storemanagement;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

public class ProductListAdapter extends ArrayAdapter {

    private Activity context;
    private List<ProductData> productDataList;

    public ProductListAdapter(Activity context, List<ProductData> productDataList) {
        super(context, R.layout.custom_list_view, productDataList);
        this.context = context;
        this.productDataList = productDataList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.custom_list_view, null ,true);

        TextView textProductName = (TextView) listViewItem.findViewById(R.id.productname);
        TextView textProductQuantity = (TextView) listViewItem.findViewById(R.id.productquantity);

        ProductData productData = productDataList.get(position);

        textProductName.setText(productData.getProduct_name());
        textProductQuantity.setText(productData.getProduct_quantity());

        return listViewItem;
    }
}

