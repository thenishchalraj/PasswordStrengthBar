<img src="https://github.com/thenishchalraj/PasswordStrengthBar/blob/master/psb_image.png" />

### See the strength of your password through separated strength bars

<img src="https://img.shields.io/badge/Version-1.1.5-green" /> <img src="https://img.shields.io/badge/License-Apache%202.0-blue" />

## Preview of PasswordStrengthBar
<img src="https://github.com/nishchalraj/PasswordStrengthBar/blob/master/screenshots/0.png" height="300em" />&nbsp;<img src="https://github.com/nishchalraj/PasswordStrengthBar/blob/master/screenshots/1.png" height="300em" />&nbsp;<img src="https://github.com/nishchalraj/PasswordStrengthBar/blob/master/screenshots/2.png" height="300em" />&nbsp;<img src="https://github.com/nishchalraj/PasswordStrengthBar/blob/master/screenshots/3.png" height="300em" />&nbsp;<img src="https://github.com/nishchalraj/PasswordStrengthBar/blob/master/screenshots/4.png" height="300em" />


### Overview of PasswordStrengthBar library
* Can be used to see password strength
* Freely set the colour of the strength bars


## Using PasswordStrengthBar Library in your Android application

1. Add it in your root build.gradle at the end of repositories:

```groovy
    	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. Add this in your app's build.gradle

```groovy
	dependencies {
	        implementation 'com.github.thenishchalraj:PasswordStrengthBar:Tag'
	}
```
3. To use this in XML File, use 

```XML
  <com.android.thenishchalraj.passwordstrength.PasswordStrengthBar
        android:id="@+id/passwordBarCheck"
        android:layout_width="match_parent"
        android:layout_height="4dp"
        android:layout_marginStart="16dp"
        android:layout_marginEnd="16dp"/>
```
3. Methods of use with variable of PasswordStrengthBar 

```
//initialize or declare
  PasswordStrengthBar passwordStrengthBar;
...
//methods available
  setStrengthColor(int noStrengthColor, int color1, int color2, int color3, int color4)
  getMaxStrength()
  getMinStrength()
  setMaxStrength(int max)
  setMinStrength(int min)
  getStrength()
  setStrength(int strength)
//

```
### TODO
* More features related to Password and Strength bars' functionality.
* Vertical password strength bars.

## If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:


## To learn how to write your own Android Library
Follow [this](https://medium.com/mindorks/want-to-write-your-first-android-library-7bba6ca4e0c5) medium link and start today.

### Contributing to PasswordStrengthBar
Fork and make your changes/improvements. All pull requests are welcome.
