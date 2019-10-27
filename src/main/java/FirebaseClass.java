


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.sun.org.apache.bcel.internal.generic.IOR;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseClass {
    //
    private Firestore db;
    private String publicIpAddr = null;

    public void updateIp(){
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            publicIpAddr = in.readLine();
        }
        catch (MalformedURLException malEx){
            malEx.printStackTrace();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

    //this function return public Ip
    public String getIp(){
        return publicIpAddr;
    }

    public void init() {
        try{
            //Init FireStore
            System.out.println("Init Firebase ....");
            InputStream serviceAccount = new FileInputStream("./myAccount.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
            //Init Another
            this.updateIp();
            System.out.println("Init OK ");
        }
        catch (FileNotFoundException fl){
            fl.printStackTrace();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    //this function use for register new account
    // this func send data : username password to Server.
    public void setInfor(String username,String password){
        try{
            DocumentReference docRef = db.collection("User").document(username);
            Map<String,Object> data = new HashMap<>();
            data.put("Password", password);
            data.put("Ip",publicIpAddr);
            ApiFuture<WriteResult> result = docRef.set(data);

            System.out.println("Update time : " + result.get().getUpdateTime());
        }
        catch (InterruptedException intrr){
            intrr.printStackTrace();
        }
        catch (ExecutionException exx){
            exx.printStackTrace();
        }
    }

    // this function get public ip and store to private value

    public static void main(String[] args) {
        FirebaseClass myFirebase = new FirebaseClass();
        myFirebase.init();
        myFirebase.setInfor("tranquangle","12345");
    }
}