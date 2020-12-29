package com.example.contraintlayout.ui;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.contraintlayout.R;
import com.example.contraintlayout.db.entity.EntrenadorEntity;
import com.example.contraintlayout.viewmodel.NuevoEntrenadorDialogViewModel;

public class NuevoEntrenadorDialogFragment extends DialogFragment {
    private View view;
    private EditText etFotoUrl,etNombre, etExperiencia, etMatricula;
    private Spinner spSucursal;
    private RadioGroup rgEstatus;
    //RadioButton

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Nuevo entrenador");
        builder.setMessage("Introduzca los datos del nuevo entrenador")
                .setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String FotoURL = etFotoUrl.getText().toString();
                        String Nombre = etNombre.getText().toString();
                        String Sucursal = spSucursal.getSelectedItem().toString();
                        double Experiencia = Double.parseDouble(etExperiencia.getText().toString());
                        int Estatus = 0;
                        switch (rgEstatus.getCheckedRadioButtonId()){
                            case R.id.rbActivo: Estatus = 1; break;
                            case R.id.rbInactivo: Estatus = 0; break;
                        }
                        int Matricula = Integer.parseInt(etMatricula.getText().toString());

                        //TODO:Comunicar al viewmodel el nuevo dato
                        NuevoEntrenadorDialogViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NuevoEntrenadorDialogViewModel.class); //unica instancia
                        mViewModel.insertEntrenador(new EntrenadorEntity(FotoURL,Nombre,Sucursal,Experiencia,Estatus,Matricula));
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        //Intanciamos y referenciamos View
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.nuevo_entrenador_dialog_fragment,null);

        etFotoUrl = view.findViewById(R.id.etFotoUrl);
        etNombre = view.findViewById(R.id.etNombre);
        etExperiencia = view.findViewById(R.id.etExperiencia);
        etMatricula = view.findViewById(R.id.etMatricula);

        spSucursal = view.findViewById(R.id.spSucursal);

        rgEstatus = view.findViewById(R.id.rgEstatus);

        String[] Sucursales = new String[]{"3 caminos", "Linda Vista", "Santa María"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,Sucursales);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSucursal.setAdapter(adapter);

        //enlazamos view
        builder.setView(view);

        return builder.create();
//        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(NuevoEntrenadorDialogViewModel.class);
        // TODO: Use the ViewModel
    }


    //**********************************************************************************************
    /*CODIGO DEFAULT GENERADO AL CREAR FRAGMENT*/
    //private NuevoEntrenadorDialogViewModel mViewModel;

    public static NuevoEntrenadorDialogFragment newInstance() {
        return new NuevoEntrenadorDialogFragment();
    }

//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.nuevo_entrenador_dialog_fragment, container, false);
//    }



}