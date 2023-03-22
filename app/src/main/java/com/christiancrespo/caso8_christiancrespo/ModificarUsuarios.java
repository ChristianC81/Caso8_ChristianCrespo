package com.christiancrespo.caso8_christiancrespo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ModificarUsuarios extends AppCompatActivity {
    EditText nombre, email, genero, status, id;
    Button editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuarios);

        //Asignacion de valores a las variables editext
        id=findViewById(R.id.txtId);
        nombre = findViewById(R.id.txtNombre);
        email = findViewById(R.id.txtEmail);
        genero = findViewById(R.id.txtGenero);
        status = findViewById(R.id.txtStatus);
        editar = findViewById(R.id.btnEditar);
        editarUsuario();
    }

    private void makePutRequestWithToken(String url, JSONObject requestBody, final String token) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Manejar la respuesta de la petición
                        Toast.makeText(getApplicationContext(), "Usuario "+id.getText().toString()+" modificado exitosamente", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Manejar el error de la petición
                Toast.makeText(getApplicationContext(), "Usuario no modificado", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Campos incorrectos o duplicados", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Agregar la petición a la cola de Volley
        Volley.newRequestQueue(this).add(request);
    }
    private void enviarUsuarioModif(){
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("name", nombre.getText().toString());
            requestBody.put("email", email.getText().toString());
            requestBody.put("gender", genero.getText().toString());
            requestBody.put("status", status.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "https://gorest.co.in/public/v2/users/"+id.getText().toString()+"/";
        String token = "7b9c3094a2805a4baf1143b33d5128e687adaec1e83af5b47b0a63bfbcb8a775";

        makePutRequestWithToken(url, requestBody, token);
    }

    public void editarUsuario(){

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarUsuarioModif();
                finish();
                Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMain);
            }
        });
    }
}