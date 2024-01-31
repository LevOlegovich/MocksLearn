package ru.appline.logic;

import java.util.ArrayList;
import java.util.List;

public class Compas {

    private List<SideOfCompas> sideOfCompas;


    public Compas(List<SideOfCompas> sideOfCompas) {
        this.sideOfCompas = sideOfCompas;
    }


    public String getSideOfCompas(int degree) {
        for (SideOfCompas sideOfCompas : this.sideOfCompas)
            if (degree <= sideOfCompas.getEndDegree()  && degree >= sideOfCompas.getStartDegree() )
                return sideOfCompas.getSide();
        return "{\"message\":\"Диапазон не найден в компасе!\"}";
    }

}
