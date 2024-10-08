package com.arico.PracticaPreferenciasCompartidas;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.arico.PracticaPreferenciasCompartidas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding Binder;
    private MainViewModel ViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.Binder = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(Binder.getRoot());

        ViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class); //Ésto crea una instancia del ViewModel.

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
}