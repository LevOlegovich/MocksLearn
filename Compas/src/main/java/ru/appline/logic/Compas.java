package ru.appline.logic;

import java.util.List;

public class Compas {

    private List<SideOfCompas> sideOfCompas;


    public Compas(List<SideOfCompas> sideOfCompas) {
        this.sideOfCompas = sideOfCompas;
    }


    public String getSideOfCompas(int degree) {
        for (SideOfCompas sideOfCompas : this.sideOfCompas) {
            if (degree < 0) {
                return "{\n\"Side\": \"Введите положительное число от 0 до 359!\"\n}";
            }

            //Для диапазона, н-р "North":338-22
            if (sideOfCompas.getStartDegree() > sideOfCompas.getEndDegree() &&
                    (degree >= sideOfCompas.getStartDegree() && degree <= 359 || degree <= sideOfCompas.getEndDegree())) {
                return "{\n\"Side\": " + sideOfCompas.getSide() + "\n}";
            }

            if (degree >= sideOfCompas.getStartDegree() && degree <= sideOfCompas.getEndDegree())
                return "{\n\"Side\": " + sideOfCompas.getSide() + "\n}";
        }
        return "{\n\"Side\": \"Диапазон не найден в компасе!Введите положительное число от 0 до 359!\"\n}";
    }

}
