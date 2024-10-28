plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply<MainGradlePlugin>()

android {
    namespace = "com.example.data"
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