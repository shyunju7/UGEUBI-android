package duksung.android.hororok.ugeubi.medicine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import duksung.android.hororok.ugeubi.R;
import duksung.android.hororok.ugeubi.registerMedicine.RegisterMedicine;
import duksung.android.hororok.ugeubi.retrofit.RetrofitClient;
import duksung.android.hororok.ugeubi.retrofit.RetrofitInterface;
import duksung.android.hororok.ugeubi.retrofit.medicine.MedicineItemDTO;
import duksung.android.hororok.ugeubi.retrofit.medicine.MedicineListDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Medicine_kit_fragment  extends Fragment {


    // adapter
    public MedicineAdapter medicine_adapter = null;
    private ListView listView = null;

    // 추가 버튼
    Button add_btn;
    LinearLayout layout;
    Context context;

    // GridView
    GridView gridView;

    // LinearLayout
    LinearLayout linearLayout;

    // 버튼 크기 저장
    int width, height;

    // retrofit
    RetrofitInterface apiService;

    public final String PREFERENCE = "ugeubi.preference";




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_medicine_kit, container, false);

        apiService = RetrofitClient.getService();


        // 추가버튼, 그리드뷰
        add_btn = rootView.findViewById(R.id.add_btn);
        gridView = rootView.findViewById(R.id.gridview);

        // 등록된 약이 없다는 멘트를 담은 레이아웃
        linearLayout = rootView.findViewById(R.id.medicine_kit_init);

        // 어댑터 생성
        medicine_adapter = new MedicineAdapter();
        gridView.setAdapter(medicine_adapter);

        // 약 추가 버튼이 클릭시
        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 약 등록하기 버튼 누름 > 약 등록 페이지로 이동
                Intent intent = new Intent(getActivity(), RegisterMedicine.class);
                startActivity(intent);
            }
        });


        // 약 조회 api 호출
        getMedicineList();


        // 그리드 뷰에 아이템 클릭시, 해당 아이템의 상세 페이지로 이동
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), Medicine_kit_detail.class);
                intent.putExtra("medicineId", medicine_adapter.getItem(position).getMedicineId());
                startActivity(intent);
            }
        });





        return rootView;
    }



    /** 약 목록 조회 API 호출 **/
    public void getMedicineList(){

        // token
        SharedPreferences pref = getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        String accessToken = "Bearer " + pref.getString("accessToken", "");
        apiService.getMedicineList(accessToken).enqueue(new Callback<MedicineListDTO>() {
            @Override
            public void onResponse(Call<MedicineListDTO> call, Response<MedicineListDTO> response) {
                if(response.isSuccessful()){
                    Log.i("info", "통신성공(register medicine)");
                    MedicineListDTO apiResponse = response.body();
                    Log.i("medicine_kit", "items.size: " + apiResponse.getItems().size());

                    if(apiResponse.getItems().size() > 0 ) {
                        linearLayout.setVisibility(View.GONE);
                        gridView.setVisibility(View.VISIBLE);
                    } else {
                        linearLayout.setVisibility(View.VISIBLE);
                        gridView.setVisibility(View.GONE);
                    }

                    for (MedicineItemDTO medicineItemDTO : apiResponse.getItems()) {
                        Log.i("medicine_kit", "Name: " + medicineItemDTO.getMedicineName());
                        Log.i("medicine_kit", "Memo: " + medicineItemDTO.getMemo());
                        Log.i("medicine_kit", "Valid Term: " + medicineItemDTO.getMedicineValidTerm());
                        medicine_adapter.addItem(medicineItemDTO);
                        medicine_adapter.notifyDataSetChanged();
                        gridView.setAdapter(medicine_adapter);

                        Log.i("어댑터", "size: " + medicine_adapter.getCount());
                    }


                }
            }

            @Override
            public void onFailure(Call<MedicineListDTO> call, Throwable t) {
                Log.e("error", "통신실패(register medicine)" + t.getCause());
            }
        });

    }



}
