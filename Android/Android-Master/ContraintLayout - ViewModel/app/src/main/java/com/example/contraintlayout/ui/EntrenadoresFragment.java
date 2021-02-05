package com.example.contraintlayout.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.contraintlayout.viewmodel.NuevoEntrenadorDialogViewModel;
import com.example.contraintlayout.db.entity2.EntrenadorEntity;
import com.example.contraintlayout.ui.Adapters.EntrenadorViewAdapter;
import com.example.contraintlayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class EntrenadoresFragment extends Fragment {
    RecyclerView recyclerView;
    private EntrenadorViewAdapter adapterEntrenadores;
    private List<EntrenadorEntity> entrenadorEntityList;
    //private EntrenadorInteractionListener mListener;    //TODO:Interfaz para conectar adapter y gymActivity
    private NuevoEntrenadorDialogViewModel nuevoEntrenadorDialogViewModel;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;       //TODO: ESTE PARAMETRO SIRVE PARA LA CANTIDAD DE COLUMNAS

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EntrenadoresFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EntrenadoresFragment newInstance(int columnCount) {
        EntrenadoresFragment fragment = new EntrenadoresFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        //TODO: Indicamos que el Fragment tiene un menú de opcones propio
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            /*RecyclerView */recyclerView = (RecyclerView) view;
            if(view.getId() == R.id.listPotrait){
            //if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {

                DisplayMetrics displayMetrics = container.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numeroColumnas = (int) (dpWidth / 250); //Cada columna ocupara 100 pixeles poe pulgada
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numeroColumnas, StaggeredGridLayoutManager.VERTICAL));//Similar a grid pero cambia el tamaño


                //recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));  //Utilizamos un gridLayout(todos del mismo tamaño)

            }

            /*String foto1 = "https://www.precor.com/es-xl/blog/wp-content/uploads/sites/6/2017/02/Precor_In-Club_DPL-DSL-Coach_C1_1311-600x400.jpg";
            String foto2 = "https://www.telemundodeportes.com/sites/default/files/styles/article_607x340/public/2015/03/09/ricardo-ferretti.jpg?itok=-e-1HNVi";
            String foto3 = "https://atalayar.com/sites/default/files/styles/foto_/public/noticias/Atalayar_Albert%20Celades%2C%20exentrenador%20Valencia%20CF.jpg?itok=-LTQJvN1";
            String foto4 = "https://www.clikisalud.net/wp-content/uploads/2019/08/consejos-top-entrenadores-personales-hacer-ejercicio.jpg";
            String Lorem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

            entrenadorEntityList = new ArrayList<>();
            for(int i = 1; i<500;i++){
                entrenadorEntityList.add(new EntrenadorEntity(1663518,"Jose Luis García",2.5,Lorem,1,foto1));
                entrenadorEntityList.add(new EntrenadorEntity(1542639,"Pablo Manuel Lopez",1,"San Nicolas",0,foto2));
                entrenadorEntityList.add(new EntrenadorEntity(1201523,"Jose Juan Gallegos",4.6,"San Pedro",1,foto3));
                entrenadorEntityList.add(new EntrenadorEntity(4521632,"Guadalupe Rodriguez",1.2,"Juarez",0,foto4));
            }*/

            entrenadorEntityList = new ArrayList<>();

            adapterEntrenadores = new EntrenadorViewAdapter(getActivity(), entrenadorEntityList);
            recyclerView.setAdapter(adapterEntrenadores);
            //recyclerView.setAdapter(new MyGymRecyclerViewAdapter(DummyContent.ITEMS));
            
            lanzarViewModel();
        }
        return view;
    }

    //TODO: Este metodo de encarga de hacer el refresh
    private void lanzarViewModel() {
        nuevoEntrenadorDialogViewModel = ViewModelProviders.of(getActivity())
                .get(NuevoEntrenadorDialogViewModel.class);
        nuevoEntrenadorDialogViewModel.getAllEntrenadores().observe(getActivity(), new Observer<List<EntrenadorEntity>>() {
            @Override
            public void onChanged(List<EntrenadorEntity> entrenadorEntities) {
                adapterEntrenadores.setNuevosEntrenadores(entrenadorEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options_menu_entrenador_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_add_trainer:
                mostrarDialogoNuevoEntrenador();
                break;
            default:
                break;
        }
        return true;
    }

    private void mostrarDialogoNuevoEntrenador() {
        FragmentManager fm = getActivity().getSupportFragmentManager(); //instanciamos fragment manager
        NuevoEntrenadorDialogFragment dialogNuevoEntren = new NuevoEntrenadorDialogFragment();
        dialogNuevoEntren.show(fm,"NuevoEntrendorDialogFragment"); //la etiqueta se almacena en el fragmento que estamos instanciando
    }
}