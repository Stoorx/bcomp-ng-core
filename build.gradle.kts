import org.jetbrains.kotlin.konan.target.buildDistribution

plugins {
    kotlin("multiplatform") version "1.3.61"
    id("maven-publish")
}

group = "me.stoorx"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        val main by compilations.getting {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    js {
        useCommonJs()
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                dependsOn(commonMain)
            }
        }

        val jsTest by getting {
            dependsOn(jsMain)
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                dependsOn(commonMain)
            }
        }

        val jvmTest by getting {
            dependsOn(jvmMain)
        }
    }
}