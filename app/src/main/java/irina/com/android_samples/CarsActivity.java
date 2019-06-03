package irina.com.android_samples;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_gallery);

        final List<CarObject> carsList = getCarObjects();

        ArrayAdapter<CarObject> adapter = new ArrayAdapter<CarObject>(this, 0, carsList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                CarObject car = carsList.get(position);

                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.car_item, null, false);
                }

                ViewHolderCar viewHolderCar = (ViewHolderCar) convertView.getTag();
                if (viewHolderCar == null) {
                    viewHolderCar = new ViewHolderCar(convertView);
                    convertView.setTag(viewHolderCar);
                }
                viewHolderCar.textViewCarDescription.setText(car.carName);
                viewHolderCar.imageViewCarPicture.setImageDrawable(car.carPicture);

                return convertView;
            }
        };

//        GridView view = findViewById(R.id.gridViewGallery);
//        view.setAdapter(adapter);
//        view.setNumColumns(2);
//
//        setOnTouchListener(view);

    }

    @NonNull
    private List<CarObject> getCarObjects() {
        Resources res = getResources();
        List<String> carsNameArray = Arrays.asList(res.getStringArray(R.array.car_types));
        return carsNameArray.stream().map(carName -> {
            int resourceId = this.getResources().getIdentifier(carName.toLowerCase(), "drawable", getPackageName());
            Drawable carImage = getResources().getDrawable(resourceId, null);
            return new CarObject(carName, carImage);
        }).collect(Collectors.toList());
    }

    private void setOnTouchListener(GridView view) {
        view.setOnItemClickListener((adapterView, view1, position, rowId) -> {
            Resources res = getResources();
            String[] items = res.getStringArray(R.array.car_types);

            Toast toast = Toast.makeText(getApplicationContext(), items[position], Toast.LENGTH_SHORT);
            toast.show();
        });
    }

}
