package com.example.eisne.licoresql.utilidades;

/**
 * Created by eisne on 26/1/2018.
 */

public class UtilidadesLicores {
    public static final String TABLA_LICORES = "licor";
    public static final String IDUSUARIO = "id_usuario";
    public static final String MATERIAL = "material";
    public static final String DESCRIPCION_BREVE = "descripcion_breve";
    public static final String UNIDADES = "unidades";



    public static final String CREAR_TABLA_LICOR = "CREATE TABLE " + TABLA_LICORES +
            "(" +  MATERIAL + " INTEGER PRIMARY KEY, " +
            DESCRIPCION_BREVE + " TEXT, " +
            UNIDADES + " INTEGER, " + IDUSUARIO + " INTEGER)";
}
