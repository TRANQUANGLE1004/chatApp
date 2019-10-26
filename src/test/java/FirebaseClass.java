
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FirebaseClass {
    public static void main(String[] args) {
        try{
            InputStream serviceAccount = new FileInputStream("path/to/serviceAccount.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

            Firestore db = FirestoreClient.getFirestore();

            DocumentReference docRef = db.collection("users").document("alovelace");
            // Add document data  with id "alovelace" using a hashmap
            Map<String, Object> data = new HashMap<>();
            data.put("first", "Ada");
            data.put("last", "Lovelace");
            data.put("born", 1815);
            //asynchronously write data
            ApiFuture<WriteResult> result = docRef.set(data);

            // result.get() blocks on response
            System.out.println("Update time : " + result.get().getUpdateTime());
        }
        catch (FileNotFoundException foe){
            System.out.println(foe);
        }

        System.out.println("hello");
    }
}
