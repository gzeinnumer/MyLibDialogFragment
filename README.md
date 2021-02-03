<p align="center">
  <img src="https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/preview/example1.jpg" width="400"/>
</p>

<h1 align="center">
    MyLibDialogFragment
</h1>

<p align="center">
    <a><img src="https://img.shields.io/badge/Version-1.0.1-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/Kotlin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Simple way to customize <b>DialogFragment</b>.</p>
</p>

---
# Content List
* [Download](#download)
* [Feature List](#feature-list)
* [Tech stack and 3rd library](#tech-stack-and-3rd-library)
* [Usage](#usage)
* [Example Code/App](#example-codeapp)
* [Version](#version)
* [Contribution](#contribution)

---
# Download
Add maven `jitpack.io` and `dependencies` in `build.gradle (Project)` :
```gradle
// build.gradle project
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

// build.gradle app/module
dependencies {
  ...
  implementation 'com.github.gzeinnumer:MyLibDialogFragment:version'
}
```

---
# Feature List
- [x] **DialogFragment.**

---
# Tech stack and 3rd library
- DialogFragment ([docs](https://developer.android.com/reference/android/app/DialogFragment))

---
# Usage

### DialogFragment.
- Dialog View
> **dialog.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/dialog_canvas"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/white"
    android:gravity="center"
    android:orientation="vertical">

    <TextView
        android:text="You can use your widget here"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!" />

</LinearLayout>
```

#
- Create Class `CustomMyLibDialog`
Extends `MyLibDialog` to your custom `DialogFragment`. and inflate you `layout` on `onCreateView`
> **CustomMyLibDialog.java**
```java
public class CustomMyLibDialog extends MyLibDialog {

    public static CustomMyLibDialog newInstance() {
        return new CustomMyLibDialog();
    }

    public CustomMyLibDialog() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
```

#
- Custom Your Dialog

Here is some configuration that you can use. Use this configuration on `onStart()`. Optional you can use it or not.
```java
public class CustomMyLibDialog extends MyLibDialog {

    ...

    public CustomMyLibDialog() {
        //super(R.style.CustomDialogStyle); // use this to change style . like Animation
    }

    @Override
    public void onStart() {
        super.onStart();

        // disable cancel
        getDialog().setCancelable(false);

        // disable dismiss dialog out side
        getDialog().setCanceledOnTouchOutside(false);

        // set dialog full screen
        setFullScreen(true);

        // set canvas width, avaliable value 0.1 - 1.0
        setCanvasWidth(0.3);

        // set BackButton to dismiss dialog
        enableBackButton(true);
    }

    ...
}
```

#
- Custom your animation dialog show

Use super constructor in your constructor to change style

```java
public class CustomMyLibDialog extends MyLibDialog {

    ...

    public CustomMyLibDialog() {
        super(R.style.CustomDialogStyle); // use this to change style . like Animation
    }

    ...

}
```
Here is the style
```xml
<resources>

    <!-- res->styles.xml -->
    <style name="CustomDialogStyle" parent="Theme.MaterialComponents.Light.Dialog">
        <item name="android:windowMinWidthMajor">80%</item>
        <item name="android:windowMinWidthMinor">80%</item>
        <item name="android:windowEnterAnimation">@anim/anim_in</item>
        <item name="android:windowExitAnimation">@anim/anim_out</item>
    </style>

</resources>
```
Style that i prepare for you
- [anim_in](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/lib/src/main/res/anim/anim_in.xml) & [anim_out](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/lib/src/main/res/anim/anim_out.xml).
- [slide_down](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/lib/src/main/res/anim/slide_down.xml) & [slide_up](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/lib/src/main/res/anim/slide_up.xml).

#
- Change Corner

You can change corner on canvas. With this step.
```java
public class CustomMyLibDialog extends MyLibDialog {

    ...

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout _dialogCanvas = view.findViewById(R.id.dialog_canvas);

        _dialogCanvas.setBackground(requireActivity().getResources().getDrawable(R.drawable.rounded_corner));

        _dialogCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }
}
```

To dismiss `Dialog`
```java
getDialog().dismiss();
```

#
- Example Corner

`R.drawable.rounded_corner`
```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle" >

    <corners
        android:bottomLeftRadius="10dp"
        android:bottomRightRadius="10dp"
        android:topLeftRadius="10dp"
        android:topRightRadius="10dp" />

    <solid android:color="@android:color/white" />

</shape>
```
Other Example:
- Same Radius -> `R.drawable.rounded_corner` [xml](https://github.com/gzeinnumer/MyLibDialog/blob/main/example/Shapes/rounded_corner.xml) [Preview](https://github.com/gzeinnumer/MyLibDialog/blob/main/README_SHAPE.md#same-radius)
- Different Radius -> `R.drawable.rounded_corner_2` [xml](https://github.com/gzeinnumer/MyLibDialog/blob/main/example/Shapes/rounded_corner_2.xml) [Preview](https://github.com/gzeinnumer/MyLibDialog/blob/main/README_SHAPE.md#different-radius)
- Dialog 3D -> `R.drawable.rounded_layer` [xml](https://github.com/gzeinnumer/MyLibDialog/blob/main/example/Shapes/rounded_layer.xml) [Preview](https://github.com/gzeinnumer/MyLibDialog/blob/main/README_SHAPE.md#dialog-3d)
- Shadow Dialog -> `R.drawable.dialog_shadow` [xml](https://github.com/gzeinnumer/MyLibDialog/blob/main/example/Shapes/dialog_shadow.xml) [Preview](https://github.com/gzeinnumer/MyLibDialog/blob/main/README_SHAPE.md#shadow-dialog)

#
### Show The Dialog
> **MainActivity.java**
```java
FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
Fragment previous = getSupportFragmentManager().findFragmentByTag(CustomMyLibDialog.TAG);
if(previous != null){
    transaction.remove(previous);
}
CustomMyLibDialog dialog = CustomMyLibDialog.newInstance();
dialog.show(transaction, CustomMyLibDialog.TAG);
```

**Preview :**
|![](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/preview/example2.jpg)|![](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/preview/example3.jpg)|
|---|---|
| Use `setFullScreen(true)`| Use `setCanvasWidth(0.3)` |

---
# Example Code/App

**FullCode [MainActivity](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/app/src/main/java/com/gzeinnumer/mylibdialogfragment/MainActivity.java) & [CustomMyLibDialog](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/app/src/main/java/com/gzeinnumer/mylibdialogfragment/CustomMyLibDialog.java) &  [XML](https://github.com/gzeinnumer/MyLibDialogFragment/blob/master/app/src/main/res/layout/dialog.xml)**

[Sample Code And App](https://github.com/gzeinnumer/MyLibDialogFragmentExample)

---
# Version
- **1.0.0**
  - First Release
- **1.0.1**
  - Add animation and set custom animation

---
# Contribution
You can sent your constibution to `branch` `open-pull`.

---

```
Copyright 2020 M. Fadli Zein
```
