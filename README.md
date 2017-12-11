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
    compile 'com.github.Omega-R:OmegaIntentBuilder:0.0.1'
}
```
# Usage

**Call Intent**
```
new OmegaIntentBuilder().call()
                        .phoneNumber("Your phone number")
                        .handler(context)
                        .startActivity();
```

**Email Intent**
```
new OmegaIntentBuilder().email()
                        .setText("Hello world")
                        .setEmailTo("develop@omega-r.com")
                        .setSubject("Great library")
                        .handler(this)
                        .startActivity();
```

**Share Intent**
```
new OmegaIntentBuilder().share()
                        .setEmailTo("develop@omega-r.com")
                        .setEmailBcc(Arrays.asList("bcc1@test.com","bcc2@test.com")) // Concealed addresses
                        .setEmailCc(Arrays.asList("cc1@test.com","cc2@test.com"))  // Copy addresses 
                        .setSubject("Great library")
                        .handler(this)
                        .setChooserTitle("Choose")
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
