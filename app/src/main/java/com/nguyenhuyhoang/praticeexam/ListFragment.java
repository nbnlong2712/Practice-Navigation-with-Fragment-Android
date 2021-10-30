package com.nguyenhuyhoang.praticeexam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private AppCompatButton btnAddStudent;
    private RecyclerView rcvStudent;
    private StudentAdapter mStudentAdapter;
    private List<Student> mStudentList;

    private StudentHelper mStudentHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mStudentHelper = new StudentHelper(getActivity());
        mStudentHelper.getWritableDatabase();

        mStudentList = new ArrayList<>();
        mStudentAdapter = new StudentAdapter();
        mStudentList = mStudentHelper.getAllStudentFromDB();

        btnAddStudent = view.findViewById(R.id.btn_add_student);
        rcvStudent = view.findViewById(R.id.rcv_student);

        rcvStudent.setHasFixedSize(true);
        mStudentAdapter.setData(mStudentList);
        rcvStudent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvStudent.setAdapter(mStudentAdapter);
        mStudentAdapter.setOnStudentClick(new OnStudentClick() {
            @Override
            public void studentClick(Student student) {
                NavDirections action = ListFragmentDirections.actionListFragmentToDetailFragment2(student);
                Navigation.findNavController(view).navigate(action);
            }
        });

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = null;
                NavDirections action = ListFragmentDirections.actionListFragmentToDetailFragment2(student);
                Navigation.findNavController(view).navigate(action);
            }
        });
        return view;
    }
}
