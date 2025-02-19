
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class conexionfirebase {
    
    static Firestore bd;

    public static void conectar() throws IOException {
        FileInputStream serviceAccount
                = new FileInputStream("firebass.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        bd = FirestoreClient.getFirestore();
        System.out.println("Se conecto con exito");

    }
    public static void main(String[] args){
        try{
            conectar();
        }catch (Exception e) {
            
        }
    }
    
}


