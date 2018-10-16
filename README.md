# PasswordStrengthBar - See the strength of your password through separated strength bars

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
	 implementation 'com.github.nishchalraj:PasswordStrengthBar:0.0.3'
```
3. To use this in XML File, use 

```XML
  <com.android.nishchalraj.passwordstrength.PasswordStrengthBar
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

### Contributing to PasswordStrengthBar
Fork and make your changes/improvements. All pull requests are welcome.
