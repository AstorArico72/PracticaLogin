package com.arico.PracticaPreferenciasCompartidas;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.arico.PracticaPreferenciasCompartidas.databinding.ActivityDatosUsuariosBinding;

public class DatosUsuarios extends AppCompatActivity {
    private ActivityDatosUsuariosBinding Binder;
    private DatosViewModel ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Binder = ActivityDatosUsuariosBinding.inflate(getLayoutInflater());
        setContentView(Binder.getRoot());
        this.ViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DatosViewModel.class);
        ViewModel.ConseguirUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Binder.CampoNombre.setText(usuario.getNombre());
                Binder.CampoClave.setText(usuario.getClave());
                Binder.CampoDni.setText(usuario.getDni());
            }
        });
        ViewModel.LeerDatos();
        Binder.ActualizarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NuevoNombre = Binder.CampoNombre.getText().toString();
                String NuevaClave = Binder.CampoClave.getText().toString();
                String NuevoDni = Binder.CampoDni.getText().toString();
                ViewModel.CambiarUsuario (NuevoNombre, NuevaClave, NuevoDni);
            }
        });
    }
}