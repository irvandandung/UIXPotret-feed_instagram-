package com.example.user.pelapor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.pelapor.api.AppController;
import com.example.user.pelapor.api.serverAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;

@SuppressWarnings("unchecked")
public class LoginActivity extends AppCompatActivity {

    AutoCompleteTextView nis, password;
    ProgressDialog pd;
    private Context context;
    Button masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;

        pd=new ProgressDialog(context);
        nis=(AutoCompleteTextView)findViewById(R.id.nisn);
        password=(AutoCompleteTextView)findViewById(R.id.Password);
        masuk=(Button)findViewById(R.id.masuk);

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        final String enis = nis.getText().toString().trim();
        final String epassword = password.getText().toString().trim();



        if (TextUtils.isEmpty(enis)) {
            nis.setError("Please enter your username");
            nis.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(epassword)) {
            password.setError("Please enter your password");
            password.requestFocus();
            return;
        }

        StringRequest senddata = new StringRequest(Request.Method.POST, serverAPI.URL_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        try {
                            JSONArray json = new JSONArray(response);
                            // Mengambil variable status pada response
                            if(){
                                // Jika Login Sukses Maka pindah ke activity lain.
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                // Jika Login Gagal Maka mengeluarkan Toast dengan message.
                                Toast.makeText(getApplicationContext(), "Username & Password Salah", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nism", enis);
                map.put("password", epassword);

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(senddata);
    }

    private void hideDialog() {
        if (!pd.isShowing())
            pd.dismiss();
    }

    private void showDialog() {
        if (!pd.isShowing())
        pd.show();
    }
}


