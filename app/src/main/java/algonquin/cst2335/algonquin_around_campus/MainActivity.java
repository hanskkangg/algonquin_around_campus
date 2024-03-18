package algonquin.cst2335.algonquin_around_campus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    AsyncHttpClient client;
    Workbook workbook;
    List<String> titles,addresses,contactNumbers,storeHours,imageURL;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to import csv file from my github
        String url = "https://github.com/bikashthapa01/excel-reader-android-app/raw/master/story.xls";
        recyclerView = findViewById(R.id.ListOfData);
        progressBar = findViewById(R.id.progressBar);

        titles = new ArrayList<>();
        addresses = new ArrayList<>();
        contactNumbers = new ArrayList<>();
        storeHours = new ArrayList<>();
        imageURL = new ArrayList<>();

        client = new AsyncHttpClient();

        progressBar.setVisibility(View.VISIBLE);

        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(MainActivity.this,"Failed to download excel file",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                Toast.makeText(MainActivity.this,"Succeeded to download excel file", Toast.LENGTH_SHORT).show();
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if(file != null){
                    try {
                        workbook = workbook.getWorkbook(file);
                        //Our excel file is divided into few parts like sheets rows and columns so workbook is a abstract concept of
                        //excel file. it's main file. each sheets we have rows and columns.


                        //With this we're getting sheet values, index of 0
                        Sheet sheet = workbook.getSheet(0);

                        //we have multiple rows and columns so we're using for loops to access all instances
                        for(int i = 0 ; i < sheet.getRows(); i++){

                            Cell[] row = sheet.getRow(i);
                            titles.add(row[0].getContents());
                            addresses.add(row[1].getContents());
                            contactNumbers.add(row[2].getContents());
                            storeHours.add(row[3].getContents());
                            imageURL.add(row[4].getContents());

                        }

                        showData();

                        
                        Log.d("TAG","onSuccess:"+ titles);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (BiffException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

    }

    private void showData() {
        adapter = new Adapter(this,titles,addresses,contactNumbers,storeHours,imageURL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}