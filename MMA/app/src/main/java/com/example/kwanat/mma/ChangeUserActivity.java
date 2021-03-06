package com.example.kwanat.mma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kwanat.mma.DB.ReadDao.EmployeeReadDao;
import com.example.kwanat.mma.DB.ReadDao.WorkstationReadDao;
import com.example.kwanat.mma.DB.Tables.Employee;
import com.example.kwanat.mma.DB.Tables.Workstation;
import com.example.kwanat.mma.DB.WriteDao.EmployeeWriteDao;

import java.util.List;

public class ChangeUserActivity extends BaseActivity {

    private EditText userNameText;
    private EditText userLastNameText;
    private EditText userPeselText;
    private EditText userAddressText;
    private EditText userPhoneText;

    private Button userSubmit;

    private Spinner worksSpinner;

    Employee user;
    Workstation works;

    private ArrayAdapter<Workstation> adapter;
    private List<Workstation> workstations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_change_user);

        getWorkstations();
        getUser();
        setViews();
        setnewAdapter();
        checkPermissions();
        setButton();


    }

    private void checkPermissions() {
        LoggedUser loguser = LoggedUser.getInstance();
        if((loguser.getUser().getId_workstation()!=1)||(loguser.getUser().getId()==user.getId()))
            worksSpinner.setVisibility(View.GONE);
    }


    private void setViews()
    {
        userNameText = (EditText) findViewById(R.id.changeUserFirstName);
        userLastNameText = (EditText) findViewById(R.id.changeUserLastName);
        userPeselText = (EditText) findViewById(R.id.changeUserPesel);
        userAddressText = (EditText) findViewById(R.id.changeUserAddress);
        userPhoneText = (EditText) findViewById(R.id.changeUserPhoneNumber);
        userSubmit = (Button) findViewById(R.id.changePasswordSubmit);
        worksSpinner = (Spinner) findViewById(R.id.changeUserSpinner);


        userNameText.setText(user.getFirst_name());
        userLastNameText.setText(user.getLast_name());
        if(user.getPesel()!=null)
        userPeselText.setText(user.getPesel());
        if(user.getAddress()!=null)
        userAddressText.setText(user.getAddress());
        if(user.getPhone_number()!=0)
        userPhoneText.setText(String.valueOf(user.getPhone_number()));

    }
    private void getWorkstations()
    {

        Thread get = new Thread(new Runnable() {
            @Override
            public void run() {
                WorkstationReadDao getdao = new WorkstationReadDao();
                workstations=getdao.getAll();

            }
        });

        get.start();
        try {
            get.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void setnewAdapter()
    {
        adapter= new ArrayAdapter<Workstation>(getApplicationContext(),android.R.layout.simple_spinner_item,workstations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        worksSpinner.setAdapter(adapter);
        worksSpinner.setSelection(user.getId_workstation()-1);
        worksSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO: zmiana pozycji w userze
                // Toast.makeText(getApplicationContext(),"wybrano opcje"+position,Toast.LENGTH_SHORT).show();
                //userWorkstation=position+1;
                user.setId_workstation(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void getUser()
    {
        Thread check = new Thread(new Runnable() {
            @Override
            public void run() {

                Intent intent = getIntent();
                int userId= intent.getIntExtra("user_id",0);

                EmployeeReadDao empread = new EmployeeReadDao();
                user=empread.getById(userId);
            }
        });
        check.start();
        try {
            check.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void setButton()
    {
        userSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setFirst_name(userNameText.getText().toString());
                user.setLast_name(userLastNameText.getText().toString());
                user.setPesel(userPeselText.getText().toString());
                user.setAddress(userAddressText.getText().toString());
                if(!userPhoneText.getText().toString().equals(""))
                user.setPhone_number(Integer.parseInt(userPhoneText.getText().toString().replaceAll("[\\D]","")));

                Thread add = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EmployeeWriteDao writedao = new EmployeeWriteDao();
                        writedao.update(user);

                    }
                });

                add.start();
                try {
                    add.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.dataChanged),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (getApplicationContext(), StartActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

}
