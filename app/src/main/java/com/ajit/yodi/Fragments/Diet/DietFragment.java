package com.ajit.yodi.Fragments.Diet;

import static com.ajit.yodi.Model.DietModel.Diet_Donts_Layout;
import static com.ajit.yodi.Model.DietModel.Diet_Dos_Layout;
import static com.ajit.yodi.Model.DietModel.Donts_Head;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajit.yodi.Adapters.DietAdapter;
import com.ajit.yodi.Dashboard.Options;
import com.ajit.yodi.Model.DietModel;
import com.ajit.yodi.Model.SessionManager;
import com.ajit.yodi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DietFragment extends Fragment {

    public DietFragment() {
        // Required empty public constructor
    }

    RecyclerView drecyclerView;
    List<DietModel> diet;
    DietAdapter adapter;
    TextView ptitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diet, container, false);

        ptitle = view.findViewById(R.id.diet_list_title);

        drecyclerView = (RecyclerView) view.findViewById(R.id.diet_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        drecyclerView.setLayoutManager(layoutManager);

        diet = new ArrayList<>();

        adapter = new DietAdapter(diet);
        drecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        SetDiet();

        return view;
    }

    private void SetDiet() {
        SessionManager sessionManager = new SessionManager(getContext());
        HashMap<String, String> sel_choice = sessionManager.GetUserChoice();

        String problem = sel_choice.get(SessionManager.KEY_PROBLEM);

        if (problem != null) {

            ptitle.setText(problem);

            if (problem == "Migraine") {

                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.almonds, "Avocados", "Rich in Magnesium"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.carrot, "Water", "Stay Hydrated"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.berries, "Carrot", "Anti-flammatory properties"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sweet_potato, "Sweet Potato", "Anti-flammatory properties"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.banana, "Banana", "Magnesium"));

                diet.add(new DietModel(Donts_Head, R.drawable.ic_baseline_clear, "Dont's"));

                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.chinese, "Chinese Food", "Monosodium Glutamate,harmful food additive"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "Trigger Migraine"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.chocolate, "Chocolate", "Due to the chemical beta-phenylalanine"));

            } else if (problem == "Asthma") {
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avacardo, "Avocados", "Contains anti-oxidant gluathione, which guards lungs"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.carrot, "Carrot", "Contains anti-oxidant beta-carotene, immune against asthma attack"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.berries, "Berries", "Contains anti-oxidant flavonoids, which have anti-flammatory benefits"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.pumpkin_seeds, "Pumpkin Seeds", "Rich in Magnesium"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.water, "Water", "Drink 3-4 litre a day"));


                diet.add(new DietModel(Donts_Head, R.drawable.ic_baseline_clear, "Dont's"));

                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.beans, "Beans", "Like cholle and rajma, it can bloast your belly"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.allergy_food, "Allergic food", "Food reaction could cause wheezing and other asthma symptoms"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.wine, "Wine", "Harmful preservative sulfite added"));

            } else if (problem == "Thyroid") {
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.pumpkin_seeds, "Sunflower Seeds", "Rich in zinc,increase thyroid hormones"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.legumes, "Legumes", "Such as chana dal,masoor dal,matki"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.brown_rice, "Brown Rice", "Rich in selenium"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.dry_fruit, "Dry Fruits", "Rich in iron"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.salt, "Salt", "Take iodised salt"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.curd, "Curd", "Improve thyroid function"));

                diet.add(new DietModel(Donts_Head, R.drawable.ic_baseline_clear, "Dont's"));

                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.millets, "Millets", "Bajra,Jwar,Maize"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.fastfood, "Fast Food", "Bad for thyroid"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.cabbage, "Cruciferous Vegetables", "Such as cauliflower,cabbage,broccoli"));

            } else if (problem == "Diabetes") {
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.green_leafy, "Green Leafy", "Rich in Fibre"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.whole_grain, "Whole Grain", "Such as brown rice,barley,oats"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.beans, "Beans", "Less in Glycemic index"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.diary, "Dairy Products", "Low fat diary products such as milk and cheese"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.berries, "Berries", "Vitamin C,They have anti-oxidant that reduces oxidative stress"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.fruit, "Fruits", "Includes oranges, melon, berries, apples, bananas, and grapes"));

                diet.add(new DietModel(Donts_Head, R.drawable.ic_baseline_clear, "Dont's"));

                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.sugar, "Sugary Products", "Such as candy, cookies, cake, ice cream, sweetened cereals, and canned fruits with added sugar"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.processed_meat, "Processed Meat", "Processed meats like bacon, hot dogs, salami,cold cuts are high in sodium, preservatives"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "This is because alcohol can increase the risk of low blood sugar"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.rice, "White Rice", "Processed grain"));

            } else if (problem == "High Blood Pressure") {
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sunflowerr_seeds, "SunFlower Seeds", "Mineral-rich food that lower blood pressure"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.berries, "Berries", "Like Blueberries and strawberries,contains antioxidant compounds called anthocyanins"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.kiwi, "Kiwi", "Vitamic C,improve blood pressure"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.beet_root, "Beet Root", "Beet juice can reduce blood pressure"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.green_leafy, "Green Leafy Vegetables", "Like cabbage,spinach"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.banana, "Banana", "Add banana in your breakfast"));

                diet.add(new DietModel(Donts_Head, R.drawable.ic_baseline_clear, "Dont's"));

                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "It can raise your blood pressure"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.salt, "Salt", "Try to eat less salty food"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.smoking, "Smoking", "Each cigarette you smoke increases your blood pressure"));

            } else if (problem == "Low Blood Pressure") {
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.tulsi, "Tulsi", "Chew Tulsi Leaves helps to regulate blood pressure"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.green_leafy, "Green Leafy Vegetables", "Chew Tulsi Leaves helps to regulate blood pressure"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.citrus_food, "Citrus Fruits", "Like oranges,lemon"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.dry_fruit, "Dry Fruits", "Like walnuts,almonds"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.salt, "Salt", "Increase salt intake as sodium helps to bring up the pressure"));

                diet.add(new DietModel(Donts_Head, R.drawable.ic_baseline_clear, "Dont's"));

                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.overeating, "Large Meals", "Large meals may cause more dramatic drops in blood pressure"));

            } else if (problem == "Liver Problem") {
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.green_leafy, "Green Leafy Vegetables", "Like spinach,methi,helps to detoxify liver"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sunflowerr_seeds, "Sunflower Seeds", "High in vitamin E"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.tumeric_milk, "Turmeric Milk", "Reduces load on liver cells"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.coffee, "Coffee", "But not more than 2-3 cups in a day"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.oats, "Oatmeal", "Fibre Rich Food"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.garlic, "Garlic", "May help reduce body weight and fat in people with fatty liver disease"));

                diet.add(new DietModel(Donts_Head, R.drawable.ic_baseline_clear, "Dont's"));

                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "It can damage your liver"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.fastfood, "Fast Food", "High in saturated fat,it can make it harder for your liver to do its job"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.sugar, "Sugar Products", "Stay away from sugary foods such as candy, cookies,and sodas"));

            } else if (problem == "Indigestion") {
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.ginger, "Ginger", "Natural Treatment for heatburn"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.curd_rice, "Curd Rice", "Easy to digest"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.oats, "OatMeal", "Rich in Fibre"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.fruit, "Fruits", "Non Citrus Foods Like Watermelon,Banana,Apple etc.. helps to improve digestion"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.green_leafy, "Vegetables", "Vegetables are naturally low in fat and sugar"));

                diet.add(new DietModel(Donts_Head, R.drawable.ic_baseline_clear, "Dont's"));

                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.citrus_food, "Citrus Food", "It can be worse in indigestion,especially highly acidic food"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.fastfood, "Fast Food", "Delays the Stomach emptying process"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.coffee, "Caffeine", "Stimulate stomach to secrete more acid"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "Known stimulant for production of acid in stomach"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.chocolate, "Chocolate", "It has been shown to relax the smooth muscle in the LES and increase acidic reflux"));

            } else if (problem == "Depression") {
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.berries, "Berries", "VITAMIN C,ANTIOXIDANT"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.beta_carotene, "Beta Carotene", "Such as broccoli,carrot,spinach,sweet potato"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sweet_potato, "Sweet Potato", "Beta-Carotene,Anti-oxidant"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avacardo, "Avocados", "Vitamin E"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.dry_fruit, "Dry Fruits", "Vitamin B"));

                diet.add(new DietModel(Donts_Head, R.drawable.ic_baseline_clear, "Dont's"));

                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.sugar, "Sugar", "Eat Products less in sugar"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "Trigger depression"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.fastfood, "Fast Food", "No nutrients with lot of calories"));
            } else if (problem == "Weight Loss") {
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.oats, "Oats", "Nutrient composition of oats is well-balanced"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sweet_potato, "Sweet Potato", "Beta-Carotene,Anti-oxidant"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.fruit, "Fruits", "Water Filled Fruits such as watermelon,oranges,Cucumber,soups"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.green_leafy, "Green Leafy Vegetables", "Rich in fibre"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.whole_grain, "Whole Grains", "(Such as whole wheat, brown rice, barley, quinoa)"));
                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.water, "Water", "Drink lemon water every morning and drink 3-4 litre normal water in a day"));

                diet.add(new DietModel(Donts_Head, R.drawable.ic_baseline_clear, "Dont's"));

                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.sugar, "Sugar Products", "High in Calories and can cause weight gain"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.chinese, "Chinese Food", "Cause indigestion"));
                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "They do not help the body meet its nutritional needs"));
            } else {
                Intent intent = new Intent(getContext(), Options.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

        } else {
            Toast.makeText(getContext(), "Select Any Option", Toast.LENGTH_SHORT).show();
        }
    }

}






    //***********************************************************************************

//    private void diet_donts() {
//        SessionManager sessionManager = new SessionManager(getContext());
//        HashMap<String, String> sel_choice = sessionManager.GetUserChoice();
//
//        String problem = sel_choice.get(SessionManager.KEY_PROBLEM);
//
//        if (problem != null) {
//
//            if (problem == "Migraine") {
//
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.almonds, "Almonds", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.banana, "Banana", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.carrot, "Carrot", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.chinese, "Chinese Food", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            } else if (problem == "Asthma") {
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avacardo, "Avocado", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.apple, "Apple", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.broccoli, "Broccoli", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.berries, "Berry", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.fastfood, "Fast Food", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//            }else if (problem == "Thyroid") {
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sunflowerr_seeds, "Sunflower Seeds", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.cucumber, "Cucumber", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.carrot, "Carrot", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.peanuts, "Peanuts", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.sweet_potato, "Sweet Potato", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "Diabetes") {
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.beans, "Beans", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.berries, "Berries", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.lemon, "Lemon", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.maida_refined_flour, "Maida", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.rice, "Rice", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "High Blood Pressure") {
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.spinach, "Spinach", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.berries, "Berries", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.carbonated_drinks, "Carbonated Drinks", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "Low Blood Pressure") {
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.fruit, "Fruit", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.milk, "Milk", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sunflowerr_seeds, "Sunflower Seeds", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.fastfood, "Fast Food", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "Liver Problem") {
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avacardo, "Avocado", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.apple, "Apple", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.tumeric_milk, "Turmeric Milk", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.fastfood, "Fast Food", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "Indigestion") {
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.carrot, "Carrot", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.fruit, "Fruits", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.ginger_tea, "Ginger Tea", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.citrus_food, "Citrus Food", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.overeating, "Over Eating", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "Depression") {
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avacardo, "Avocado", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sweet_potato, "Sweet Potato", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.broccoli, "Broccoli", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.sugar, "Sugar", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            } else {
//                Intent intent = new Intent(getContext(), Options.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//
//        } else {
//            Toast.makeText(getContext(), "Select Any Option", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void diet_dos() {
//        SessionManager sessionManager = new SessionManager(getContext());
//        HashMap<String, String> sel_choice = sessionManager.GetUserChoice();
//
//        String problem = sel_choice.get(SessionManager.KEY_PROBLEM);
//
//        if (problem != null) {
//
//            if (problem == "Migraine") {
//
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.almonds, "Almonds", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.banana, "Banana", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.carrot, "Carrot", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.chinese, "Chinese Food", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            } else if (problem == "Asthma") {
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avacardo, "Avocado", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.apple, "Apple", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.broccoli, "Broccoli", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.berries, "Berry", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.fastfood, "Fast Food", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//            }else if (problem == "Thyroid") {
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sunflowerr_seeds, "Sunflower Seeds", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.cucumber, "Cucumber", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.carrot, "Carrot", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.peanuts, "Peanuts", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.sweet_potato, "Sweet Potato", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "Diabetes") {
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.beans, "Beans", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.berries, "Berries", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.lemon, "Lemon", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.maida_refined_flour, "Maida", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.rice, "Rice", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "High Blood Pressure") {
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.spinach, "Spinach", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.berries, "Berries", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.carbonated_drinks, "Carbonated Drinks", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "Low Blood Pressure") {
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.fruit, "Fruit", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.milk, "Milk", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sunflowerr_seeds, "Sunflower Seeds", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.fastfood, "Fast Food", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "Liver Problem") {
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avacardo, "Avocado", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.apple, "Apple", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.tumeric_milk, "Turmeric Milk", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.fastfood, "Fast Food", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "Indigestion") {
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.carrot, "Carrot", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.fruit, "Fruits", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.ginger_tea, "Ginger Tea", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.citrus_food, "Citrus Food", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.overeating, "Over Eating", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            }else if (problem == "Depression") {
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avacardo, "Avocado", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.sweet_potato, "Sweet Potato", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.broccoli, "Broccoli", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.sugar, "Sugar", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.alcohol, "Alcohol", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//
//            } else {
//                Intent intent = new Intent(getContext(), Options.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//
//        } else {
//            Toast.makeText(getContext(), "Select Any Option", Toast.LENGTH_SHORT).show();
//        }
//    }













    //***********************************************************************
    //***********************************************************************

//    private void SetDiet() {
//
//        SessionManager sessionManager = new SessionManager(getContext());
//        HashMap<String, String> sel_choice = sessionManager.GetUserChoice();
//
//        String problem = sel_choice.get(SessionManager.KEY_PROBLEM);
//
////        if (problem == "Asthma") {
////
////            diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avocado, "Avocado", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//////            if (diet_option.isChecked()) {
//////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avocado, "Avocado", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//////            } else {
//////                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.berry, "Berry", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//////            }
////
////        }
//
//
//        if (problem != null) {
//
//            if (problem == "Migraine") {
//
//                do_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avocado, "Avocado", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                    }
//                });
//
//                dont_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        diet.add(new DietModel(Diet_Donts_Layout, R.drawable.berry, "Berry", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                    }
//                });
//
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.avocado, "Avocado", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.berry, "Berry", "IT IS FOR VITAMIN C.GOOD FOR HEALTH"));
////                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.milk, "Milk", "Lungs Problem"));
//
////                img.add(R.drawable.avocado);
////                img.add(R.drawable.broccoli);
////                img.add(R.drawable.carrot);
////                img.add(R.drawable.spinach);
////
////                titles.add("Avocado");
////                titles.add("Broccoli");
////                titles.add("Carrot");
////                titles.add("Spinach");
//
//            } else if (problem == "Asthma") {
//                diet.add(new DietModel(Diet_Dos_Layout, R.drawable.carrot, "Carrot", "Vitamin D"));
//                diet.add(new DietModel(Donts_Head,R.drawable.ic_baseline_clear, "Dont's"));
//                diet.add(new DietModel(Diet_Donts_Layout, R.drawable.berry, "Milk", "Lungs Problem"));
//
//            } else {
//                Intent intent = new Intent(getContext(), Options.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//
//        } else {
//            Toast.makeText(getContext(), "Select Any Option", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
