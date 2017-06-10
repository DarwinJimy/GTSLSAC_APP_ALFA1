package com.example.witch.gtslsac_app_1;

/**
 * Created by Witchraper on 15/05/2017.
 */
public class Config {
    //URL to our login.php file
    public static final String adaptador_url = "http://www.gtslsac.com/AppMovil/php/adaptador.php";
    public static final String adaptador_listar_clientes_url = "http://www.gtslsac.com/AppMovil/php/adaptadorClientes.php";
    public static final String adaptador_listar_equipos_url = "http://www.gtslsac.com/AppMovil/php/adaptadorEquipos.php";
    public static final String adaptador_listar_operadores_url = "http://www.gtslsac.com/AppMovil/php/adaptadorOperadores.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_OPCION = "Opcion";
    public static final String KEY_EMAIL = "Correo";
    public static final String KEY_PASSWORD = "Password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "login correcto";

    //Keys for Sharedpreferences
    //utilizamos sharedpreferences para mantener la sesion iniciada en el dispositivo
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "Correo";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
