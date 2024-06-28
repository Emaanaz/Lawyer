package com.ensias.healthcareapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.ensias.healthcareapp.adapter.DoctoreAdapter;
import com.ensias.healthcareapp.model.Doctor;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchPatActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference doctorRef = db.collection("Lawyer");

    private DoctorAdapterFiltred adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pat);
        configureToolbar();
        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.serachPatRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Query query = doctorRef.orderBy("name", Query.Direction.DESCENDING);


        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                adapter = new DoctorAdapterFiltred(task.getResult().toObjects(Doctor.class));
                recyclerView.setAdapter(adapter);
            }
        });
        //FirestoreRecyclerOptions<Doctor> options = new FirestoreRecyclerOptions.Builder<Doctor>()
              //  .setQuery(query, Doctor.class)
              //  .build();

        //adapter = new DoctoreAdapter(options);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        Drawable r= getResources().getDrawable(R.drawable.ic_local_hospital_black_24dp);
        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" Specialite" );
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //menu.findItem(R.id.empty).setTitle(sb);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint(Html.fromHtml("<font color = #000000>" + "Search client" + "</font>"));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                DoctorAdapterFiltred.specialiteSearch = false;
               adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.option_All:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("");
                return true;
            case R.id.option_Civil:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Civil Rights Lawyer");
                return true;
            case R.id.option_Bankruptcy:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Bankruptcy Lawyer");
                return true;
            case R.id.option_Criminal:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Criminal Lawyer");
                return true;
            case R.id.option_Bank:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Banking &amp; Finance Law");
                return true;
            case R.id.option_ADR:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Alternate Dispute Resolution(A.D.R)");
                return true;
            case R.id.option_Constitutional:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Contitutional Lawyer");
                return true;
            case R.id.option_Cyber:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Cyber &amp; IT Lawyer");
                return true;
            case R.id.option_Documentation:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Documentation Lawyer");
                return true;
            case R.id.option_Start_up:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Start Up Company setup Lawyer Lawyer");
                return true;
            case R.id.option_Labour:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Employement and Labour Lawyer");
                return true;
            case R.id.option_Property:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Property Documentation Lawyer");
                return true;
            case R.id.option_Family:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Family Lawyer");
                return true;
            case R.id.option_Consultation:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Consultation Lawyer");
                return true;
            case R.id.option_Vehicle:
                DoctorAdapterFiltred.specialiteSearch = true;
                adapter.getFilter().filter("Vehicle Act Lawyer");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle("Lawyer list");
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }


}
