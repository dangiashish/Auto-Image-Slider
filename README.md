# Auto-Image-Slider-Android
Android auto image slide library. Compatible for Java and Kotlin,

[![](https://jitpack.io/v/DangiAshish/Auto-Image-Slider.svg)](https://jitpack.io/#DangiAshish/Auto-Image-Slider)

### Gradle

Add dependency in your `build.gradle` (project-level) file :
```gradle
allprojects {
      repositories {
	...
	maven { url 'https://jitpack.io' }
	}
}
```
##### OR 
in your `settings.gradle`
 
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
### Add dependency :

Add dependency in your `build.gradle` (module-level) file :

```groovy
dependencies{

    implementation 'com.github.DangiAshish:Auto-Image-Slider:1.0.2'
}
```
### Code Snippets :

#### XML
```groovy
 <com.codebyashish.autoimageslider.AutoImageSlider
        android:id="@+id/autoImageSlider"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:layout_margin="10dp"
        app:ais_auto_sliding="true"
        app:ais_corner_radius="10"
        app:ais_dots_visible="false"
        app:ais_indicator_align="@string/left"
        app:ais_placeholder="@drawable/placeholder_default_loading"
        app:ais_time_interval="5000"
        app:ais_title_background="@drawable/text_background"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
 ```

#### Kotlin
```groovy
class MainActivity : AppCompatActivity() , ItemsListener {

    // declare a variable for ItemListener
    private var listener : ItemsListener? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // initialization of the listener
        listener = this
        
        // create an imageArrayList which extend ImageSlideModel class
        val autoImageList : ArrayList<ImageSlidesModel> = ArrayList()
        
        // find and initialize ImageSlider
        val autoImageSlider = findViewById<AutoImageSlider>(R.id.autoImageSlider)
        
        // add some imagees or titles (text) inside the imagesArrayList
        autoImageList.add(ImageSlidesModel("https://picsum.photos/id/237/200/300", "First image"))
        autoImageList.add(ImageSlidesModel("https://picsum.photos/id/238/200/300", ""))
        autoImageList.add(ImageSlidesModel("https://picsum.photos/id/239/200/300", "Third image"))
        
        // set the added images inside the AutoImageSlider
        autoImageSlider.setImageList(autoImageList, ImageScaleType.FIT)
        
        // set any default animation or custom animation (setDefaultAnimation(ImageAnimationTypes.ZOOM_IN))
        autoImageSlider.setDefaultAnimation()

        // handle click event on item click
        autoImageSlider.onItemClickListener(listener)

    }

    override fun onItemChanged(position: Int) {
       // do what you want on item change event
    }

    override fun onTouched(actionTypes: ImageActionTypes?, position: Int) {
       // do what you want on item touch event
    }

    override fun onItemClicked(position: Int) {
       // do what you want on click event
    }
}
```

#### Java
```groovy
public class MainActivity extends AppCompatActivity implements ItemsListener {

    // declare a variable for ItemListener
    private ItemsListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // initialization of the listener
        listener = this;

        // create an imageArrayList which extend ImageSlideModel class
        ArrayList<ImageSlidesModel> autoImageList = new ArrayList<>();
        
        // find and initialize ImageSlider
        AutoImageSlider autoImageSlider = findViewById(R.id.autoImageSlider);
        
        // add some imagees or titles (text) inside the imagesArrayList
        autoImageList.add(new ImageSlidesModel("https://picsum.photos/id/237/200/300", "First image"));
        autoImageList.add(new ImageSlidesModel("https://picsum.photos/id/238/200/300", ""));
        autoImageList.add(new ImageSlidesModel("https://picsum.photos/id/239/200/300", "Third image"));
        
        // set the added images inside the AutoImageSlider
        autoImageSlider.setImageList(autoImageList, ImageScaleType.FIT);
        
        // set any default animation or custom animation (setDefaultAnimation(ImageAnimationTypes.ZOOM_IN))
        autoImageSlider.setDefaultAnimation();

        // handle click event on item click
        autoImageSlider.setOnItemClickListener(listener);
    }

    @Override
    public void onItemChanged(int position) {
        // Do what you want on item change event
    }

    @Override
    public void onTouched(ImageActionTypes actionTypes, int position) {
        // Do what you want on item touch event
    }

    @Override
    public void onItemClicked(int position) {
        // Do what you want on click event
    }
}
```

