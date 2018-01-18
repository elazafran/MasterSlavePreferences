package com.example.usuario.masterslavepreferences;


import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import java.util.List;

/**
 * Created by usuario on 15/01/18.
 */

public class SettingsActivity extends PreferenceActivity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * este un metodo que se dispara el solo, cunado se le pasan las cabeceras
     * se le pasa una lista con las cabeceras y nosotros la vamos a utilizar
     * @TargetApi(Build.VERSION_CODES.HONEYCOMB)
     * por que la cabeceras solo trabajan a partir de esto
     * @param target
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.settings_header,target);

    }

    /**
     * Nosotros somos los encargados de decidir si es multi
     * y lo hacemos midiendo la pantalla y usamos el metoso displaymetrics
     * va ser consultado automaticamente
     *
     * @return de vuelve un boleano
     */

    @Override
    public boolean onIsMultiPane() {

        //Nos fijamos en que la relacion de aspecto del ancho y alto de la pantalla
        DisplayMetrics metrics = new DisplayMetrics();

        //este metodo nos da acceso al gestor de ventana
        //getDefaultDisplay para sacar la pantalla por defecto
        //getMetrics(metrics) utiliza un objeto de DisplayMetrics para que lo rellene
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //vemos la documentacion de moodle
        //vamos a coger un factor de relacion para saber si es
        //una pantalla de 320 de alto y unos 1024 de ancho
        //320/1024= 0.31 y si es mayor haremos una cosa y si es mejor otra
        //densityDpi es la que da nos da un valor que hay en la tabla
        //widthPixels es una propiedad

        return ((float) metrics.densityDpi)/((float)metrics.widthPixels)<=0.31;

    }




    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        // se trae todos los metodos de mi fragment relacionados, attaca al xml
        // hemos tenidos que hacerlos declarativamente
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            String settings = getArguments().getString("settings");

            if (settings.equals("generales")){
                addPreferencesFromResource(R.xml.settings_gen);
            }else if (settings.equals("twitter")){
                    addPreferencesFromResource(R.xml.settings_twitter);
            }
        }


        @Override
        public void onResume() {
            super.onResume();
            //le pasamos la referencia de la propiaReferencia

            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            //le pasamos la referencia de la propiaReferencia
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }


        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("numArticulos")){
                Preference preference = findPreference(key);
                preference.setSummary(sharedPreferences.getString(key,""));
            }
        }
    }

    /**
     * como nuestra clase es estatica vamos a hacer reflexion,
     * si esto no lo ponemos dará error pq no estamos haciendo programación
     * segura
     *
     * @param fragmentName
     * @return
     */
    @Override
    protected boolean isValidFragment(String fragmentName) {
        //Hacemos reflexion para no equivocarnos y consultamos la clase que damos
        //como validad y lo comparamos lo uqe nos venga del metoso
        return SettingsFragment.class.getName().equals(fragmentName);
    }


}
