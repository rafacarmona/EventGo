package com.carmona.rafa.eventgo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link listadoEventosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link listadoEventosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listadoEventosFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "id";
    private ArrayList<EventoItem> items = new ArrayList<EventoItem>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mparam3;
    private OnFragmentInteractionListener mListener;

    public listadoEventosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment listadoEventosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static listadoEventosFragment newInstance(String param1, String param2) {
        listadoEventosFragment fragment = new listadoEventosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mparam3 = getArguments().getString(ARG_PARAM3);
        }

        //leer base de datos
        BBDDSQLiteHelper usdbh =
                new BBDDSQLiteHelper(getActivity(), Environment.getExternalStorageDirectory()+"/Eventgo.db", null, 1);
        //instancia la db.
        SQLiteDatabase db = usdbh.getReadableDatabase();

        String[] argsID = new String[] {mparam3};
        Cursor cursor = db.rawQuery(" SELECT id, nombre FROM evento WHERE id_acontecimiento=? ", argsID);

        //Nos aseguramos de que existe al menos un registro

        if (cursor.moveToFirst()) {

            items.removeAll(items);

            //Recorremos el cursor hasta que no haya más registros
            do{
                //recogemos los datos y los asignamos a una variable.
                String id =cursor.getString(cursor.getColumnIndex("id"));
                String nombreEvento = cursor.getString(cursor.getColumnIndex("nombre"));

                items.add(new EventoItem(id,nombreEvento));

            }while(cursor.moveToNext());
        }


        //String provincias[] = {"Avila", "Burgos", "León"};
        setListAdapter(new ArrayAdapter<EventoItem>(getActivity(), android.R.layout.simple_list_item_activated_1, items));

    }

    public void onStart() {
        super.onStart();

        if(getFragmentManager().findFragmentById(R.id.fragmentMostrarEvento) != null){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listado_eventos, container, false);
    }

    @Override public void onListItemClick(ListView l, View v, int position, long id){
        if(mListener != null){
            mListener.onFragmentInteraction(position);
        }

        getListView().setItemChecked(position, true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position);
    }
}
