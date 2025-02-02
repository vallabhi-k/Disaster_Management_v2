package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReliefCentreRegisterActivity extends AppCompatActivity {

    //Button registerButton;


    EditText email,password,affectedPeople;
    EditText landmark,phone,aadhar,policeThana,confirmPassword;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg,mRef;
    Button registerButton;
    Double latitude;
    Double longitude;
    int count=0;


    //Button registerBtn;

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    Context context = this;
    TextView relief_centre_loc;
    private FusedLocationProviderClient mFusedLocationClient;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relief_centre_register);

      //  progressDialog = new ProgressDialog(this);
        mFirebaseAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.emailid);
        phone=findViewById(R.id.registerPhoneno);
        password=findViewById(R.id.registerPassword);
        confirmPassword=findViewById(R.id.repeatPassword);
        affectedPeople=findViewById(R.id.registerPeople);
        landmark=findViewById(R.id.registerLandmark);
        aadhar=findViewById(R.id.registerAadhar);
        policeThana=findViewById(R.id.registerPolice);

        registerButton= findViewById(R.id.RegisterButton);
        mReg= FirebaseDatabase.getInstance().getReference().child("Sub Admin Registration");
        mRef=FirebaseDatabase.getInstance().getReference().child("Police Thana Details");
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchLocation();

//                String e=email.getText().toString();
//                String pwd=password.getText().toString();
//                String cpwd=confirmPassword.getText().toString();
//                String pne=phone.getText().toString();
//                String ap=affectedPeople.getText().toString();
//                String lmark=landmark.getText().toString();
//                String aid=aadhar.getText().toString();
//                String pt=policeThana.getText().toString();
//                if(e.isEmpty())
//                {
//                    email.setError("Please Enter email id!!");
//                    email.requestFocus();
//
//                }
//                else if(pne.isEmpty())
//                {
//                    phone.setError("Please Enter your phone number!!!");
//                    phone.requestFocus();
//                }
//                else if(aid.isEmpty())
//                {
//                    aadhar.setError("Please Enter Aadhar UID");
//                    aadhar.requestFocus();
//                    count++;
//                }
//                else if(ap.isEmpty())
//                {
//                    affectedPeople.setError("Enter no of people affected in disaster!!");
//                    affectedPeople.requestFocus();
//                    count++;
//                }
//                else if(pt.isEmpty())
//                {
//                    policeThana.setError("Enter nearest Police Thana details");
//                    policeThana.requestFocus();
//                }
//                else if(lmark.isEmpty())
//                {
//
//                    landmark.setError("Enter nearest Landmark");
//                    landmark.requestFocus();
//                }
//                else if(pwd.isEmpty())
//                {
//
//                    password.setError("Please enter Your Password!!");
//                    password.requestFocus();
//                }
//                else if(cpwd.isEmpty())
//                {
//
//                    confirmPassword.setError("Confirm your Password");
//                    confirmPassword.requestFocus();
//                }
//                else if(!(pwd.equals(cpwd)))
//                {
//
//                    confirmPassword.setError("Password does not Match");
//                    confirmPassword.requestFocus();
//                }
//                else if(e.isEmpty() && pwd.isEmpty() && pne.isEmpty() && aid.isEmpty() && ap.isEmpty() && lmark.isEmpty() && pt.isEmpty() && cpwd.isEmpty())
//                {
//
//                    Toast.makeText(ReliefCentreRegisterActivity.this, "Fields are Empty!!", Toast.LENGTH_SHORT).show();
//                }
//                else if(!(e.isEmpty()&&pwd.isEmpty()&&pne.isEmpty()&&aid.isEmpty()&&ap.isEmpty()&&lmark.isEmpty()&&pt.isEmpty()&&cpwd.isEmpty()))
//                {
//                    mFirebaseAuth.createUserWithEmailAndPassword(e,pwd).addOnCompleteListener(ReliefCentreRegisterActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(!task.isSuccessful()){
//                                Toast.makeText(ReliefCentreRegisterActivity.this, "Signup Unsuccessful, Please Try Again!!", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                startActivity(new Intent(ReliefCentreRegisterActivity.this,MainActivity.class));
//                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Type").setValue(type);
//                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Email id").setValue(email.getText().toString());
//                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Phone No").setValue(phone.getText().toString());
//                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Aadhar UID").setValue(aadhar.getText().toString());
//                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Affected People").setValue(affectedPeople.getText().toString());
//                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Landmark").setValue(landmark.getText().toString());
//                                mRef.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Aadhar UID").setValue(aadhar.getText().toString());
//                                mRef.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Nearest Police Thana").setValue(policeThana.getText().toString());
//                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Latitude").setValue(latitude.toString(latitude));
//                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Longitude").setValue(longitude.toString(longitude));
//
//                            }
//
//                        }
//                    });
//                }
//                else
//                {
//                    Toast.makeText(ReliefCentreRegisterActivity.this, "Error Occurred!!", Toast.LENGTH_SHORT).show();
//                }
//                //Intent toReliefCentreDashboard = new Intent(ReliefCentreRegisterActivity.this, MainActivity.class);
//                //startActivity(toReliefCentreDashboard);
//
//
            }

        });








        registerButton = findViewById(R.id.RegisterButton);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                fetchLocation();
//
//
//            }
//        });


//        registerButton= findViewById(R.id.RegisterButton);
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent toReliefCentreDashboard = new Intent(ReliefCentreRegisterActivity.this, MainActivity.class);
//                startActivity(toReliefCentreDashboard);
//            }
//        });
//    }
    }

    private void fetchLocation() {

        if (ContextCompat.checkSelfPermission(ReliefCentreRegisterActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ReliefCentreRegisterActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                new AlertDialog.Builder(this)
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission To Register and Use this Application")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(ReliefCentreRegisterActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(ReliefCentreRegisterActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    });
            pranavfunction();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //abc

//                registerButton = findViewById(R.id.RegisterButton);
//                registerButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent toReliefCentreDashboard = new Intent(ReliefCentreRegisterActivity.this, MainActivity.class);
//                        startActivity(toReliefCentreDashboard);
//            }
//        });
            }else{

            }
        }
    }

    private void pranavfunction(){
        String e=email.getText().toString();
        String pwd=password.getText().toString();
        String cpwd=confirmPassword.getText().toString();
        String pne=phone.getText().toString();
        String ap=affectedPeople.getText().toString();
        String lmark=landmark.getText().toString();
        String aid=aadhar.getText().toString();
        String pt=policeThana.getText().toString();
        if(e.isEmpty())
        {
            email.setError("Please Enter email id!!");
            email.requestFocus();

        }
        else if(pne.isEmpty())
        {
            phone.setError("Please Enter your phone number!!!");
            phone.requestFocus();
        }
        else if(aid.isEmpty())
        {
            aadhar.setError("Please Enter Aadhar UID");
            aadhar.requestFocus();
            count++;
        }
        else if(ap.isEmpty())
        {
            affectedPeople.setError("Enter no of people affected in disaster!!");
            affectedPeople.requestFocus();
            count++;
        }
        else if(pt.isEmpty())
        {
            policeThana.setError("Enter nearest Police Thana details");
            policeThana.requestFocus();
        }
        else if(lmark.isEmpty())
        {

            landmark.setError("Enter nearest Landmark");
            landmark.requestFocus();
        }
        else if(pwd.isEmpty())
        {

            password.setError("Please enter Your Password!!");
            password.requestFocus();
        }
        else if(cpwd.isEmpty())
        {

            confirmPassword.setError("Confirm your Password");
            confirmPassword.requestFocus();
        }
        else if(!(pwd.equals(cpwd)))
        {

            confirmPassword.setError("Password does not Match");
            confirmPassword.requestFocus();
        }
        else if(e.isEmpty() && pwd.isEmpty() && pne.isEmpty() && aid.isEmpty() && ap.isEmpty() && lmark.isEmpty() && pt.isEmpty() && cpwd.isEmpty())
        {

            Toast.makeText(ReliefCentreRegisterActivity.this, "Fields are Empty!!", Toast.LENGTH_SHORT).show();
        }
        else if(!(e.isEmpty()&&pwd.isEmpty()&&pne.isEmpty()&&aid.isEmpty()&&ap.isEmpty()&&lmark.isEmpty()&&pt.isEmpty()&&cpwd.isEmpty()))
        {
            mFirebaseAuth.createUserWithEmailAndPassword(e,pwd).addOnCompleteListener(ReliefCentreRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(ReliefCentreRegisterActivity.this, "Signup Unsuccessful, Please Try Again!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        startActivity(new Intent(ReliefCentreRegisterActivity.this,MainActivity.class));
                        mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Email id").setValue(email.getText().toString());
                        mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Phone No").setValue(phone.getText().toString());
                        mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Aadhar UID").setValue(aadhar.getText().toString());
                        mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Affected People").setValue(affectedPeople.getText().toString());
                        mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Landmark").setValue(landmark.getText().toString());
                        mRef.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Aadhar UID").setValue(aadhar.getText().toString());
                        mRef.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Nearest Police Thana").setValue(policeThana.getText().toString());
                        mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Latitude").setValue(latitude);
                        mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Longitude").setValue(longitude);

                    }

                }
            });
        }
        else
        {
            Toast.makeText(ReliefCentreRegisterActivity.this, "Error Occurred!!", Toast.LENGTH_SHORT).show();
        }
        //Intent toReliefCentreDashboard = new Intent(ReliefCentreRegisterActivity.this, MainActivity.class);
        //startActivity(toReliefCentreDashboard);


    }





}
