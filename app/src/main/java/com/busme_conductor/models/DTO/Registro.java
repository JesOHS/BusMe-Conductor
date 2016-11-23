package com.busme_conductor.models.DTO;

import android.os.Parcel;
import android.os.Parcelable;

public class Registro implements Parcelable {
    String id_unidad;
    String clave;

    public Registro(String id_unidad, String clave) {
        this.id_unidad = id_unidad;
        this.clave = clave;
    }

    protected Registro(Parcel in) {
        id_unidad = in.readString();
        clave = in.readString();
    }

    public static final Creator<Registro> CREATOR = new Creator<Registro>() {
        @Override
        public Registro createFromParcel(Parcel in) {
            return new Registro(in);
        }

        @Override
        public Registro[] newArray(int size) {
            return new Registro[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_unidad);
        dest.writeString(clave);
    }

    public String getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(String id_unidad) {
        this.id_unidad = id_unidad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


}
