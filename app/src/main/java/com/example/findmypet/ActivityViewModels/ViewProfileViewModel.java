package com.example.findmypet.ActivityViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.findmypet.Models.ViewProfileModel;
import com.example.findmypet.Models.ViewProfilePostModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewProfileViewModel extends ViewModel {

    private MutableLiveData<ViewProfileModel> liveData;
    private ViewProfileModel profileModel;
    private List<ViewProfilePostModel> postModelList;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    // parametre göndermemiz lazım ama normal şekilde nesne oluşturulmadığı için yapamıyoruz
    // şimdilik db kullanmadığımız için parametreye de ihtiyaç yok o yzden şimdilik es geçiyoruz
    // o kısmı
//    public ViewProfileViewModel(String targetUserId){
//        liveData = new MutableLiveData<>();
//        init(targetUserId);
//    }
    public ViewProfileViewModel(){
        liveData = new MutableLiveData<>();
        init();
    }

    public MutableLiveData<ViewProfileModel> getProfile(){
        return liveData;
    }
    private void init() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        postModelList = new ArrayList<>();
        populatePostList();
        populateProfile(postModelList);
        liveData.setValue(profileModel);
    }

    private void populateProfile(List<ViewProfilePostModel> postModelList) {

        profileModel = new ViewProfileModel("ft","fatih_terim"
                ,"fatih terim","GOAT teknik direktör",10000
                ,1,100,postModelList);


//        databaseReference.child("Users").child(targetUserId)
//                .addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // tek tek verileri al, sonra içine listeyi de koy
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    private void populatePostList() {
        ViewProfilePostModel post = new ViewProfilePostModel("ft","photo","fatih terim",
                2,5,"photo","I am the last bird fucker","online");
        ViewProfilePostModel post1 = new ViewProfilePostModel("icardi","photo","icardi",
                2,5,"photo","söylenmedi hiiiiiç","offline");
        ViewProfilePostModel post2 = new ViewProfilePostModel("diagne","photo","diagne",
                2,5,"photo","olmasaydı sonumuz böyle","online");
        ViewProfilePostModel post3 = new ViewProfilePostModel("falcao","photo","falcao",
                2,5,"photo","sakat doğdum sakat ölücem","online");

        postModelList.add(post);
        postModelList.add(post2);
        postModelList.add(post3);
        postModelList.add(post1);
        postModelList.add(post2);
        postModelList.add(post);
        postModelList.add(post2);
        postModelList.add(post3);
        postModelList.add(post1);
        postModelList.add(post2);

        // db den post ları çekicez
//        databaseReference.child("Posts").child(firebaseUser.getUid())
//              .addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){ // post id lerin üzerinde
//                                                                         // geziniyoruz
//                    ViewProfilePostModel postModel = dataSnapshot.getValue(ViewProfilePostModel.class);
//                    // bu yöntem çalışmaz muhtemelen, db uyuşmazsa eğer, o yüzden gerekirse tek tek
//                    // alırız verileri nesne oluşturup listeye atarız
//                    postModelList.add(postModel);
//
//                }
//
//                // eğer gecikme olursa populateProfileList methodunu init te değil burada çağırırız
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }


}
