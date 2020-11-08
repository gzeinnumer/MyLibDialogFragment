<p align="center">
  <img src="https://github.com/gzeinnumer/MyLibUtils/blob/master/preview/bg.jpg" width="400"/>
</p>

<h1 align="center">
    MyLibDialogFragment
</h1>

<p align="center">
    <a><img src="https://img.shields.io/badge/Version-1.0.0-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/Koltin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Simple way to customize <b>DialogFragment</b>.</p>
</p>

---
## Download
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

## Feature List
- [x] **DialogFragment.**

## Tech stack and 3rd library
- DialogFragment ([docs](https://developer.android.com/reference/android/app/DialogFragment))

---
## USE

### DialogFragment.
> dialog.xml
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

Extends `MyLibDialog` to your custom `DialogFragment` and inflate you `layout` on `onCreateView`
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

Here is some configuration that you can use. Use this configuration on `onStart()`. Optional you can use it or not.
```java
public class CustomMyLibDialog extends MyLibDialog {

    ...

    @Override
    public void onStart() {
        super.onStart();

        // disable cancel
        getDialog().setCancelable(false);

        // disable dismiss dialog out side
        getDialog().setCanceledOnTouchOutside(false);

        // set dialog full screen
        setFullScreen(true);

        //set canvas width, avaliable value 0.1 - 1.0
        setCanvasWidth(0.3);

        //disable BackButton to dismiss dialog
        enableBackButton(true);
    }

    ...
}
```

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

---

```
Copyright 2020 M. Fadli Zein
```
