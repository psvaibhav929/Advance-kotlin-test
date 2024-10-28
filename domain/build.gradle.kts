plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply<MainGradlePlugin>()

android {
    namespace = "om.example.domain"
}

dependencies {
    implementation(project(":common"))
    implementation(Retrofit.retrofitGson)
    daggerHilt()
    test()
}