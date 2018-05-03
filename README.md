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
#### If you use only Java.
```
dependencies {
    implementation 'com.github.Omega-R.OmegaIntentBuilder:core:1.1.1'
    // For extras
    implementation 'com.github.Omega-R.OmegaIntentBuilder:annotations:1.1.1'
    annotationProcessor 'com.github.Omega-R.OmegaIntentBuilder:processor:1.1.1'
}
```

#### If you use Kotlin or Java with Kotlin, please use kapt instead java annotationProcessor. Main build.gradle file should look like this
```
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-kapt'

kapt {
    generateStubs = true
}

android {
    ......
}    
    
dependencies {
    implementation 'com.github.Omega-R.OmegaIntentBuilder:core:1.1.1'
    // For extras
    implementation 'com.github.Omega-R.OmegaIntentBuilder:annotations:1.1.1'
    kapt 'com.github.Omega-R.OmegaIntentBuilder:processor:1.1.1'
}
```



## [Wiki](https://github.com/Omega-R/OmegaIntentBuilder/wiki)

Supported features.

* [Build Extras](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Build-Extra)
  * [Activity Extras Builder](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Activity-Extras-builder)
  * [Fragment Extras Builder](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Fragment-Extras-Builder)
  * [Service Extras Builder](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Service-Extras-Builder) 
* [Supported intents](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Supported-intents) 
  * [Activity Intent Builder](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Activity-Intent-builder)
  * [Service Intent Builder](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Service-Intent-Builder)
  * [Call Intent](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Call-Intent)
  * [Email Intent](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Email-Intent)
  * [Share Intent](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Share-Intent)
  * [Web Browser Intent](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Web-Intent)
  * [Settings Intent](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Settings-Intent)
  * [Speech to text](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Speech-to-text)
  * [GooglePlayStore Intent](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Google-play-store)
  * [Photo Capture Intent](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Photo-Capture-Intent)
  * [Map Intent](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Map-Intent)
  * [Crop Image Intent](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Crop-Image-Intent)
  * [Calendar Intent](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Calendar-Intent)
  * [Pick Files](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Pick-files)
    * [File](https://github.com/Omega-R/OmegaIntentBuilder/wiki/File-Pick)
    * [Image](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Image-Pick)
    * [Audio](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Audio-Pick)
    * [Contact](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Contact-Pick)
    * [Video](https://github.com/Omega-R/OmegaIntentBuilder/wiki/Video-Pick)


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
