
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply<MainGradlePlugin>()

android {
    namespace = "com.example.common"
}


dependencies {
    implementation(Dep.coreKtx)
    compose()
    daggerHilt()
    test()
}