package com.carmona.rafa.eventgo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.Layout;
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
 * {@link ListadoEventosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListadoEventosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListadoEventosFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "id";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String id_Acon;
    private ListView listView;
    private ArrayList<EventoItem> items;
    private EventoAdapter eventosAdapter;


    private OnFragmentInteractionListener mListener;

    public ListadoEventosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListadoEventosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListadoEventosFragment newInstance(String param1, String param2,String id_Acon) {
        ListadoEventosFragment fragment = new ListadoEventosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3,id_Acon);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            id_Acon = getArguments().getString(ARG_PARAM3);
           /* BaseDeDatosSQLiteHelper usdbh =
                    new BaseDeDatosSQLiteHelper(getActivity(), Environment.getExternalStorageDirectory() + "/Eventonline.db", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();


            String[] argsID = new String[]{id_Acon};
            Cursor cursor = db.rawQuery(" SELECT id,nombre FROM evento WHERE id=? ", argsID);

            //Nos aseguramos de que existe al menos un registro
            if (cursor.moveToFirst()) {
                items.removeAll(items);

                //Recorremos el cursor hasta que no haya más registros
           /* LinearLayout layoutPrincipal = (LinearLayout) findViewById(R.id.linearVerAcontecimientos);
            layoutPrincipal.setOrientation(LinearLayout.VERTICAL);*/
               /* do {
//recogemos los datos
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String nombreAcontecimiento = cursor.getString(cursor.getColumnIndex("nombre"));
                    items.add(new EventoItem(id, nombreAcontecimiento));
                } while (cursor.moveToNext());
            }
            EventoAdapter adapter = new EventoAdapter(getActivity(), items);
            setListAdapter(adapter);
            //setListAdapter(new ArrayAdapter<EventoItem>(getActivity(), android.R.layout.simple_list_item_activated_1, items));
        */}
    }
    @Override
    public void onStart(){
        super.onStart();
        if(getFragmentManager().findFragmentById(R.id.fragmentMostrarEvento)!=null){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_listado_eventos, container, false);
        View rootView = inflater.inflate(R.layout.fragment_listado_eventos, container, false);
        listView = (ListView) rootView.findViewById(android.R.id.list);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //int num = getArguments().getInt(ARG_SECTION_NUMBER);

        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        /*items = new ArrayList<EventoItem>();
        items.add(new EventoItem("1", "Primer acontecimiento"));
        items.add(new EventoItem("10", "Segundo acontecimiento"));
        eventosAdapter = new EventoAdapter(getActivity(), layout, items);
        listView.setAdapter(eventosAdapter);*/
        BBDDSQLiteHelper usdbh =
                new BBDDSQLiteHelper(getActivity(), Environment.getExternalStorageDirectory() + "/Eventgo.db", null, 1);
        SQLiteDatabase db = usdbh.getReadableDatabase();


        String[] argsID = new String[]{id_Acon};
        Cursor cursor = db.rawQuery(" SELECT id,nombre FROM evento WHERE id=? ", argsID);

        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            items = new ArrayList<EventoItem>();

            //Recorremos el cursor hasta que no haya más registros
           /* LinearLayout layoutPrincipal = (LinearLayout) findViewById(R.id.linearVerAcontecimientos);
            layoutPrincipal.setOrientation(LinearLayout.VERTICAL);*/
            do {
//recogemos los datos
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String nombreAcontecimiento = cursor.getString(cursor.getColumnIndex("nombre"));
                items.add(new EventoItem(id, nombreAcontecimiento));
            } while (cursor.moveToNext());
        }
        eventosAdapter = new EventoAdapter(getActivity(),layout, items);
        listView.setAdapter(eventosAdapter);
        //setListAdapter(new ArrayAdapter<EventoItem>(getActivity(), android.R.layout.simple_list_item_activated_1, items));

    }

    @Override
    public void onListItemClick(ListView l,View v , int position,long id){
        if(mListener != null){
            mListener.onFragmentInteraction(position,items.get(position).getId());
        }
        getListView().setItemChecked(position,true);
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
        void onFragmentInteraction(int position,String id);
    }
}