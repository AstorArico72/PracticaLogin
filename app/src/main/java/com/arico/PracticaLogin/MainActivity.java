package com.arico.PracticaLogin;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.arico.PracticaLogin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding Binder;
    private MainViewModel ViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.Binder = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(Binder.getRoot());
        ViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class); //Ésto crea una instancia del ViewModel.
        ConseguirPermisos();

        Binder.SubmitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ésto consigue el texto de los EditText de la vista.
                String NombreUsuario = Binder.CampoUsuario.getText().toString();
                String ClaveUsuario = Binder.CampoContraseA.getText().toString();

                ViewModel.Ingresar(NombreUsuario, ClaveUsuario);
            }
        });

        Binder.NuevaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NombreUsuario = Binder.CampoUsuario.getText().toString();
                String ClaveUsuario = Binder.CampoContraseA.getText().toString();
                ViewModel.NuevaCuenta(NombreUsuario, ClaveUsuario);
            }
        });
    }
    private void ConseguirPermisos() {
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }
    }
}