
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}
apply<MainGradlePlugin>()

android {
    namespace = "com.example.features"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}


dependencies {
    implementation(project(":domain"))
    implementation(Dep.coreKtx)
    implementation(Dep.lifecycleRuntime)
    implementation(Dep.coil)
    implementation(Retrofit.retrofitGson)
    implementation(DaggerHilt.hiltNavigationCompose)
    compose()
    daggerHilt()
    test()
    androidTest()
}