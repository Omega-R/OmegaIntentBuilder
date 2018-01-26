[![](https://jitpack.io/v/Omega-R/OmegaIntentBuilder.svg)](https://jitpack.io/#Omega-R/OmegaIntentBuilder)
[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

# OmegaIntentBuilder
OmegaIntentBuilder it's simple way to create and start your owner intent. 

# Installation
To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**Step 2.** Add the dependency
```
dependencies {
    compile 'com.github.Omega-R:OmegaIntentBuilder:core:1.0.1'
    // For extras
    compile 'com.github.Omega-R.OmegaIntentBuilder:annotations:1.0.1'
    annotationProcessor 'com.github.Omega-R.OmegaIntentBuilder:processor:1.0.1'
}
```
# Usage

**Call Intent**
```
OmegaIntentBuilder.from(this)
                    .call("Your phone number")
                    .createIntentHandler(this)
                    .failToast("Sorry, you don't have app for making call phone")
                    .startActivity();
```

**Email Intent**
```
OmegaIntentBuilder.from(this)
                .email()
                .text("Hello world")
                .emailTo("develop@omega-r.com")
                .subject("Great library")
                .createIntentHandler()
                .failCallback(new FailCallback() {
                    @Override
                    public void onActivityStartError(@NotNull Exception exc) {
                        Toast.makeText(getApplicationContext(), "Sorry, you don't have app for sending email", Toast.LENGTH_SHORT).show();
                    }
                })
                .startActivity();
```

**Share Intent**
```
OmegaIntentBuilder.from(context)
                    .share()
                    .emailTo("develop@omega-r.com")
                    .emailBcc("bcc1@test.com","bcc2@test.com") // Concealed addresses
                    .emailCc("cc1@test.com","cc2@test.com")  // Copy addresses
                    .subject("Great library")
                    .createIntentHandler()
                    .chooserTitle("Choose")
                    .startActivity();
```

You can download file from internet and put it to intent. 
```
OmegaIntentBuilder.from(context)
                    .share()
                    .emailTo("your_email_here@gmail.com")
                    .subject("Great library")
                    .filesUrls("https://developer.android.com/studio/images/hero_image_studio.png")
                    .fileUrlWithMimeType("https://avatars1.githubusercontent.com/u/28600571?s=200&v=4", MimeTypes.IMAGE_PNG)
                    .download(new DownloadCallback() {
                        @Override
                        public void onDownloaded(boolean success, @NotNull ContextIntentHandler contextIntentHandler) {
                            contextIntentHandler.startActivity();
                        }
                    });
```

**Web Intent**
```
OmegaIntentBuilder.from(context)
                .web("https://omega-r.com/")
                .createIntentHandler()
                .chooserTitle("Omega-R")
                .failToast("You don't have app for open urls")
                .startActivity();
```

**Settings Intent**
```
OmegaIntentBuilder.from(context)
                .settings()
                .wifi()
                .createIntentHandler()
                .startActivity();
```

**Map Intent**

Supported map application : Google, Yandex, Kakao, Naver;
```
OmegaIntentBuilder.from(context)
                .map(MapTypes.GOOGLE_MAP)
                .latitude(56.6327622)
                .longitude(47.910693)
                .address("Omega-R")
                .createIntentHandler()
                .failToast("You don't have Google Map application")
                .startActivity();
```

**Calendar Intent**
```
OmegaIntentBuilder.from(this)
                .calendar(CalendarActionTypes.INSERT_EVENT)
                .startDate(startDate)
                .endDate(endDate)
                .title("Omega-R")
                .description("Great library")
                .location("New York")
                .allDay(false)
                .organizer("develop@omega-r.com")
                .hasAlarm(false)
                .createIntentHandler()
                .startActivity();
```

**Sms Intent**
```
OmegaIntentBuilder.from(this)
                .sms("Your phone number here")
                .message("Your message here")
                .createIntentHandler()
                .startActivity();
```

**Photo capture Intent**
```
OmegaIntentBuilder.from(this)
                .photoCapture()
                // .file("Path to file") Also you can use your full path to captured file
                .createIntentHandler(this)
                // You can use startActivityForResult() method without Activity participate.
                .startActivityForResult(new ActivityResultCallback() {
                    @Override
                    public void onActivityResult(int resultCode, @NotNull Intent data) {
                        if (resultCode == RESULT_OK) {
                            Bundle extras = data.getExtras();
                            if (extras != null) {
                                Bitmap imageBitmap = (Bitmap) extras.get("data");
                                imageView.setImageBitmap(imageBitmap);
                            }
                        }
                    }
                });
```

**Crop image Intent**
```
OmegaIntentBuilder.from(this)
                .cropImage()
                .property(DEFAULT_OUTPUT_X, DEFAULT_OUTPUT_Y)
                .bitmap(BitmapFactory.decodeResource(getResources(), R.drawable.crop_image))
                //.file("Your image file here") Also you can use your image file instead bitmap
                //.fileUri("Your URI here") Also you can use your URI instead bitmap, file
                .createIntentHandler(this)
                .failToast("You don't have app for cropping image")
                .startActivityForResult(CROP_REQUEST_CODE);
```

**Pick file Intent**
```
OmegaIntentBuilder.from(this)
        .pick()
        .file()
        .mimeType("Your mimeType here") // Default mimeType "file/*"
        .multiply(false)
        .createIntentHandler(this)
        .startActivityForResult("Your result code here");
```

**Pick image Intent**
```
OmegaIntentBuilder.from(this)
        .pick()
        .image()
        .multiply(false)
        .createIntentHandler(this)
        .startActivityForResult("Your result code here");
```

**Speech to text Intent**
```
OmegaIntentBuilder.from(this)
        .speechToText()
        .prompt("Say something")
        .createIntentHandler(this)
        .failToast("You don't have app for \"Speech to text\"")
        .startActivityForResult(request_code)
```

**Extras**
```
@OmegaActivity
public class ExampleActivity extends Activity {

    @OmegaExtra("parameter_name")   // You can use your own generated parameter name
    String message;

    @OmegaExtra()   // Or parameter name will be automatically generated by value name
    String title;


    @OmegaExtraModel(prefix = "model") // You can use your model class with OmegaExtra annotation parameters
    Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        AppOmegaIntentBuilder.inject(this);
    }
}
```

```
public class Model {

    @OmegaExtra("Var2")
    public String url;

    public String getUrl() {
        return url;
    }
}
```

```
AppOmegaIntentBuilder.from(this)
                .appActivity()
                .exampleActivity()
                .parameter_name("Omega-R")
                .title("https://omega-r.com/")
                .modelVar2("https://avatars1.githubusercontent.com/u/28600571?s=200&v=4")
                .createIntentHandler()
                .startActivity();
```

# License
```
The MIT License

Copyright 2017 Omega-R

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
associated documentation files (the "Software"), to deal in the Software without restriction, 
including without limitation the rights to use, copy, modify, merge, publish, distribute, 
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
