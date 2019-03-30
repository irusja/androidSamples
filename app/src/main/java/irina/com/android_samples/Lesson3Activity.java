package irina.com.android_samples;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Lesson3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3);

        // ------------

//        ListView listView = findViewById(R.id.listView);
//
//        String[] streets = getResources().getStringArray(R.array.streets_array);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                R.layout.list_item, //layout of items
//                R.id.textName, //id of textView in layout
//                streets //string array of data
//        );
//
//        listView.setAdapter(adapter);

        // ------------

//        Spinner spinner = findViewById(R.id.spinner);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.streets_array,
//                android.R.layout.simple_spinner_item
//        );
//
//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(this);

        // ------------

//        GridView gridView = new GridView(this);
//
//        String[] streets = getResources().getStringArray(R.array.streets_array);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                R.layout.list_item, //layout of items
//                R.id.textName, //id of textView in layout
//                streets //string array of data
//        );
//
//        gridView.setAdapter(adapter);
//        gridView.setNumColumns(2);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//
//        setContentView(gridView);

        // ------------

        final CheeseObject[] cheesesAdvanced = {
                new CheeseObject("Parmesan", "Hard, granular cheese"),
                new CheeseObject("Ricotta", "Italian whey cheese"),
                new CheeseObject("Fontina", "Italian cow's milk cheese"),
                new CheeseObject("Mozzarella", "Southern Italian buffalo milk cheese"),
                new CheeseObject("Cheddar", "Firm, cow's milk cheese")
        };

        ArrayAdapter<CheeseObject> adapter = new ArrayAdapter<CheeseObject>(this, 0, cheesesAdvanced) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                CheeseObject cheese = cheesesAdvanced[position];

                if (convertView == null) { //to increase performance
                    convertView = getLayoutInflater().inflate(R.layout.list_item, null, false);
                }

                ViewHolderCheese viewHolderCheese = (ViewHolderCheese) convertView.getTag();
                if (viewHolderCheese == null) {
                    viewHolderCheese = new ViewHolderCheese();
                    viewHolderCheese.textViewName = convertView.findViewById(R.id.textName);
                    viewHolderCheese.textViewDescription = convertView.findViewById(R.id.textDescription);

                    convertView.setTag(viewHolderCheese);
                }
                viewHolderCheese.textViewName.setText(cheese.name);
                viewHolderCheese.textViewDescription.setText(cheese.description);

                return convertView;
            }
        };

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        //setContentView(listView);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
