import logo from './logo.svg';
import './App.css';
import 'prismjs/themes/prism.css';
import React from "react";
import Prism from "prismjs";


const App = () => {
  return (
    <div>
      <header className="app-header">
        <div >
          <h1 class="main-heading">Auto Image Slider</h1>
          <p class="slogan">An Android Library</p>
        </div>
      </header>
      <main>
        <section className='app-body'>
          <h3>Android auto image slide library. Compatible for Java and Kotlin,</h3>
          <p><span>
            <a href="https://jitpack.io/#dangiashish/Auto-Image-Slider">
              <img src="https://jitpack.io/v/dangiashish/Auto-Image-Slider.svg" alt="JitPack" />
            </a>
          </span>

            <span>&nbsp;</span>

            <span>
              <img src="https://img.shields.io/badge/android--sdk-24%2B-green" alt="Android SDK 24+" />
            </span>

            <span>&nbsp;</span>

            <span>
              <a href="https://developer.android.com/tools/sdkmanager">
                <img src="https://img.shields.io/badge/compatible-java-blue" alt="Compatible with Java" />
              </a>
            </span>

            <span>&nbsp;</span>

            <span>
              <a href="https://kotlinlang.org/">
                <img src="https://img.shields.io/badge/compatible-kotlin-blueviolet" alt="Compatible with Kotlin" />
              </a>
            </span>

          </p>
          <h3>Getting Started :</h3>
          <h3>Gradle : </h3>
          <p>Add the repository in your <highlight>settings.gradle</highlight></p>

          <p> <pre>

            {`
  dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
      maven { url "https://jitpack.io" }
            }
        }
        `}
          </pre>
          </p>
          <h3>OR</h3>
          <p>Add dependency in your <highlight>build.gradle</highlight> (module-level) file :</p>
          <pre>
            {`
  dependencies 
      {  implementation 'com.github.dangiashish:Auto-Image-Slider:1.0.4'   }
          `}
          </pre>

        </section>

        <section className='app-body'>
          <h3>Code Snippets (xml) :</h3>

          <pre>
  {`
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
          `}
          </pre>

          <h3>All Params :</h3>

          <pre>
            {`
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
            `}
          </pre>

          <h3>Kotlin :</h3>
  <pre>{`
  class MainActivity : AppCompatActivity() , ItemsListene
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
  
          `}</pre>

          <h3>Java :</h3>
          <pre>
            {`

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
  
            `}
          </pre>

          <h3>Licence :</h3>
          <pre>
            {`
  MIT Lic  Copyright (c) 2023 Ashish Dangi
  
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
            `}
          </pre>


        </section>
       
       
      </main>
      <footer className='app-footer'>
        <p>Copyright © {new Date().getFullYear()} Ashish Dangi</p>
        <p>Made with ❤️ by <a className='app-link' href='https://codebyashish.netlify.app'>CodeByAshish</a></p>

      </footer>
    </div>
  );
};

export default App;

