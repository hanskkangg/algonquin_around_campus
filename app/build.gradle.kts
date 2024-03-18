plugins {
    id("com.android.application")
}

android {
    namespace = "algonquin.cst2335.algonquin_around_campus"
    compileSdk = 34

    defaultConfig {
        applicationId = "algonquin.cst2335.algonquin_around_campus"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    //noinspection GradleCompatible,GradleCompatible
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // https://mvnrepository.com/artifact/net.sourceforge.jexcelapi/jxl
    implementation ("net.sourceforge.jexcelapi:jxl:2.6.12")
    implementation ("com.loopj.android:android-async-http:1.4.10")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("androidx.exifinterface:exifinterface:1.3.2")

}