# Android-ScalableImageView
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/yqritc/maven/android-scalableimageview/images/download.svg)](https://bintray.com/yqritc/maven/android-scalableimageview/_latestVersion)

*__VideoView having same scale feature is also [available here](https://github.com/yqritc/Android-ScalableVideoView)__*  

Android ImageView having extra scale types.

![Sample](/sample/sample.gif)

# Sample (Coming Soon)
<a href="https://play.google.com/store/apps/details?id=com.yqritc.scalableimageview.sample"><img src="http://developer.android.com/images/brand/en_app_rgb_wo_60.png"/></a>

# Release Note

[Release Note] (https://github.com/yqritc/Android-ScalableImageView/releases)

# Gradle (Comming Soon)
```
repositories {
    jcenter()
}

dependencies {
    compile 'com.yqritc:android-scalableimageview:1.0.0'
}
```

# Support Scale Types  

### Scale to fit 
- fitXY
- fitStart
- fitCenter
- fitEnd

### No Scale
- leftTop
- leftCenter
- leftBottom
- centerTop
- center
- centerBottom
- rightTop
- rightCenter
- rightBottom

### Crop
- leftTopCrop
- leftCenterCrop
- leftBottomCrop
- centerTopCrop
- centerCrop
- centerBottomCrop
- rightTopCrop
- rightCenterCrop
- rightBottomCrop

### Scale Inside
- startInside
- centerInside
- endInside


# Usage

### Set scale type in layout file
```
<com.yqritc.scalableimageview.ScalableImageView
  android:id="@+id/image_view"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  app:scalableType="fitCenter"/>
```
Please refere the following xml for the list of scalableType you can set.  
[attrs.xml](https://github.com/yqritc/Android-ScalableImageView/blob/master/library/src/main/res/values/attrs.xml)

### Set scale type in source code
```
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  
  mImageView = (ScalableImageView) findViewById(R.id.image_view);
  findViewById(R.id.btn_update_scale).setOnClickListener(this);
}

@Override
public void onClick(View v) {
  switch (v.getId()) {
    case R.id.btn_update_scale:
        mImageView.setScalableType(ScalableType.CENTER_TOP_CROP);
        mImageView.requestLayout();
        break;
      default:
        break;
  }
}
```


# License
```
Copyright 2015 yqritc

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
