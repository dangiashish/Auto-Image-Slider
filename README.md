# Auto-Image-Slider-Android
Android auto image slide library. Compatible for Java and Kotlin,

[![](https://jitpack.io/v/dangiashish/Auto-Image-Slider.svg)](https://jitpack.io/#dangiashish/Auto-Image-Slider)
[![](https://img.shields.io/badge/android--sdk-24%2B-green)](https://developer.android.com/tools/sdkmanager)
[![](https://img.shields.io/badge/compatible-java-blue)](https://www.java.com/)
[![](https://img.shields.io/badge/compatible-kotlin-blueviolet)](https://kotlinlang.org/)
[![Netlify Status](https://api.netlify.com/api/v1/badges/cbda5b77-3ff3-479d-aae4-7e2d79f87567/deploy-status)](https://app.netlify.com/sites/androidimageslider/deploys)



<!-- https://github.com/dangiashish/Auto-Image-Slider/assets/70362030/86b9346d-441d-4124-8276-d35779f334ce 

### Preview
<img src="https://github.com/DangiAshish/Auto-Image-Slider/blob/8ce39ce03c180c2d4be72bff4f62993c8d18e6dc/preview.gif" alt="gif" style="width:400px; height:200px"/>-->


### Gradle

Add repository in your `build.gradle` (project-level) file :
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

    implementation 'com.github.dangiashish:Auto-Image-Slider:1.0.4'
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
        app:ais_indicator_align="@string/center"
        app:ais_placeholder="@drawable/placeholder_default_loading"
        app:ais_time_interval="5000"
        app:ais_title_background="@drawable/text_background"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
 ```

#### All Params

```groovy
app:ais_auto_sliding="true"  // Auto slide animation - true | false

app:ais_corner_radius="10"  // Widget Corner Radius - 0 | ....(n)

app:ais_indicator_align="@string/center"  // Dots Indicator Alignment - { LEFT | CENTER | RIGHT }

app:ais_dots_visible="true" // Dots visibiliy - { true | false }

app:ais_time_interval="1000" // Slide interval in milliseconds

app:ais_slide_delay="2000"  // Sliding delay by 2 seconds

app:ais_placeholder="@drawable/placeholder.png" // any placeholder image untill real image load

app:ais_exception_image="@drawable/error.png" // any error or failure image if image could not load

app:ais_title_background="@drawable/custom_background.xml" // any custom drawable as text background

app:ais_text_align="LEFT" // text/title alignment - { LEFT | CENTER | RIGHT }

app:ais_title_color="@color/white" // assign any color to title
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
        
        // set any default animation or custom animation (setSlideAnimation(ImageAnimationTypes.ZOOM_IN))
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
        
        // set any default animation or custom animation (setSlideAnimation(ImageAnimationTypes.ZOOM_IN))
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

### Licence
```
MIT License

Copyright (c) 2023 Ashish Dangi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

