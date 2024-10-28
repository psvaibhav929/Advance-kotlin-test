plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply<MainGradlePlugin>()

android {
    namespace = "com.example.data"
    defaultConfig {

        buildConfigField(type = "String", name = "BASE_URL", value = "\"https://api.cricapi.com/\"")
        buildConfigField(type = "String", name = "API_KEY", value = "\"7e5c88dc-c3fd-487a-b29f-cf4969b55120\"")
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Dep.coreKtx)
    // Retrofit for typesafe API calls
    retrofit()
    //DI - Dagger Hilt
    daggerHilt()
    test()
}