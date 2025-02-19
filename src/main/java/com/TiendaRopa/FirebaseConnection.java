/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TiendaRopa;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class FirebaseConnection {
    static Firestore bd;
    
    public Firestore conectar() {
            ClassLoader classLoader = TiendaRopaApplication.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
            try {
                FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
                
                FirebaseOptions options = new FirebaseOptions.Builder()
                  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                  .build();

                if(FirebaseApp.getApps().isEmpty()) {
                    FirebaseApp.initializeApp(options);
                }
            
            } catch (IOException e) {
                System.out.println("Ocurrio un error a la hora de inicializar el servicio.");
                System.out.println(e.getMessage());
            }
            
            bd = FirestoreClient.getFirestore();
            
            return bd;
    }
}
