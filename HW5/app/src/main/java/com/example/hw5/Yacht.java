package com.example.hw5;

import android.content.Context;

public class Yacht {

    public final static double EXPENSIVE = 30000.0;
    public final static double AFFORDABLE = 25000.0;
    public final static double SET_PRICE = 50000.0;

    private String name;
    private int cabins;
    private int heads;

    public Yacht(String name, int cabins, int heads) {
        this.name = name;
        setCabins(cabins);
        setHeads(heads);
    }

    public Yacht(Context cxt) {
        this.name = cxt.getString(R.string.default_name);
        this.cabins = Integer.parseInt(cxt.getString(R.string.default_cabins));
        this.heads = Integer.parseInt(cxt.getString(R.string.default_heads));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCabins() {
        return cabins;
    }

    public void setCabins(int cabins) {
        if(cabins < 0)
            this.cabins = 0;
        else
            this.cabins = cabins;
    }

    public int getHeads() {
        return heads;
    }

    public void setHeads(int heads) {
        if(heads < 0)
            heads = 0;
        else
            this.heads = heads;
    }

    public double determinePrice(int years) {
        if(years < 0)
            years = 0;
        double price, newPrice;
        int rooms = this.cabins + this.heads;
        if(rooms > 5) {
            newPrice = EXPENSIVE * rooms;
        } else if(rooms > 2) {
            newPrice = AFFORDABLE * rooms;
        } else {
            newPrice = SET_PRICE;
        }
        price = newPrice * (1.0 - (0.05 * years));

        if(price <= 0.1) {
            price = newPrice * .05 / 2.0;
        }

        return price;
    }
}
