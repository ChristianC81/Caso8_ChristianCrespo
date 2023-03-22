package com.christiancrespo.caso8_christiancrespo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.christiancrespo.caso8_christiancrespo.models.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter arrayAdpater;
    ArrayList<String> datos= new ArrayList<>();
    Button btnModifUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnModifUsu= findViewById(R.id.btnModificarAct);
        listView=findViewById(R.id.Datos);
        arrayAdpater= new ArrayAdapter(this, android.R.layout.simple_spinner_item, datos);
        listView.setAdapter(arrayAdpater);
        irModificarUsuarios();
        obtenerDatosUsuarios();
        //makeApiCall();
    }

    private void manejarJsonUsuarios(JSONArray jsonArray){

        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = null;
            Users usuarios = new Users();
            try {
                jsonObject = jsonArray.getJSONObject(i);
                usuarios.setIdU(jsonObject.getString("id"));
                usuarios.setNombreU(jsonObject.getString("name"));
                usuarios.setEmailU(jsonObject.getString("email"));
                usuarios.setGeneroU(jsonObject.getString("gender"));
                usuarios.setStatusU(jsonObject.getString("status"));

                arrayAdpater.add("ID: "+usuarios.getIdU()+"\n NOMBRE: "+usuarios.getNombreU()+"\n EMAIL: "+usuarios.getEmailU()+"\n GENERO: "+usuarios.getGeneroU()+"\n STATUS: "+usuarios.getStatusU());
            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }
        arrayAdpater.notifyDataSetChanged();
    }
    private void obtenerDatosUsuarios() {
        // URL de la API
        String url = "https://gorest.co.in/public/v2/users/";

        // Token de autorización
        String token = "f7302288a2224548488972afe8c5b48c3422fcc290a8cc8fbba58b09128801fd";

        // Crea un objeto de tipo JsonArrayRequest para hacer la petición GET con Volley
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    // Si la petición es exitosa, se ejecuta este código
                    try {
                        // Aquí puedes procesar el JSONArray con los datos de la API
                        JSONArray jsonArray = response;
                        manejarJsonUsuarios(jsonArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Si hay un error en la petición, se ejecuta este código
                    error.printStackTrace();
                }
        ) {
            // Se sobrescribe el método getHeaders para agregar el token de autorización en la cabecera de la petición
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        // Agrega la petición al RequestQueue de Volley para que se ejecute
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void irModificarUsuarios(){
        btnModifUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentModif = new Intent(getApplicationContext(), ModificarUsuarios.class);
                startActivity(intentModif);
                finish();
            }
        });
    }
}