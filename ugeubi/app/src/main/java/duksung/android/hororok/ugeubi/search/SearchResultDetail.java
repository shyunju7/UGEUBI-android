package duksung.android.hororok.ugeubi.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

import duksung.android.hororok.ugeubi.R;
import duksung.android.hororok.ugeubi.retrofit.ItemInfoDTO;

// 검색 결과 페이지
public class SearchResultDetail extends Activity {


    GridView contentsGridView;
    Button back_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_detail);

        back_btn = findViewById(R.id.back_btn);
        contentsGridView = findViewById(R.id.contentsGridView);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // 어댑터 생성, 설정
        SearchResultDetailAdapter adapter = new SearchResultDetailAdapter();
        contentsGridView.setAdapter(adapter);


        // 약 정보 가져오기
        Intent intent = getIntent();
        ArrayList<ItemInfoDTO> resultList = (ArrayList<ItemInfoDTO>) intent.getSerializableExtra("resultList");



        // 약정보를 그리드뷰에 표시
//        for (ItemInfoDTO itemInfoDTO : resultList) {
//            adapter.addItem(itemInfoDTO.getITEM_NAME(), itemInfoDTO.getENTP_NAME());
//        }


        adapter.addItem("품목명", "게므론연질캡슐(수출용)");
        adapter.addItem("URL", "https://nedrug.mfds.go.kr/pbp/cmn/pdfViewer/200409932/NB");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제");
        adapter.addItem("성상", "백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제백색의 양면이 볼록한 원형 정제");






    }


    // 어댑터
    class SearchResultDetailAdapter extends BaseAdapter {

        ArrayList<SearchResultDetailData> items = new ArrayList<SearchResultDetailData>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(String key, String value){
            SearchResultDetailData data = new SearchResultDetailData(key, value);
            items.add(data);
        }

        @Override
        public SearchResultDetailData getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            SearchResultDetailViewer searchViewer = new SearchResultDetailViewer(getApplication());
            searchViewer.setItem(items.get(i));
            return searchViewer;

        }
    }



    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
