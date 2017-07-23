package com.example.idstudent.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TextInputFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TextInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextInputFragment extends DialogFragment {

    private OnFragmentInteractionListener mListener;

    public TextInputFragment() {
        // Required empty public constructor
    }
        char action;
    int mult = 1;
    // TODO: Rename and change types and number of parameters
    public static TextInputFragment newInstance(char a) {
        TextInputFragment fragment = new TextInputFragment();
        Bundle args = new Bundle();
        args.putChar("action",a);
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            action = getArguments().getChar("action");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater i = getActivity().getLayoutInflater();

        View view = i.inflate(R.layout.dialogdeltasubs,null);
        TextView tvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
        final EditText etDeltaSubs = (EditText) view.findViewById(R.id.etDeltaSubs);

        if (action=='-'){
            tvQuestion.setText("How many subs should be subtracted?");
            mult = -1;
        }else if(action=='+'){
            mult = 1;
            tvQuestion.setText("How many subs should be added?");
        }
        String sButton = "";
        if (action=='-'){
             sButton = "Subtract";
        }else if(action=='+'){
             sButton = "Add";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setPositiveButton(sButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onFragmentInteraction(mult*Integer.parseInt(etDeltaSubs.getText().toString()));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setView(view);
        return builder.create();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
        void onFragmentInteraction(int amount);
    }
}
