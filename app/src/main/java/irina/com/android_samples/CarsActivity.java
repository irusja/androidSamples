package irina.com.android_samples;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class CarsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        final ArrayList<CarObject> carsList = getCarObjects();

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
                    viewHolderCar = new ViewHolderCar();
                    viewHolderCar.textViewCarDescription = convertView.findViewById(R.id.carDescription);
                    viewHolderCar.imageViewCarPicture = convertView.findViewById(R.id.carPicture);

                    convertView.setTag(viewHolderCar);
                }
                viewHolderCar.textViewCarDescription.setText(car.carName);
                viewHolderCar.imageViewCarPicture.setImageDrawable(car.carPicture);

                return convertView;
            }
        };

        GridView view = findViewById(R.id.gridViewCars);
        view.setAdapter(adapter);
        view.setNumColumns(2);

        setOnTouchListener(view);

    }

    @NonNull
    private ArrayList<CarObject> getCarObjects() {
        Resources res = getResources();
        final String[] carsNameArray = res.getStringArray(R.array.car_types);
        final ArrayList<CarObject> carsList = new ArrayList<>();
        for (int i = 0; i < carsNameArray.length; i++) {
            String carName = carsNameArray[i];
            int resourceId = this.getResources().getIdentifier(carName.toLowerCase(), "drawable", getPackageName());
            Drawable carImage = getResources().getDrawable(resourceId);
            CarObject newCar = new CarObject(carName, carImage);
            carsList.add(newCar);
        }
        return carsList;
    }

    private void setOnTouchListener(GridView view) {
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                Resources res = getResources();
                String[] items = res.getStringArray(R.array.car_types);

                Toast toast = Toast.makeText(getApplicationContext(), items[position], Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}
