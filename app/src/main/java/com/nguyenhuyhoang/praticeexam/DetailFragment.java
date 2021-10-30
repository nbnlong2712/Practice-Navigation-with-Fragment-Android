package com.nguyenhuyhoang.praticeexam;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class DetailFragment extends Fragment {
    private Button btnSave, btnDelete, btnBack;
    private EditText edtName, edtBirth, edtRegion;
    private String strName = "", strRegion = "", strBirth = "";
    private Student mStudent;
    private StudentHelper mStudentHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        mStudent = DetailFragmentArgs.fromBundle(getArguments()).getStudent();

        mStudentHelper = new StudentHelper(getActivity());
        mStudentHelper.getWritableDatabase();

        btnBack = view.findViewById(R.id.btn_back);
        btnDelete = view.findViewById(R.id.btn_delete);
        btnSave = view.findViewById(R.id.btn_save);
        edtBirth = view.findViewById(R.id.edt_birth);
        edtName = view.findViewById(R.id.edt_name);
        edtRegion = view.findViewById(R.id.edt_region);


        if (mStudent != null)
        {
            strName = mStudent.getName();
            strBirth = mStudent.getBirth();
            strRegion = mStudent.getRegion();
            edtName.setText(mStudent.getName());
            edtRegion.setText(mStudent.getRegion());
            edtBirth.setText(mStudent.getBirth());
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections navDirections = DetailFragmentDirections.actionDetailFragmentToListFragment();
                Navigation.findNavController(view).navigate(navDirections);
            }
        });

        //--------------------------------------------------------
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                strName = charSequence.toString().trim();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        edtRegion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                strRegion = charSequence.toString().trim();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        edtBirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                strBirth = charSequence.toString().trim();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        //--------------------------------------------------------

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStudent == null)
                {
                    if (strName.isEmpty() || strRegion.isEmpty() || strBirth.isEmpty())
                    {
                        Toast.makeText(getActivity(), "Please fill enough", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        boolean result = mStudentHelper.insertData(strName, strRegion, strBirth);
                        if (result)
                        {
                            Toast.makeText(getActivity(), "Save success", Toast.LENGTH_SHORT).show();
                            edtName.setText("");
                            edtBirth.setText("");
                            edtRegion.setText("");
                        }
                        else Toast.makeText(getActivity(), "Save failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if (strName.isEmpty() || strRegion.isEmpty() || strBirth.isEmpty())
                    {
                        Toast.makeText(getActivity(), "Please fill enough", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mStudentHelper.update(mStudent.getId(), strName, strRegion, strBirth);
                        Toast.makeText(getActivity(), "Update success", Toast.LENGTH_SHORT).show();
                        NavDirections action = DetailFragmentDirections.actionDetailFragmentToListFragment();
                        Navigation.findNavController(view).navigate(action);
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStudent != null)
                {
                    boolean result = mStudentHelper.deleteStudent(mStudent.getId());
                    if (result) {
                        Toast.makeText(getActivity(), "Delete success", Toast.LENGTH_SHORT).show();
                        NavDirections action = DetailFragmentDirections.actionDetailFragmentToListFragment();
                        Navigation.findNavController(view).navigate(action);
                    }
                    else {
                        Toast.makeText(getActivity(), "Delete failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Student is empty so cannot delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
