package com.ensias.healthcareapp.fireStoreApi;

import com.ensias.healthcareapp.model.Doctor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DoctorHelper {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference DoctorRef = db.collection("Lawyer");

    public static void addDoctor(String name, String adresse, String tel,String specialite){
        Doctor doctor = new Doctor(name,adresse,tel, FirebaseAuth.getInstance().getCurrentUser().getEmail(),specialite);

        DoctorRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(doctor);

    }
}
